package com.example.temple.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.activity.item.order.AppointmentRecordActivity;
import com.example.temple.activity.item.order.OrderTeacherDetailsActivity;
import com.example.temple.adapter.OrderTeacherAdapter;
import com.example.temple.bean.teacher.MyTeacherListBean;
import com.example.temple.bean.teacher.TeacherListBean;
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

public class OrderTeacherActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.rView)
    RecyclerView mRView;
    @BindView(R.id.tv_appointment_record)
    TextView tv_appointment_record;
    private OrderTeacherAdapter mAdapter;

    private int page = 0, size = 10, mTotalPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_teacher;
    }

    @Override
    protected void initView() {
        baseTitleGone();
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
        mAdapter = new OrderTeacherAdapter(R.layout.item_order_teacher_list);
        mRView.setAdapter(mAdapter);
        mRView.addItemDecoration(new SpaceDecoration(0, 0, 0, SizeUtils.dp2px(10)));
        mAdapter.setEmptyView(R.layout.empty_view);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(mContext, OrderTeacherDetailsActivity.class)
                        .putExtra("id", mAdapter.getData().get(position).getId())
                        .putExtra("teacherimg", mAdapter.getData().get(position).getTeacherImg())
                        .putExtra("teacherremark", mAdapter.getData().get(position).getTeacherRemark())
                        .putExtra("teacheraddress", mAdapter.getData().get(position).getTeacherAddress())
                        .putExtra("teachername", mAdapter.getData().get(position).getTeacherName())
                        .putExtra("teachercount", mAdapter.getData().get(position).getTeacherCount())
                        .putExtra("teachermark", mAdapter.getData().get(position).getTeacherMark())
                        .putExtra("teacherprice", mAdapter.getData().get(position).getTeacherPrice())

                );
            }
        });

        refreshData();

        tv_appointment_record.setOnClickListener(this);
    }


    protected void loadMoreData() {
        //分页情况用于加载更多数据
        if (page < mTotalPage - 1) {
            page++;
            getTeacherList();
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
        getTeacherList();
        getMyTeacherList();
    }

    //我是否有预约的老师
    public void getMyTeacherList() {
        RxHttp.get(Comments.MY_TEACHER_LIST)
                .add("page", page)
                .add("size", size)
                .asResponse(MyTeacherListBean.class)
                .doFinally(() -> {

                })
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    mTotalPage = model.getData().getTotalPages();
                    if (model.getData().getContent() != null && model.getData().getContent().size() > 0) {
                        tv_appointment_record.setVisibility(View.VISIBLE);
                    } else {
                        tv_appointment_record.setVisibility(View.GONE);
                    }

                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }


    public void getTeacherList() {
        RxHttp.get(Comments.TEACHER_LIST)
                .add("page", page)
                .add("size", size)
                .asResponse(TeacherListBean.class)
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
        TeacherListBean bean = (TeacherListBean) re_data;
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
        if (event.equals(Comments.PAY_TEACHER_SUCCESS)) {
            refreshData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
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
        } else if (v.getId() == R.id.tv_appointment_record) {
            startActivity(new Intent(mContext, AppointmentRecordActivity.class));
        }
    }


}