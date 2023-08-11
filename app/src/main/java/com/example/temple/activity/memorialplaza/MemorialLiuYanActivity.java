package com.example.temple.activity.memorialplaza;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.BarUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.LiuYanAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class MemorialLiuYanActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.view)
    RecyclerView mRView;
    LiuYanAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_memorial_liuyan;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        BarUtils.setStatusBarLightMode(MemorialLiuYanActivity.this, false);

        mRView.setLayoutManager(new LinearLayoutManager(this));
        mRView.setHasFixedSize(true);
        mAdapter = new LiuYanAdapter(R.layout.item_liuyan_list);
        mRView.setAdapter(mAdapter);

        //假数据
        ArrayList<String> data = new ArrayList<String>();
        data.add("WWWW");
        data.add("WWWW");
        data.add("WWWW");
        data.add("WWWW");
        data.add("WWWW");
        data.add("WWWW");
        data.add("WWWW");
        data.add("WWWW");
        mAdapter.setList(data);

    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        }
    }


}