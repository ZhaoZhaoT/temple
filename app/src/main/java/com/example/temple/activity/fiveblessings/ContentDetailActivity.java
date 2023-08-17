
package com.example.temple.activity.fiveblessings;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.utils.GlideUtils;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class ContentDetailActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_title)
    TextView mTitle;

    @BindView(R.id.tv_content_title)
    TextView tv_content_title;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_toptext)
    TextView tv_toptext;

    @BindView(R.id.jzplayerview)
    JZVideoPlayerStandard jzvdStd;

    String title, authorName, content, time, videoUrl, coverimg;

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
        title = getIntent().getStringExtra("title");
        authorName = getIntent().getStringExtra("authorName");
        content = getIntent().getStringExtra("content");
        time = getIntent().getStringExtra("time");
        videoUrl = getIntent().getStringExtra("video");
        coverimg = getIntent().getStringExtra("coverimg");

        tv_content_title.setText(title);
        tv_name.setText(authorName);
        tv_time.setText(time);
        tv_toptext.setText(content);


        jzvdStd.setUp((TextUtils.isEmpty(videoUrl) ? "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/6be6fed3bb634cd1ee695a4f9c4904ab.mp4"
                : videoUrl), JZVideoPlayer.SCREEN_WINDOW_NORMAL, "");
        jzvdStd.SAVE_PROGRESS = false;//始终从头开始播放：
        // 设置视频封面的图片填充方式为填满整个容器
        jzvdStd.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        // 设置视频封面图片
        GlideUtils.loadRoundCircleSeatImage(this, (TextUtils.isEmpty(coverimg) ? "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/6be6fed3bb634cd1ee695a4f9c4904ab.mp4"
                : coverimg), jzvdStd.thumbImageView, R.mipmap.no_empty_one, 0);
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

    @Override
    public void onResume() {
        super.onResume();
        if (jzvdStd != null) {
            jzvdStd.startVideo();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (jzvdStd != null) {
            jzvdStd.goOnPlayOnPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        jzvdStd.releaseAllVideos();
    }

}