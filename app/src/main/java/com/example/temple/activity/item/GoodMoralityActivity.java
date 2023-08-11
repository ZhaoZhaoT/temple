package com.example.temple.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

public class GoodMoralityActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.ll_chaojing)
    LinearLayout llChaoJing;

    @BindView(R.id.ll_zhuangzhong)
    LinearLayout llZhuangZhong;

    @BindView(R.id.ll_chouqian)
    LinearLayout llChouqian;

    @BindView(R.id.ll_mugu)
    LinearLayout ll_mugu;
    @BindView(R.id.ll_songjing)
    LinearLayout ll_songjing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_good_morality;
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
        llChaoJing.setOnClickListener(this);
        llZhuangZhong.setOnClickListener(this);
        llChouqian.setOnClickListener(this);
        ll_mugu.setOnClickListener(this);
        ll_songjing.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.ll_chaojing) {
            startActivity(new Intent(this, ChaojingActivity.class));
        }else if (v.getId() == R.id.ll_zhuangzhong) {
            startActivity(new Intent(this, ZhuangZhongActivity.class));
        }
        else if (v.getId() == R.id.ll_chouqian) {
            startActivity(new Intent(this, ChouQianActivity.class));
        }else if (v.getId() == R.id.ll_mugu) {
            startActivity(new Intent(this, MuGuActivity.class));
        }else if (v.getId() == R.id.ll_songjing) {
            startActivity(new Intent(this, SongJingActivity.class));
        }
    }



}