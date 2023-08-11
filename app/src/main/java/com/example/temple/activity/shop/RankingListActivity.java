package com.example.temple.activity.shop;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseListActivity;
import com.example.temple.adapter.ShoopGridAdapter;
import com.example.temple.bean.ShoopBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.SpaceDecoration;
import com.rxjava.rxlife.RxLife;

import rxhttp.wrapper.param.RxHttp;


public class RankingListActivity extends BaseListActivity {

    private ShoopGridAdapter mAdapter;
//    private int page=0,size=10,mType;
//    private int page=0,size=10;
    private int mType;

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return super.getLayoutInflater();
    }

    @Override
    protected void initView() {
        mType=getIntent().getIntExtra("type",0);
        if(mType==1){
            baseTitle.setText("排行榜");
        }

        rView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new ShoopGridAdapter(R.layout.item_shoop1);
        rView.addItemDecoration(new SpaceDecoration(0, 0, 0, SizeUtils.dp2px(10)));
        rView.setAdapter(mAdapter);
        mAdapter.setEmptyView(R.layout.empty_view);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(mContext, ShoopDetailActivity.class)
                        .putExtra("name", mAdapter.getData().get(position).getName())
                        .putExtra("goodId", mAdapter.getData().get(position).getId()));
            }
        });
    }


    @Override
    protected void getList() {
        super.getList();
        RxHttp.get(Comments.SEARCH_KEY)
                .add("page",mPage)
                .add("size",10)
                .asResponse(ShoopBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        mTotalPage=model.getData().getTotalPages();
                        onJsonDataGetSuccess(model.getData(), 1000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if(reqcode==1000){
            ShoopBean bean = (ShoopBean)re_data;
            if (bean.getContent()!= null && bean.getContent().size() > 0) {
                if (mPage == 0) {
                    mAdapter.setList(bean.getContent());
                } else {
                    mAdapter.addData(bean.getContent());
                }
            } else {
                mAdapter.setList(null);
            }
        }
    }
}