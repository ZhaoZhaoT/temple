package com.example.temple.activity.fiveblessings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

/**
 * 藏经阁
 */
public class CangjinPavilionActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout mIvLeft;

    @BindView(R.id.layout_one)
    RelativeLayout mLayoutOne;

    @BindView(R.id.layout_two)
    RelativeLayout mLayoutTwo;
    @BindView(R.id.layout_there)
    RelativeLayout mLayoutThere;
    @BindView(R.id.layout_four)
    RelativeLayout mLayoutFour;
    @BindView(R.id.layout_five)
    RelativeLayout mLayoutFive;
    @BindView(R.id.layout_six)
    RelativeLayout mLayoutSix;
    @BindView(R.id.layout_seven)
    RelativeLayout mLayoutSeven;
    @BindView(R.id.layout_eight)
    RelativeLayout mLayoutEight;
    @BindView(R.id.layout_nine)
    RelativeLayout mLayoutNine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cangjin_pavilion;
    }

    @Override
    protected void initView() {
        baseTitleGone();

    }


    @Override
    protected void initListener() {
        super.initListener();
        mIvLeft.setOnClickListener(this);

        mLayoutOne.setOnClickListener(this);
        mLayoutTwo.setOnClickListener(this);
        mLayoutThere.setOnClickListener(this);
        mLayoutFour.setOnClickListener(this);
        mLayoutFive.setOnClickListener(this);
        mLayoutSix.setOnClickListener(this);
        mLayoutSeven.setOnClickListener(this);
        mLayoutEight.setOnClickListener(this);
        mLayoutNine.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();

        } else if (v.getId() == R.id.layout_one) {
            startActivity(new Intent(CangjinPavilionActivity.this, CangjinStoreyActivity.class).putExtra("store", "一"));

        } else if (v.getId() == R.id.layout_two) {
            startActivity(new Intent(CangjinPavilionActivity.this, CangjinStoreyActivity.class).putExtra("store", "二"));

        } else if (v.getId() == R.id.layout_there) {
            startActivity(new Intent(CangjinPavilionActivity.this, CangjinStoreyActivity.class).putExtra("store", "三"));

        } else if (v.getId() == R.id.layout_four) {
            startActivity(new Intent(CangjinPavilionActivity.this, CangjinStoreyActivity.class).putExtra("store", "四"));

        } else if (v.getId() == R.id.layout_five) {
            startActivity(new Intent(CangjinPavilionActivity.this, CangjinStoreyActivity.class).putExtra("store", "五"));

        } else if (v.getId() == R.id.layout_six) {
            startActivity(new Intent(CangjinPavilionActivity.this, CangjinStoreyActivity.class).putExtra("store", "六"));

        } else if (v.getId() == R.id.layout_seven) {
            startActivity(new Intent(CangjinPavilionActivity.this, CangjinStoreyActivity.class).putExtra("store", "七"));

        } else if (v.getId() == R.id.layout_eight) {
            startActivity(new Intent(CangjinPavilionActivity.this, CangjinStoreyActivity.class).putExtra("store", "八"));

        } else if (v.getId() == R.id.layout_nine) {
            startActivity(new Intent(CangjinPavilionActivity.this, CangjinStoreyActivity.class).putExtra("store", "九"));

        }

    }


}