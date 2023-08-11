
package com.example.temple.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.BarUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.activity.memorialplaza.CreateActivity;
import com.example.temple.activity.memorialplaza.MemorialPeopleActivity;
import com.example.temple.adapter.GoodMuDiDetailsAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 在线连接  线上墓地
 */
public class OnLineMudiActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.view_back)
    ImageView view_back;
    @BindView(R.id.img_bg)
    ImageView img_bg;
    @BindView(R.id.view_dec)
    ImageView view_dec;
    @BindView(R.id.view_dec_view)
    ImageView view_dec_view;
    @BindView(R.id.rView)
    RecyclerView mRView;
    GoodMuDiDetailsAdapter mAdapter;

    @BindView(R.id.lin_creat)
    LinearLayout lin_creat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_online_mudi;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        BarUtils.setStatusBarLightMode(OnLineMudiActivity.this, false);
    }


    @Override
    protected void initListener() {
        super.initListener();
        view_back.setOnClickListener(this);
        view_dec.setOnClickListener(this);
        img_bg.setOnClickListener(this);
        lin_creat.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRView.setLayoutManager(layoutManager);
        mRView.setHasFixedSize(true);
        mAdapter = new GoodMuDiDetailsAdapter(R.layout.item_mudi_detail_list);
        mRView.setAdapter(mAdapter);
        //假数据
        ArrayList<String> data = new ArrayList<String>();
        data.add("董存瑞");
        data.add("邱少云");
        data.add("黄继光");
        data.add("左宗棠");
        data.add("林则徐");
        data.add("戚继光");
        data.add("屈原");
        data.add("岳飞");
        mAdapter.setList(data);
        mRView.smoothScrollToPosition(data.size());
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(OnLineMudiActivity.this, MemorialPeopleActivity.class)
                        .putExtra("name", data.get(position)));
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.view_back) {
            finish();
        }if (v.getId() == R.id.lin_creat) {
            Intent intent = new Intent(mContext, CreateActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.view_dec || v.getId() == R.id.img_bg) {
//            if (view_dec_view.getVisibility() == View.VISIBLE) {
//                view_dec_view.setVisibility(View.GONE);
//            } else if (view_dec_view.getVisibility() == View.GONE) {
//                view_dec_view.setVisibility(View.VISIBLE);
//            }
        }

    }


}