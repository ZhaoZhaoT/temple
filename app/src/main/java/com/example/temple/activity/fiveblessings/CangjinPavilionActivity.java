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
    RelativeLayout iv_left;

    @BindView(R.id.layout_one)
    RelativeLayout layout_one;

    @BindView(R.id.layout_two)
    RelativeLayout layout_two;
    @BindView(R.id.layout_there)
    RelativeLayout layout_there;
    @BindView(R.id.layout_four)
    RelativeLayout layout_four;
    @BindView(R.id.layout_five)
    RelativeLayout layout_five;
    @BindView(R.id.layout_six)
    RelativeLayout layout_six;
    @BindView(R.id.layout_seven)
    RelativeLayout layout_seven;
    @BindView(R.id.layout_eight)
    RelativeLayout layout_eight;
    @BindView(R.id.layout_nine)
    RelativeLayout layout_nine;

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
        iv_left.setOnClickListener(this);

        layout_one.setOnClickListener(this);
        layout_two.setOnClickListener(this);
        layout_there.setOnClickListener(this);
        layout_four.setOnClickListener(this);
        layout_five.setOnClickListener(this);
        layout_six.setOnClickListener(this);
        layout_seven.setOnClickListener(this);
        layout_eight.setOnClickListener(this);
        layout_nine.setOnClickListener(this);

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