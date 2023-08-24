
package com.example.temple.activity.fiveblessings;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

/**
 * 创建日程
 */
public class CreateScheduleActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout mIvLeft;
    @BindView(R.id.tv_to_add)
    TextView mTvToAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_schedule;
    }


    @Override
    protected void initView() {
        baseTitleGone();

    }


    @Override
    protected void initListener() {
        super.initListener();
        mIvLeft.setOnClickListener(this);
        mTvToAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.tv_to_add) {
            finish();
        }

    }


}