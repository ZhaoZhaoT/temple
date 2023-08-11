package com.example.temple.activity.fiveblessings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.ZhuziBaijiaDirectoryAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 诸子百家 目录
 */
public class ContentDirectoryActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.rView)
    RecyclerView mRView;
    private ZhuziBaijiaDirectoryAdapter mAdapter;

    @BindView(R.id.rView_two)
    RecyclerView mRViewTwo;
    private ZhuziBaijiaDirectoryAdapter mTwoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhuzi_xing_directory;
    }

    @Override
    protected void initView() {
        baseTitleGone();

        mRView.setLayoutManager(new LinearLayoutManager(this));
        mRView.setHasFixedSize(true);
        mAdapter = new ZhuziBaijiaDirectoryAdapter(R.layout.item_zhuzi_xing_directory);
        mRView.setAdapter(mAdapter);

        //假数据
        ArrayList<String> data = new ArrayList<String>();
        data.add("逍遥游第一");
        data.add("齐物论第二");
        data.add("养生主第三");
        data.add("人间世第四");
        data.add("德充符第五");
        data.add("大宗师第六");
        data.add("应帝王第七");
        mAdapter.setList(data);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(ContentDirectoryActivity.this, ContentScripturesActivity.class));
            }
        });


        mRViewTwo.setLayoutManager(new LinearLayoutManager(this));
        mRViewTwo.setHasFixedSize(true);
        mTwoAdapter = new ZhuziBaijiaDirectoryAdapter(R.layout.item_zhuzi_xing_directory);
        mRViewTwo.setAdapter(mTwoAdapter);

        //假数据
        ArrayList<String> data2 = new ArrayList<String>();
        data2.add("骈拇第八");
        data2.add("马蹄第九");
        data2.add("胠箧第十");
        data2.add("在宥第十一");
        data2.add("天地第十二");
        data2.add("天道第十三");
        mTwoAdapter.setList(data2);

        mTwoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(ContentDirectoryActivity.this, ContentScripturesActivity.class));
            }
        });

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