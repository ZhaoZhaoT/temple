package com.example.temple.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.activity.memorialplaza.MemorialPeopleActivity;
import com.example.temple.activity.memorialplaza.MemorialPlazaActivity;
import com.example.temple.adapter.GoodEndingAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class GoodEndingActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.tv_chakan)
    LinearLayout tv_chakan;

    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.rView)
    RecyclerView mRView;

    @BindView(R.id.lin_online_connection)
    LinearLayout lin_online_connection;
    @BindView(R.id.lin_family)
    LinearLayout lin_family;

    private GoodEndingAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_good_ending;
    }

    @Override
    protected void initView() {
        baseTitleGone();

    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        tv_chakan.setOnClickListener(this);

        lin_online_connection.setOnClickListener(this);
        lin_family.setOnClickListener(this);

        mRView.setLayoutManager(new GridLayoutManager(this, 4));
        mRView.setHasFixedSize(true);
        mAdapter = new GoodEndingAdapter(R.layout.item_good_ending_list);
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
                startActivity(new Intent(GoodEndingActivity.this, MemorialPeopleActivity.class)
                        .putExtra("name",data.get(position)));
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.tv_chakan) {
            startActivity(new Intent(this, MemorialPlazaActivity.class));
        }else if (v.getId() == R.id.lin_online_connection) {

            startActivity(new Intent(this, OnLineConnectionActivity.class));
        }else if (v.getId() == R.id.lin_family) {

            startActivity(new Intent(this, FamilyHeirloomActivity.class));

        }

    }


}