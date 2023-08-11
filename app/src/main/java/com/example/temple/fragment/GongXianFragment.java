package com.example.temple.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.adapter.CauseCalcAdapter;
import com.example.temple.adapter.GXAdapter;
import com.example.temple.bean.GongXianBean;
import com.example.temple.bean.MyCauseCalcBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.BigDecimalUtils;
import com.example.temple.utils.SpaceDecoration;
import com.rxjava.rxlife.RxLife;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class GongXianFragment extends BaseFragment {
    private int mType=-1;
    private int page=0,size=10,mTotalPage;
    @BindView(R.id.rView)
    RecyclerView rView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_gx_month)
    RelativeLayout rlGxMohtn;
    @BindView(R.id.tv_gx_month)
    TextView tvGxMonth;
    @BindView(R.id.tv_chaxun)
    TextView tvChaXun;
    private GXAdapter mAdapter;
    private CauseCalcAdapter mCauseCalcAdapter;
    TimePickerView pvTime;

    public GongXianFragment(int mType) {
        this.mType = mType;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        rView.setHasFixedSize(true);

        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                getEveryDayList(getTime(date));
            }
        }).setType(new boolean[]{true, true,false,false,false,false})
                .setItemVisibleCount(6)
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)
                .build();


        if(mType == 0) {
            mAdapter = new GXAdapter(R.layout.item_zhangdan);
            rView.setAdapter(mAdapter);
            rView.addItemDecoration(new SpaceDecoration(0,0,0, SizeUtils.dp2px(10)));
            mAdapter.setEmptyView(R.layout.empty_view);
            getList();
        }
        if(mType == 1) {
            rlGxMohtn.setVisibility(View.VISIBLE);
            mCauseCalcAdapter = new CauseCalcAdapter(R.layout.item_cause_calc);
            rView.setAdapter(mCauseCalcAdapter);
            rView.addItemDecoration(new SpaceDecoration(0,0,0, SizeUtils.dp2px(10)));
            mCauseCalcAdapter.setEmptyView(R.layout.empty_view);
            refreshLayout.setEnableLoadMore(false);
            refreshLayout.setEnableRefresh(false);
            getEveryDayList(getTime(new Date()));
        }

    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }


    private void getList() {
        RxHttp.get(Comments.GET_EARNINGS_APP)
                .add("page",page)
                .add("size",size)
                .asResponse(GongXianBean.class)
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
                    if (model.getData() != null) {
                        mTotalPage=model.getData().getTotalPages();
                        onJsonDataGetSuccess(model.getData(), 1000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }

    private void getEveryDayList(String date) {
        RxHttp.get(Comments.GET_MY_CAUSE_CALC)
                .add("queryData",date)
                .asResponse(MyCauseCalcBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 2000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if(reqcode==1000){
            GongXianBean bean = (GongXianBean)re_data;
            if (bean.getContent()!= null && bean.getContent().size() > 0) {
                if (page == 0) {
                    mAdapter.setList(bean.getContent());
                } else {
                    mAdapter.addData(bean.getContent());
                }
            } else {
                mAdapter.setList(null);
            }
        }
        if(reqcode==2000){
            MyCauseCalcBean bean = (MyCauseCalcBean)re_data;
            tvGxMonth.setText(bean.getQueryData()+"累计贡献：" + BigDecimalUtils.stripTrailingZeros(bean.getQueryMonthMoney()));
            if (bean.getListData()!= null && bean.getListData().size() > 0) {
                if (page == 0) {
                    mCauseCalcAdapter.setList(bean.getListData());
                } else {
                    mCauseCalcAdapter.addData(bean.getListData());
                }
            } else {
                mCauseCalcAdapter.setList(null);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gongxian;
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
        refreshLayout.setHeaderTriggerRate(0.3f);
        tvChaXun.setOnClickListener(v -> {
            pvTime.show();
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        page = 0;
        getList();
    }

    public void refreshData() {
        //分页情况用于刷新数据
        page = 0;
        getList();
    }

    protected void loadMoreData() {
        //分页情况用于加载更多数据
        if (page < mTotalPage-1) {
            page++;
            getList();
        } else {
            ToastUtils.showShort("暂无更多数据！");
            if (refreshLayout.isLoading()) {
                refreshLayout.finishLoadMore();
            }
        }
    }



}
