package com.example.temple.activity.fiveblessings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.dialog.SurNamePopup;
import com.lxj.xpopup.XPopup;

import butterknife.BindView;

public class BaiJiaXingActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.layout_surname_zhao)
    LinearLayout layout_surname_zhao;

    @BindView(R.id.tv_surname_sacrifice)
    TextView tv_surname_sacrifice;
    @BindView(R.id.tv_ancestral_worship)
    TextView tv_ancestral_worship;

    @BindView(R.id.tv_wedding_banquet)
    TextView tv_wedding_banquet;


    private SurNamePopup surNamePopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_baijiaxing;
    }

    @Override
    protected void initView() {
        baseTitleGone();


    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        layout_surname_zhao.setOnClickListener(this);

        tv_surname_sacrifice.setOnClickListener(this);
        tv_ancestral_worship.setOnClickListener(this);
        tv_wedding_banquet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.layout_surname_zhao) {
            surNamePopup = new SurNamePopup(mContext);
            new XPopup.Builder(mContext)
                    .dismissOnBackPressed(true)
                    .dismissOnTouchOutside(true)
                    .asCustom(surNamePopup)
                    .show();
        } else if (v.getId() == R.id.tv_wedding_banquet) {
            startActivity(new Intent(BaiJiaXingActivity.this, WeddingBanquetActivity.class));
        }else if(v.getId() == R.id.tv_ancestral_worship) {
            startActivity(new Intent(BaiJiaXingActivity.this, AncestralWorshipActivity.class));
        }else if (v.getId() == R.id.tv_surname_sacrifice) {
            startActivity(new Intent(BaiJiaXingActivity.this, SurnameSacrificeActivity.class));
        }

    }


}