package com.example.temple.activity.memorialplaza;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

public class CreateActivity extends BaseTitleActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create;
    }

    @Override
    protected void initView() {
        baseTitleGone();


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