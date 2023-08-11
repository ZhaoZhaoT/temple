package com.example.temple.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.inter.JsonLoaderCallBack;
import com.example.temple.utils.AppManager;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;
import butterknife.ButterKnife;


/*
* 不带fragment的activity,页面加载中、加载成功、加载失败的基类
* */
public abstract class BaseListActivity extends AppCompatActivity implements JsonLoaderCallBack {

    protected Context mContext;

    private LoadingPopupView mWaitDialog = null;

    public int mPage = 0;
    public int mTotalPage;

    @BindView(R.id.rView)
    protected RecyclerView rView;
    @BindView(R.id.refreshLayout)
    protected SmartRefreshLayout refreshLayout;
    protected View errorView;

    private FrameLayout ivBack;
    protected TextView baseTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        BarUtils.transparentStatusBar(this);
        BarUtils.setStatusBarLightMode(this, true);

        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        mContext=this;

        ivBack=findViewById(R.id.layout_left_back);
        baseTitle=findViewById(R.id.tv_base_title);

        initView();
        initListener();
        getList();
    }


    protected void initView(){
        errorView = getLayoutInflater().inflate(R.layout.error_view, null, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });
    }

    protected void initListener(){
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

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    protected void refreshData() {
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


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
            doThingsByJsonOk(re_data, reqcode);
    }

    public void doThingsByJsonOk(Object re_data, int reqcode) {
        hideWaitDialog();
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
        if (refreshLayout.isLoading()) {
            refreshLayout.finishLoadMore();
        }
    }

    public void doThingsByJsonFail(String re_code, String re_info, int reqcode) {
        hideWaitDialog();
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
        if (refreshLayout.isLoading()) {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void onJsonDataGetFailed(String re_code, String re_info, int reqcode) {
       /* if (re_code == 401) {//未登录或登录失效
           *//* DataCacheUtil dataUtils = DataCacheUtil.getInstance(this);
            dataUtils.saveUser(null);
            dataUtils.getmSharedPreferences().saveString("token", "");
            startActivityForResult(new Intent(this, LoginActivity.class), Comments.UNABLE_TO_LOGIN);*//*

        } else {*/
            if (!TextUtils.isEmpty(re_info)) {
                ToastUtils.showShort(re_info);
            }
//        }
        doThingsByJsonFail(re_code, re_info, reqcode);
    }


    public void showWaitDialog(String title, boolean cancelable) {
        if (mWaitDialog == null) {
            mWaitDialog = new XPopup.Builder(this)
                    .dismissOnTouchOutside(cancelable)
                    .hasShadowBg(false)
                    .asLoading();
        }
        mWaitDialog.setTitle(title);
        mWaitDialog.show();
    }

    public void hideWaitDialog() {
        if (mWaitDialog != null && mWaitDialog.isShow())
            mWaitDialog.dismiss();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
    }
}
