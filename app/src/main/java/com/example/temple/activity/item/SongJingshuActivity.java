package com.example.temple.activity.item;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.temple.BaseApplication;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.bean.haode.ChaoJinListBean;
import com.example.temple.dialog.SuccessHintPopup;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.DataCacheUtil;
import com.example.temple.utils.EventBusUtils;
import com.lxj.xpopup.XPopup;
import com.rxjava.rxlife.RxLife;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import me.wcy.lrcview.LrcView;
import rxhttp.wrapper.param.RxHttp;

public class SongJingshuActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.lrc_view)
    LrcView lrcView;
    @BindView(R.id.tv_current_time)
    TextView tv_current_time;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.progress_bar)
    SeekBar seekBar;
    @BindView(R.id.iv_play_pause)
    ImageView iv_play_pause;

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Handler handler = new Handler();

    String name, lrcpath, mpath, id;
    String totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_songbook;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        name = getIntent().getStringExtra("name");
        lrcpath = getIntent().getStringExtra("lrcpath");
        mpath = getIntent().getStringExtra("mpath");
        id = getIntent().getStringExtra("id");
        tv_title.setText(name);
        if (DataCacheUtil.getInstance(SongJingshuActivity.this).getmSharedPreferences().getBoolean("bgMusic", false)) {//打开
            BaseApplication.getInstance().getMediaPlayer().pause();
        }
        try {
            mediaPlayer.reset();
            //加载本地文件
            // 加载assets
            //AssetFileDescriptor fileDescriptor = getAssets().openFd("daodejin.mp3");
            //mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());

            //加载线上文件
            mediaPlayer.setDataSource(mpath);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(mp -> {
                seekBar.setMax(mediaPlayer.getDuration());
                seekBar.setProgress(0);
            });
            mediaPlayer.setOnCompletionListener(mp -> {
//                lrcView.updateTime(0);
//                seekBar.setProgress(0);
                iv_play_pause.setBackgroundResource(R.mipmap.songjing_play);
                if (!TextUtils.isEmpty(totalTime)) {
                    tv_current_time.setText(totalTime);
                }
                //播放完成
                getSaveHaoderSong();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 加载assets 下的歌词文本
        //String mainLrcText = getLrcText("daodejin_lrc.lrc");
        //lrcView.loadLrc(mainLrcText, null);
        // 加载在线歌词
        lrcView.loadLrcByUrl(lrcpath, "utf-8");

        lrcView.setDraggable(true, (view, time) -> {
            mediaPlayer.seekTo((int) time);
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                handler.post(runnable);
            }
            return true;
        });

        lrcView.setOnTapListener((view, x, y) -> {
//            Toast.makeText(this, "点击歌词", Toast.LENGTH_SHORT).show();
        });


        iv_play_pause.setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) {
                iv_play_pause.setBackgroundResource(R.mipmap.songjing_play_ing);

                mediaPlayer.start();
                handler.post(runnable);
            } else {
                iv_play_pause.setBackgroundResource(R.mipmap.songjing_play);
                mediaPlayer.pause();
                handler.removeCallbacks(runnable);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int duration2 = mediaPlayer.getDuration() / 1000;//获取音乐总时长
                int position = mediaPlayer.getCurrentPosition();//获取当前播放的位置
                tv_current_time.setText(calculateTime(position / 1000));//开始时间
                totalTime = calculateTime(duration2);
                tv_time.setText(calculateTime(duration2));//总时长
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                lrcView.updateTime(seekBar.getProgress());
                tv_current_time.setText(calculateTime(mediaPlayer.getCurrentPosition() / 1000));
            }
        });

    }

    SuccessHintPopup successHintPopup;

    public void getSaveHaoderSong() {
        RxHttp.postJson(Comments.HAODE_SAVE)
                .add("id", id)
                .add("type", "ONE")
                .asResponse(ChaoJinListBean.class)
                .doFinally(() -> {

                })
                .to(RxLife.toMain(this))
                .subscribe(model -> {
//                    ToastUtils.showShort("保存成功");
                    EventBusUtils.post(Comments.HAODE_SAVE_SUCCESS);
                    if (successHintPopup == null) {
                        successHintPopup = new SuccessHintPopup(SongJingshuActivity.this, "该篇经文诵读完成",
                                "确定", new SuccessHintPopup.onClickDone() {
                            @Override
                            public void selectAffrim() {
                                successHintPopup.dismiss();
                                finish();
                            }

                        });
                    }
                    new XPopup.Builder(SongJingshuActivity.this)
                            .dismissOnBackPressed(true)
                            .dismissOnTouchOutside(true)
                            .asCustom(successHintPopup)
                            .show();
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }


    private String getLrcText(String fileName) {
        String lrcText = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            lrcText = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lrcText;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer.isPlaying()) {
                long time = mediaPlayer.getCurrentPosition();
                lrcView.updateTime(time);
                seekBar.setProgress((int) time);
            }

            handler.postDelayed(this, 300);
        }
    };

    //计算播放时间
    public String calculateTime(int time) {
        int minute;
        int second;
        if (time >= 60) {
            minute = time / 60;
            second = time % 60;
            //分钟在0~9
            if (minute < 10) {
                //判断秒
                if (second < 10) {
                    return "0" + minute + ":" + "0" + second;
                } else {
                    return "0" + minute + ":" + second;
                }
            } else {
                //分钟大于10再判断秒
                if (second < 10) {
                    return minute + ":" + "0" + second;
                } else {
                    return minute + ":" + second;
                }
            }
        } else {
            second = time;
            if (second >= 0 && second < 10) {
                return "00:" + "0" + second;
            } else {
                return "00:" + second;
            }
        }
    }


    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
        if (DataCacheUtil.getInstance(SongJingshuActivity.this).getmSharedPreferences().getBoolean("bgMusic", false)) {//打开
            BaseApplication.getInstance().getMediaPlayer().start();
        }
        super.onDestroy();
    }

    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        }
    }


}