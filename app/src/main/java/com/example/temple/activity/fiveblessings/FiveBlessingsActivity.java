package com.example.temple.activity.fiveblessings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

public class FiveBlessingsActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.baijiax)
    ImageView baijiax;
    @BindView(R.id.iv_honor_hall)
    ImageView iv_honor_hall;
    @BindView(R.id.iv_hundred_schools_thought)
    ImageView iv_hundred_schools_thought;
    @BindView(R.id.iv_cangjin_pavilion)
    ImageView iv_cangjin_pavilion;
    @BindView(R.id.iv_guangchang)
    ImageView iv_guangchang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_five_blessings;
    }

    @Override
    protected void initView() {
        baseTitleGone();
    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        baijiax.setOnClickListener(this);
        iv_honor_hall.setOnClickListener(this);
        iv_hundred_schools_thought.setOnClickListener(this);
        iv_cangjin_pavilion.setOnClickListener(this);
        iv_guangchang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.baijiax) {
            startActivity(new Intent(this, BaiJiaXingActivity.class));
        } else if (v.getId() == R.id.iv_honor_hall) {
            startActivity(new Intent(this, HonorHallActivity.class));
        } else if (v.getId() == R.id.iv_hundred_schools_thought) {
            startActivity(new Intent(this, HundredSchoolsThoughtActivity.class));
        } else if (v.getId() == R.id.iv_cangjin_pavilion) {
            startActivity(new Intent(this, CangjinPavilionActivity.class));
        }else if (v.getId() == R.id.iv_guangchang) {
            startActivity(new Intent(this, HonorSquareActivity.class));
        }

    }


}