package com.example.temple.activity.memorialplaza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.GoodEndingDetailsAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class MemorialPlazaActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.rView)
    RecyclerView mRView;

    @BindView(R.id.tv_to)
    TextView tv_to;
    GoodEndingDetailsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_memorial_plaza;
    }

    @Override
    protected void initView() {
        baseTitleGone();

        mRView.setLayoutManager(new LinearLayoutManager(this));
        mRView.setHasFixedSize(true);
        mAdapter = new GoodEndingDetailsAdapter(R.layout.item_good_ending_detail_list);
        mRView.setAdapter(mAdapter);

        //假数据
        ArrayList<String> data = new ArrayList<String>();
        data.add("岳飞");
        data.add("屈原");
        data.add("戚继光");
        data.add("林则徐");
        data.add("左宗棠");
        data.add("黄继光");
        data.add("邱少云");
        data.add("董存瑞");
        mAdapter.setList(data);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(MemorialPlazaActivity.this, MemorialPeopleActivity.class)
                        .putExtra("name", data.get(position)));
            }
        });

    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        tv_to.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        }else if (v.getId() == R.id.tv_to) {
            startActivity(new Intent(this, MemorialHallActivity.class));
        }
    }


}