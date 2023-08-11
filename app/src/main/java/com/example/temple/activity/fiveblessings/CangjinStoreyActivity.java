package com.example.temple.activity.fiveblessings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.CangjinStoreListAdapter;
import com.example.temple.view.SpacesItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 藏经阁 楼层
 */
public class CangjinStoreyActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.rView)
    RecyclerView mRView;
    private CangjinStoreListAdapter mAdapter;
    private String store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cangjin_store;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        store = getIntent().getStringExtra("store");
    }


    @Override
    protected void initListener() {
        super.initListener();
        tv_title.setText("第" + store + "层");
        iv_left.setOnClickListener(this);

        mRView.setLayoutManager(new GridLayoutManager(this, 4));
        mRView.setHasFixedSize(true);
        mAdapter = new CangjinStoreListAdapter(R.layout.item_cangjin_store);
        mRView.setAdapter(mAdapter);
        mRView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(10), 4));

        View view = LayoutInflater.from(this).inflate(R.layout.empty_view, null);
        TextView tv_load_empty = view.findViewById(R.id.tv_load_empty);
        tv_load_empty.setText("暂无搜索结果");
        ImageView iv_load_empty = view.findViewById(R.id.iv_load_empty);
        iv_load_empty.setImageResource(R.mipmap.icon_no_result);

        mAdapter.setEmptyView(R.layout.empty_view);

        //假数据
        ArrayList<String> data = new ArrayList<String>();
        data.add("koko");
        data.add("ko1ko");
        data.add("ko2ko");
        data.add("koko");
        data.add("koko");
        data.add("ko1ko");
        data.add("ko2ko");
        data.add("koko");
        mAdapter.setList(data);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {

                startActivity(new Intent(CangjinStoreyActivity.this, ContentScripturesActivity.class));

            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        }

    }


}