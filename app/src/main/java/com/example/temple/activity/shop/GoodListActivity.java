package com.example.temple.activity.shop;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.ShoopAdapter;
import com.example.temple.bean.ShoopBean;
import com.example.temple.bean.TypeBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.view.SpaceItemDecoration;
import com.rxjava.rxlife.RxLife;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class GoodListActivity extends BaseTitleActivity {


    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.rView)
    RecyclerView rView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int goodType;
    private int page=0,size=10,mTotalPage;
    private ShoopAdapter mAdapter;
    private int mType;
    private String mGoodType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods;
    }

    @Override
    protected void initView() {

        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        rView.setLayoutManager(manager);
        mAdapter = new ShoopAdapter(R.layout.item_shoop2);
        rView.addItemDecoration(new SpaceItemDecoration(SizeUtils.dp2px(10), 2));
        rView.setAdapter(mAdapter);
        mAdapter.setEmptyView(R.layout.empty_view);

        mType=getIntent().getIntExtra("type",1);
        if(mType==1){
            baseTitle.setText("特供区");
            mGoodType = "ONE";
        }else if(mType==2){
            baseTitle.setText("文创区");
            mGoodType = "TWO";
        }else if(mType==3) {
            baseTitle.setText("互换区");
            mGoodType = "THREE";
        }else if(mType == 4) {
            baseTitle.setText("更多分类");
        }
        getProType();
    }


    @Override
    protected void initListener() {
        super.initListener();
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

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(mContext, ShoopDetailActivity.class)
                        .putExtra("name", mAdapter.getData().get(position).getName())
                        .putExtra("goodId", mAdapter.getData().get(position).getId()));
            }
        });
    }

    public void getProType() {
        RxHttp.get(Comments.GET_TYPE)
                .asResponseList(TypeBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 1000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }

    public void getByType() {
        RxHttp.get(Comments.GET_PRO_BY_TYPE)
                .add("goodTypeId",goodType)
                .add("typeEnum",mGoodType)
                .add("page",page)
                .add("size",size)
                .asResponse(ShoopBean.class)
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
                    mTotalPage=model.getData().getTotalPages();
                    onJsonDataGetSuccess(model.getData(), 2000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }

    public void getByTypePage() {
        RxHttp.get(Comments.QUERY_GOOD_TYPE_PAGE)
                .add("goodType",goodType)
                .add("page",page)
                .add("size",size)
                .asResponse(ShoopBean.class)
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
                    mTotalPage=model.getData().getTotalPages();
                    onJsonDataGetSuccess(model.getData(), 2000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }

    public void refreshData() {
        //分页情况用于刷新数据
        page = 0;
        if(mType == 4) {
            getByTypePage();
        }else {
            getByType();
        }

    }

    protected void loadMoreData() {
        //分页情况用于加载更多数据
        if (page < mTotalPage-1) {
            page++;
            if(mType == 4) {
                getByTypePage();
            }else {
                getByType();
            }
        } else {
            ToastUtils.showShort("暂无更多数据！");
            if (refreshLayout.isLoading()) {
                refreshLayout.finishLoadMore();
            }
        }
    }


    private int lastIndex;
    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 1000) {
            List<TypeBean> list = (List<TypeBean>) re_data;
            if(list.size()<=0){
                llType.setVisibility(View.GONE);
                return;
            }
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin=40;
            for (int i=0;i<list.size();i++) {
                TextView textView = new TextView(mContext);
                final int current=i;
                textView.setTextSize(16);
                if(i==0){
                    textView.setTextColor(getResources().getColor(R.color.tabcolor));
                }else{
                    textView.setTextColor(getResources().getColor(R.color.black));
                }
                textView.setText(list.get(i).getTypeName());
                textView.setLayoutParams(params);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goodType=list.get(current).getId();
                        ((TextView)llType.getChildAt(lastIndex)).setTextColor(getResources().getColor(R.color.black));
                        textView.setTextColor(getResources().getColor(R.color.tabcolor));
                        lastIndex=current;
                        refreshData();
                    }
                });
                llType.addView(textView);
            }
            goodType=list.get(0).getId();
            getByType();
        }else if(reqcode==2000){
            ShoopBean bean= (ShoopBean) re_data;

            if (bean.getContent()!= null && bean.getContent().size() > 0) {
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
    }
}