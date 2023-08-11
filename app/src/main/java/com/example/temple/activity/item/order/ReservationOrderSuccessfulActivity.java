package com.example.temple.activity.item.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

/**
 * 预约 成功
 */
public class ReservationOrderSuccessfulActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_click)
    TextView tv_click;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_time)
    TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reservation_order_success;
    }

    @Override
    protected void initView() {
        baseTitleGone();
    }


    @Override
    protected void initListener() {
        super.initListener();
        tv_address.setText(getIntent().getStringExtra("address"));
        tv_name.setText(getIntent().getStringExtra("name"));//预约人
        tv_time.setText(getIntent().getStringExtra("time"));

        iv_left.setOnClickListener(this);
        tv_click.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.tv_click) {
            startActivity(new Intent(this, AppointmentRecordActivity.class));
            finish();

        }
    }


}