package com.example.temple.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

public class HealthActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.ll_qiuzi)
    LinearLayout llQiuZi;

    @BindView(R.id.ll_yinyuan)
    LinearLayout ll_yinyuan;

    @BindView(R.id.ll_pingan)
    LinearLayout ll_pingan;

    @BindView(R.id.ll_xueye)
    LinearLayout ll_xueye;

    @BindView(R.id.ll_caifu)
    LinearLayout ll_caifu;

    @BindView(R.id.ll_jiankang)
    LinearLayout ll_jiankang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_health;
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
        llQiuZi.setOnClickListener(this);
        ll_yinyuan.setOnClickListener(this);
        ll_pingan.setOnClickListener(this);
        ll_xueye.setOnClickListener(this);
        ll_caifu.setOnClickListener(this);
        ll_jiankang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_left) {
            finish();
        } else if(v.getId() == R.id.ll_qiuzi) {
            startActivity(new Intent(this, QiuZiActivity.class).putExtra("index",0));
        }else if(v.getId() == R.id.ll_yinyuan) {
            startActivity(new Intent(this, QiuZiActivity.class).putExtra("index",1));
        }else if(v.getId() == R.id.ll_pingan) {
            startActivity(new Intent(this, QiuZiActivity.class).putExtra("index",2));
        }else if(v.getId() == R.id.ll_xueye) {
            startActivity(new Intent(this, QiuZiActivity.class).putExtra("index",3));
        }else if(v.getId() == R.id.ll_caifu) {
            startActivity(new Intent(this, QiuZiActivity.class).putExtra("index",4));
        }else if(v.getId() == R.id.ll_jiankang) {
            startActivity(new Intent(this, QiuZiActivity.class).putExtra("index",5));
        }
    }


}