package com.example.temple.activity.memorialplaza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.BarUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.utils.GlideUtils;

import butterknife.BindView;

public class MemorialVideoActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.iv_start)
    ImageView iv_start;
    @BindView(R.id.img_view)
    ImageView img_view;

    String videoName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_memorial_video;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        BarUtils.setStatusBarLightMode(MemorialVideoActivity.this, false);
        videoName=getIntent().getStringExtra("video");

        GlideUtils.loadRoundCircleSeatImage(mContext,videoName,img_view,R.mipmap.no_empty_two,4);
    }
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        iv_start.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_left) {
            finish();
        }else if(v.getId() == R.id.iv_start ) {
            Intent intent = new Intent(mContext, PlayVideoActivity.class);
            intent.putExtra("video",videoName);
            intent.putExtra("name",getIntent().getStringExtra("name"));
            startActivity(intent);
        }
    }



}