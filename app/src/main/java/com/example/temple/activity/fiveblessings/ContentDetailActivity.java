
package com.example.temple.activity.fiveblessings;

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

public class ContentDetailActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView mTitle;

    @BindView(R.id.video_view)
    VideoView mVv;

    @BindView(R.id.iv_start)
    ImageView ivStart;
    @BindView(R.id.banner)
    ImageView banner;

    private int mIndex;
    private boolean play = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_content_detail;
    }

    @Override
    protected void initView() {
        baseTitleGone();

        //添加播放控制条,还是自定义好点
        mVv.setMediaController(new MediaController(this));

        // 播放在线视频
        Uri rawUri = Uri.parse("https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/6be6fed3bb634cd1ee695a4f9c4904ab.mp4");
        mVv.setVideoPath(rawUri.toString());


    }
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        ivStart.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_left) {
            finish();
        }else if(v.getId() == R.id.iv_start) {
            if(play) {
                play=!play;
                mVv.pause();
                ivStart.setVisibility(View.VISIBLE);
                banner.setVisibility(View.VISIBLE);
            }else {
                play=!play;
                mVv.start();
                ivStart.setVisibility(View.GONE);
                banner.setVisibility(View.GONE);
            }
        }
    }


}