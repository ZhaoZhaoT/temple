package com.example.temple.activity.memorialplaza;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

public class PlayVideoActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.video_view)
    VideoView mVv;

    @BindView(R.id.iv_start)
    ImageView ivStart;
    @BindView(R.id.banner)
    ImageView banner;

    private boolean play = false;
    String video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pley_video;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        video = getIntent().getStringExtra("video");
        tv_title.setText(getIntent().getStringExtra("name"));
        //添加播放控制条,还是自定义好点
        mVv.setMediaController(new MediaController(this));

//        GlideUtils.loadRoundCircleImage(mContext,video,banner,0);
        // 播放在线视频
        Uri rawUri = Uri.parse(video);
        mVv.setVideoPath(rawUri.toString());

    }

    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        ivStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.iv_start) {
            if (play) {
                play = !play;
                mVv.pause();
                ivStart.setVisibility(View.VISIBLE);
                banner.setVisibility(View.VISIBLE);
            } else {
                play = !play;
                mVv.start();
                ivStart.setVisibility(View.GONE);
                banner.setVisibility(View.GONE);
            }
        }
    }


}