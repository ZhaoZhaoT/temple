package com.example.temple.activity.message;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

public class MessageActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        int mIndex=getIntent().getIntExtra("index",1);
        if(mIndex == 1) {
            mTitle.setText("系统消息");
        } else if(mIndex == 2) {
            mTitle.setText("日程提醒");
        } else if(mIndex == 3) {
            mTitle.setText("生日提醒");
        } else if(mIndex == 4) {
            mTitle.setText("忌日提醒");
        }
    }
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_left) {
            finish();
        }
    }


}