package com.example.temple.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.adapter.QiYuanQiangAdapter;
import com.example.temple.bean.QiYuanQiangBean;
import com.example.temple.datatype.DataMatching;
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

public class QiyuanQiangFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_list)
    RecyclerView view_list;
    QiYuanQiangAdapter qiYuanQiangAdapter;

    private int mType = 1;
    private int page = 0, size = 10, mTotalPage;

    public QiyuanQiangFragment(int mType) {
        this.mType = mType;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
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
        refreshLayout.setHeaderTriggerRate(0.3f);

        view_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        view_list.setHasFixedSize(true);
        qiYuanQiangAdapter = new QiYuanQiangAdapter(R.layout.item_qiyuan_list);
        view_list.setAdapter(qiYuanQiangAdapter);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.empty_view, null);
        TextView tv_load_empty = view.findViewById(R.id.tv_load_empty);
        tv_load_empty.setText("暂无数据");
        ImageView iv_load_empty = view.findViewById(R.id.iv_load_empty);
        iv_load_empty.setImageResource(R.mipmap.no_data);
        qiYuanQiangAdapter.setEmptyView(view);

        getQiyuanList(DataMatching.KangNingType.getKangNingDesc(String.valueOf(mType)));

    }


    public void getQiyuanList(String status) {
        RxHttp.get(Comments.KANGNING_QIYUANQIANG)
                .add("status", status)
                .add("page", page)
                .add("size", size)
                .asResponse(QiYuanQiangBean.class)
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
                    mTotalPage = model.getData().getTotalPages();
                    onJsonDataGetSuccess(model.getData(), 2000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }

    protected void loadMoreData() {
        //分页情况用于加载更多数据
        if (page < mTotalPage - 1) {
            page++;
            getQiyuanList(DataMatching.KangNingType.getKangNingDesc(String.valueOf(mType)));
        } else {
            ToastUtils.showShort("暂无更多数据！");
            if (refreshLayout.isLoading()) {
                refreshLayout.finishLoadMore();
            }
        }
    }

    public void refreshData() {
        //分页情况用于刷新数据
        page = 0;
        getQiyuanList(DataMatching.KangNingType.getKangNingDesc(String.valueOf(mType)));
    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        QiYuanQiangBean bean = (QiYuanQiangBean) re_data;

        if (bean.getContent() != null && bean.getContent().size() > 0) {
            if (page == 0) {
                qiYuanQiangAdapter.setList(bean.getContent());
            } else {
                qiYuanQiangAdapter.addData(bean.getContent());
            }
        } else {
            if (page == 0) {
                qiYuanQiangAdapter.setList(null);
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(Object event) {
        if (event.equals(Comments.PAY_SUCCESS)) {
            refreshData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_qiyuanqiang;
    }

    @Override
    protected void initListener() {
        EventBusUtils.register(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }


}
