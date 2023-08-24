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
    RelativeLayout mIvLeft;
    @BindView(R.id.baijiax)
    ImageView mBaiJiaX;
    @BindView(R.id.iv_honor_hall)
    ImageView mIvHonorHall;
    @BindView(R.id.iv_hundred_schools_thought)
    ImageView mIvHundredSchoolsThought;
    @BindView(R.id.iv_cangjin_pavilion)
    ImageView mIvCangjinPavilion;
    @BindView(R.id.iv_guangchang)
    ImageView mIvGuangchang;

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
        mIvLeft.setOnClickListener(this);
        mBaiJiaX.setOnClickListener(this);
        mIvHonorHall.setOnClickListener(this);
        mIvHundredSchoolsThought.setOnClickListener(this);
        mIvCangjinPavilion.setOnClickListener(this);
        mIvGuangchang.setOnClickListener(this);
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