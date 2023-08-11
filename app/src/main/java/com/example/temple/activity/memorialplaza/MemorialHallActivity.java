package com.example.temple.activity.memorialplaza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

public class MemorialHallActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.ll_create)
    LinearLayout ll_create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_memorialhall;
    }

    @Override
    protected void initView() {
        baseTitleGone();


    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        ll_create.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.ll_create) {
            Intent intent = new Intent(mContext, CreateActivity.class);
            startActivity(intent);
        }
    }


}