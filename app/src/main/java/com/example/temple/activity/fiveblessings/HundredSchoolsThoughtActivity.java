package com.example.temple.activity.fiveblessings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

/**
 * 诸子百家
 */
public class HundredSchoolsThoughtActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.img_dao)
    ImageView img_dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hundred_school_thought;
    }

    @Override
    protected void initView() {
        baseTitleGone();


    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        img_dao.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.img_dao) {
            startActivity(new Intent(HundredSchoolsThoughtActivity.this, ZhuZiBaiJiaActivity.class).putExtra("name","道家"));
        }

    }


}