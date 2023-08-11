package com.example.temple.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.temple.BaseApplication;
import com.example.temple.R;
import com.example.temple.bean.haode.ChaoJinListBean;
import com.example.temple.dialog.SuccessHintPopup;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.DataCacheUtil;
import com.example.temple.utils.EventBusUtils;
import com.lxj.xpopup.XPopup;
import com.rxjava.rxlife.RxLife;

import java.io.IOException;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class ZhuangZhongFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.iv_zhuangzhong1)
    ImageView zhuangzhong;

    @BindView(R.id.iv_zhuangzhong2)
    ImageView zhuangzhong2;

    @BindView(R.id.tv_count)
    TextView tvCount;

    int count = 0;
    private MediaPlayer mediaPlayer = null;
    SuccessHintPopup successHintPopup;

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chenzhong;
    }

    @Override
    protected void initListener() {
        zhuangzhong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.iv_zhuangzhong1) {
            zhuangzhong.setVisibility(View.INVISIBLE);
            zhuangzhong2.setVisibility(View.VISIBLE);
            playSound();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    zhuangzhong.setVisibility(View.VISIBLE);
                    zhuangzhong2.setVisibility(View.INVISIBLE);
                    count++;
                    if (count % 3 == 0) {
                        getSaveHaoderZhuangZhong();
                    }
                    tvCount.setText("" + count);
                }
            }, 1000);
        }
    }

    public void getSaveHaoderZhuangZhong() {
        RxHttp.postJson(Comments.HAODE_SAVE)
                .add("type", "TWO")
                .asResponse(ChaoJinListBean.class)
                .doFinally(() -> {

                })
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    EventBusUtils.post(Comments.HAODE_SAVE_SUCCESS);
                    if (successHintPopup == null) {
                        successHintPopup = new SuccessHintPopup(getActivity(), "完成撞钟三下，以祈 [福禄寿]", "确定", new SuccessHintPopup.onClickDone() {
                            @Override
                            public void selectAffrim() {
                                successHintPopup.dismiss();
                            }

                        });
                    }
                    new XPopup.Builder(getActivity())
                            .dismissOnBackPressed(true)
                            .dismissOnTouchOutside(true)
                            .asCustom(successHintPopup)
                            .show();

                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }


    private void playSound() {
        try {
            if (mediaPlayer == null) {
                //在初始化MediaPlayer时，通过create方法设置数据源。则不能写MediaPlayer.prepare()方法，这时，会报错
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.zhuangzhong_score);
                //播放完成触发此事件
                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        return false;
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (DataCacheUtil.getInstance(getActivity()).getmSharedPreferences().getBoolean("bgMusic", false)) {//打开
                            BaseApplication.getInstance().getMediaPlayer().start();
                        }
                    }
                });
                if (DataCacheUtil.getInstance(getActivity()).getmSharedPreferences().getBoolean("bgMusic", false)) {//打开
                    BaseApplication.getInstance().getMediaPlayer().pause();
                }

                mediaPlayer.start();
            } else {
                mediaPlayer.stop();
                //在播放音频资源之前，必须调用Prepare方法完成些准备工作
                mediaPlayer.prepare();
                if (DataCacheUtil.getInstance(getActivity()).getmSharedPreferences().getBoolean("bgMusic", false)) {//打开
                    BaseApplication.getInstance().getMediaPlayer().pause();
                }
                mediaPlayer.start();
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();//释放音频资源
            mediaPlayer = null;
        }
    }
}
