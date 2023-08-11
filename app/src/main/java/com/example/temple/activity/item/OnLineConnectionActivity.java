package com.example.temple.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

/**
 * 在线连接
 */
public class OnLineConnectionActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.lin_online_connection)
    LinearLayout lin_online_connection;
    @BindView(R.id.lin_online_mudi)
    LinearLayout lin_online_mudi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_online_connection;
    }

    @Override
    protected void initView() {
        baseTitleGone();
    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);

        lin_online_connection.setOnClickListener(this);
        lin_online_mudi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.lin_online_connection) {
            startActivity(new Intent(this, RealisticViewActivity.class));
        } else if (v.getId() == R.id.lin_online_mudi) {
            startActivity(new Intent(this, OnLineMudiActivity.class));
        }
    }

}