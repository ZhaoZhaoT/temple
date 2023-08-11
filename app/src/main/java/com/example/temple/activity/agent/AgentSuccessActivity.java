package com.example.temple.activity.agent;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.utils.AppManager;

import butterknife.BindView;

/**
 * 代理商 提交成功界面
 */
public class AgentSuccessActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_click)
    TextView tv_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_agent_scuuess;
    }

    @Override
    protected void initView() {
        baseTitleGone();

    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        tv_click.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left || v.getId() == R.id.tv_click) {
            AppManager.getAppManager().finishActivity(AgentActivity.class);
            finish();
        }
    }


}