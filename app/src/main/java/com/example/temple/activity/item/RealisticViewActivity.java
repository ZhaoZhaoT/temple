package com.example.temple.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.BarUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;

import butterknife.BindView;

/**
 * 在线连接 真人观
 */
public class RealisticViewActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.view_back)
    ImageView view_back;
    @BindView(R.id.img_bg)
    ImageView img_bg;
    @BindView(R.id.view_dec)
    ImageView view_dec;

    @BindView(R.id.layout_dec_view)
    LinearLayout layout_dec_view;
    @BindView(R.id.view_realistis_arrow)
    ImageView view_realistis_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_realistis_view;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        BarUtils.setStatusBarLightMode(RealisticViewActivity.this, false);
    }

    @Override
    protected void initListener() {
        super.initListener();
        view_back.setOnClickListener(this);
        view_dec.setOnClickListener(this);
        img_bg.setOnClickListener(this);
        view_realistis_arrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.view_back) {
            finish();
        } else if (v.getId() == R.id.view_dec) {
            if (layout_dec_view.getVisibility() == View.VISIBLE) {
                layout_dec_view.setVisibility(View.GONE);
            } else if (layout_dec_view.getVisibility() == View.GONE) {
                layout_dec_view.setVisibility(View.VISIBLE);
            }
        } else if (v.getId() == R.id.view_realistis_arrow || v.getId() == R.id.img_bg) {
            if (layout_dec_view.getVisibility() == View.VISIBLE) {
                layout_dec_view.setVisibility(View.GONE);
            } else {
                startActivity(new Intent(RealisticViewActivity.this, SpiritTabletActivity.class));

            }
        }
    }

}