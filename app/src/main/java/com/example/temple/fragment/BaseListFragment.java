package com.example.temple.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;

public abstract class BaseListFragment extends BaseFragment {

    public int mPage = 0;
    public int mTotalPage;


    @BindView(R.id.rView)
    protected RecyclerView rView;
    @BindView(R.id.refreshLayout)
    protected SmartRefreshLayout refreshLayout;
   /* @BindView(R.id.loading_layout)
    protected LoadingLayout loadingLayout;*/

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData();
            }
        });
    }

    @Override
    public void onJsonDataGetFailed(String re_code, String re_info, int reqcode) {
        super.onJsonDataGetFailed(re_code, re_info, reqcode);
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
        if (refreshLayout.isLoading()) {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
//        loadingLayout.showContent();
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
        if (refreshLayout.isLoading()) {
            refreshLayout.finishLoadMore();
        }

    }

    public void refreshData() {
        //分页情况用于刷新数据
        mPage = 0;
        getList();
    }

    protected void loadMoreData() {
        //分页情况用于加载更多数据
        if (mPage < mTotalPage-1) {
            mPage++;
            getList();
        } else {
            ToastUtils.showShort("没有更多数据了");
            if (refreshLayout.isLoading()) {
                refreshLayout.finishLoadMore();
            }
        }
    }

    protected void getList() {

    }

}
