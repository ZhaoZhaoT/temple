
package com.example.temple.activity.item.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.AppointmentResordAdapter;
import com.example.temple.bean.teacher.MyTeacherListBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.EventBusUtils;
import com.example.temple.utils.SpaceDecoration;
import com.rxjava.rxlife.RxLife;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

/**
 * 预约记录
 */
public class AppointmentRecordActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rView)
    RecyclerView mRView;

    AppointmentResordAdapter mAdapter;
    private int page = 0, size = 10, mTotalPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_appoinment_record;
    }

    @Override
    protected void initView() {
        baseTitleGone();
    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        EventBusUtils.register(this);
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

        mRView.setLayoutManager(new LinearLayoutManager(this));
        mRView.setHasFixedSize(true);
        mAdapter = new AppointmentResordAdapter(R.layout.item_appoinment_record_list);
        mRView.setAdapter(mAdapter);
        mRView.addItemDecoration(new SpaceDecoration(0, 0, 0, SizeUtils.dp2px(10)));
        mAdapter.setEmptyView(R.layout.empty_view);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(mContext, ReservedActivity.class)
                        .putExtra("id", mAdapter.getData().get(position).getId()));

            }
        });
        refreshData();
    }


    protected void loadMoreData() {
        //分页情况用于加载更多数据
        if (page < mTotalPage - 1) {
            page++;
            getMyTeacherList();
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
        getMyTeacherList();

    }

    //我是否有预约的老师
    public void getMyTeacherList() {
        RxHttp.get(Comments.MY_TEACHER_LIST)
                .add("page", page)
                .add("size", size)
                .asResponse(MyTeacherListBean.class)
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


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        MyTeacherListBean bean = (MyTeacherListBean) re_data;
        if (bean.getContent() != null && bean.getContent().size() > 0) {
            if (page == 0) {
                mAdapter.setList(bean.getContent());
            } else {
                mAdapter.addData(bean.getContent());
            }
        } else {
            if (page == 0) {
                mAdapter.setList(null);
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(Object event) {
        if (event.equals(Comments.TEACHER_CANCLE_SUCCESS) ||
                event.equals(Comments.TEACHER_OVER_SUCCESS)) {
            refreshData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        }
    }


}