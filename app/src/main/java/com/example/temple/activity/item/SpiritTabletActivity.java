package com.example.temple.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.BarUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

/**
 * 在线连接 神位
 */
public class SpiritTabletActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.view_back)
    ImageView view_back;
    @BindView(R.id.img_add_shenwei)
    ImageView img_add_shenwei;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_spirit_table_view;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        BarUtils.setStatusBarLightMode(SpiritTabletActivity.this, false);
    }

    @Override
    protected void initListener() {
        super.initListener();
        view_back.setOnClickListener(this);
        img_add_shenwei.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.view_back) {
            finish();
        } else if (v.getId() == R.id.img_add_shenwei) {
            startActivity(new Intent(SpiritTabletActivity.this, SpiritTableConfirmOrderActivity.class));

        }
    }

}