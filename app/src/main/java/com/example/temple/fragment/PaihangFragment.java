package com.example.temple.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple.R;
import com.example.temple.adapter.JinWenRankAdapter;
import com.example.temple.bean.haode.RankListBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.EventBusUtils;
import com.rxjava.rxlife.RxLife;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class PaihangFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_list)
    RecyclerView view_list;
    JinWenRankAdapter jinWenRankAdapter;
    String type;


    public PaihangFragment(String type) {
        this.type = type;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBusUtils.register(this);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                loadMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getHaoderRankList();
            }
        });
        refreshLayout.setHeaderTriggerRate(0.3f);

        view_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        view_list.setHasFixedSize(true);
        jinWenRankAdapter = new JinWenRankAdapter(R.layout.item_progress_rank,"次");
        view_list.setAdapter(jinWenRankAdapter);
        jinWenRankAdapter.setEmptyView(R.layout.empty_view);

        getHaoderRankList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_paihang;
    }

    @Override
    protected void initListener() {

    }

    public void getHaoderRankList() {
        RxHttp.get(Comments.HAODE_RANK)
                .add("type", type)
                .asResponse(RankListBean.class)
                .doFinally(() -> {
                    //请求结束，当前在主线程回调
                    if (refreshLayout.isRefreshing()) {
                        refreshLayout.finishRefresh();
                    }
                    if (refreshLayout.isLoading()) {
                        refreshLayout.finishLoadMore();
                    }
                })
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 3000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        RankListBean bean = (RankListBean) re_data;
        jinWenRankAdapter.setList(bean.getRanking());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(Object event) {
        if (event.equals(Comments.HAODE_SAVE_SUCCESS)) {
            getHaoderRankList();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }


}
