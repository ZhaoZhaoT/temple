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
import com.example.temple.adapter.ZhuZiBaiJiaListAdapter;
import com.example.temple.view.SpacesItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 诸子百家 姓氏内容
 */
public class ZhuZiBaiJiaActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.rView)
    RecyclerView mRView;
    private ZhuZiBaiJiaListAdapter mAdapter;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhuzi_baijia;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        name = getIntent().getStringExtra("name");
    }

//    zhuangzi_book
    @Override
    protected void initListener() {
        super.initListener();
        tv_title.setText(name);
        iv_left.setOnClickListener(this);

        mRView.setLayoutManager(new GridLayoutManager(this, 3));
        mRView.setHasFixedSize(true);
        mAdapter = new ZhuZiBaiJiaListAdapter(R.layout.item_zhuzi_baijia);
        mRView.setAdapter(mAdapter);
        mRView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(10),3));

        View view = LayoutInflater.from(this).inflate(R.layout.empty_view, null);
        TextView tv_load_empty = view.findViewById(R.id.tv_load_empty);
        tv_load_empty.setText("暂无搜索结果");
        ImageView iv_load_empty = view.findViewById(R.id.iv_load_empty);
        iv_load_empty.setImageResource(R.mipmap.icon_no_result);
        mAdapter.setEmptyView(view);

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
                startActivity(new Intent(ZhuZiBaiJiaActivity.this, ContentDirectoryActivity.class));


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