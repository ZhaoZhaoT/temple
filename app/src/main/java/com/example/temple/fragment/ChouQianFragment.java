package com.example.temple.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.temple.R;
import com.example.temple.dialog.JieQianPopup;
import com.lxj.xpopup.XPopup;

import butterknife.BindView;

public class ChouQianFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_click)
    TextView tvClick;

    @BindView(R.id.iv_zhuangzhong1)
    ImageView zhuangzhong1;
    @BindView(R.id.iv_zhuangzhong2)
    ImageView zhuangzhong2;

    private JieQianPopup jieQianPopup;


    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chouqian;
    }

    @Override
    protected void initListener() {
        tvClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v.getId() == R.id.tv_click) {
            zhuangzhong1.setVisibility(View.INVISIBLE);
            zhuangzhong2.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    zhuangzhong1.setVisibility(View.VISIBLE);
                    zhuangzhong2.setVisibility(View.INVISIBLE);
                    showCodeDialog();
                }
            },1000);
        }
    }

    public void showCodeDialog() {
        jieQianPopup = new JieQianPopup(getContext());

        new XPopup.Builder(getContext())
                .dismissOnBackPressed(true)
                .dismissOnTouchOutside(true)
                .asCustom(jieQianPopup)
                .show();
    }




}
