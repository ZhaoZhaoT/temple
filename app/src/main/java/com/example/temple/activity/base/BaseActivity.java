package com.example.temple.activity.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.login.LoginActivity;
import com.example.temple.inter.JsonLoaderCallBack;
import com.example.temple.network.Comments;
import com.example.temple.utils.AppManager;
import com.example.temple.utils.DataCacheUtil;
import com.example.temple.view.BaseTitleBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;

import butterknife.ButterKnife;


/*
* 不带fragment的activity,页面加载中、加载成功、加载失败的基类
* */
public abstract class BaseActivity extends AppCompatActivity implements JsonLoaderCallBack {

    protected BaseTitleBar baseTitleBar;
    protected Context mContext;
    protected LinearLayout rootView;

    private LoadingPopupView mWaitDialog = null;
    protected boolean isToLogin=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
//        EventBus.getDefault().register(this);
        /*getWindow().getDecorView().findViewById(android.R.id.content)
                .setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));*/
        BarUtils.transparentStatusBar(this);
//        BarUtils.setStatusBarLightMode(this, true);
        AppManager.getAppManager().addActivity(this);

        baseTitleBar=findViewById(R.id.base_title_bar);
//        baseStatusBar=findViewById(R.id.base_status_bar);
        rootView=findViewById(R.id.root_layout);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        rootView.addView(LayoutInflater.from(this).inflate(getLayoutId(), null),params);
        ButterKnife.bind(this);
        mContext=this;
        initView();
        initListener();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initListener();

    protected void initData() {

    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
            doThingsByJsonOk(re_data, reqcode);
    }

    public void doThingsByJsonOk(Object re_data, int reqcode) {
        hideWaitDialog();
    }

    public void doThingsByJsonFail(String re_code, String re_info, int reqcode) {
        hideWaitDialog();
    }

    @Override
    public void onJsonDataGetFailed(String re_code, String re_info, int reqcode) {
        if (re_info.contains("登陆信息已过期")) {//未登录或登录失效
            DataCacheUtil dataUtils = DataCacheUtil.getInstance(mContext);
            dataUtils.saveUser(null);
            dataUtils.saveUserInfo(null);
            dataUtils.getmSharedPreferences().saveString("token", "");
            startActivityForResult(new Intent(mContext, LoginActivity.class), Comments.UNABLE_TO_LOGIN);
        } else {
            if (!TextUtils.isEmpty(re_info)) {
                ToastUtils.showShort(re_info);
            }
        }
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


}
