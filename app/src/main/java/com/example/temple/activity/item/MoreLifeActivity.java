package com.example.temple.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

public class MoreLifeActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.rl_yaoshan)
    RelativeLayout rl_yaoshan;
    @BindView(R.id.ll_yaoshan)
    LinearLayout ll_yaoshan;

    @BindView(R.id.iv_kangyang)
    ImageView iv_kangyang;
    @BindView(R.id.iv_gongfa)
    ImageView iv_gongfa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more_life;
    }

    @Override
    protected void initView() {
        baseTitleGone();


    }

    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        iv_kangyang.setOnClickListener(this);
        iv_gongfa.setOnClickListener(this);
        ll_yaoshan.setOnClickListener(this);
        rl_yaoshan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_left) {
            finish();
        }else if(v.getId() == R.id.iv_kangyang) {
            startActivity(new Intent(this, KangYangActivity.class));
        }else if(v.getId() == R.id.iv_gongfa) {
            startActivity(new Intent(this, GongFaActivity.class));
        }else if(v.getId() == R.id.ll_yaoshan || v.getId() == R.id.rl_yaoshan) {
            startActivity(new Intent(this, YaoShanActivity.class));
        }
    }




}