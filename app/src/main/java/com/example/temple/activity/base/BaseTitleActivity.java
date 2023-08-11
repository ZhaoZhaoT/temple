package com.example.temple.activity.base;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.login.LoginActivity;
import com.example.temple.dialog.PermissionPopup;
import com.example.temple.inter.JsonLoaderCallBack;
import com.example.temple.network.Comments;
import com.example.temple.utils.AppManager;
import com.example.temple.utils.DataCacheUtil;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;

import butterknife.ButterKnife;


/*
* 不带fragment的activity,页面加载中、加载成功、加载失败的基类
* */
public abstract class BaseTitleActivity extends AppCompatActivity implements JsonLoaderCallBack {

    protected Context mContext;
    protected LinearLayout rootView;
    private LoadingPopupView mWaitDialog = null;
    protected FrameLayout ivBack;
    protected TextView baseTitle;
    protected ImageView ivBaseRight;
    protected RelativeLayout rlTop;
    protected View statusBar;
    protected Bundle mSavedInstanceState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_base_title);
        BarUtils.transparentStatusBar(this);
        BarUtils.setStatusBarLightMode(this, true);
        AppManager.getAppManager().addActivity(this);


        ivBack=findViewById(R.id.layout_left_back);
        baseTitle=findViewById(R.id.tv_base_title);
        rootView=findViewById(R.id.root_layout);
        ivBaseRight=findViewById(R.id.iv_base_right);
        rlTop=findViewById(R.id.rl_top);
        statusBar=findViewById(R.id.base_status_bar);

        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        rootView.addView(LayoutInflater.from(this).inflate(getLayoutId(), null),params);
        ButterKnife.bind(this);
        mContext=this;
        initView();
        initListener();
        initData();

    }

    protected void baseTitleGone() {
        statusBar.setVisibility(View.GONE);
        rlTop.setVisibility(View.GONE);
    }
    protected void grayTitle() {
        statusBar.setBackgroundColor(getResources().getColor(R.color.background));
        rlTop.setBackgroundColor(getResources().getColor(R.color.background));
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected void initListener(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

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
//            EventBusUtils.post(Comments.ON_EXIT);
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


    private PermissionPopup permissionDialog;
    /**
     * 权限弹窗
     */
    public void showPermissionDailog(String title, String content) {

        if (permissionDialog == null) {
            permissionDialog = new PermissionPopup(BaseTitleActivity.this, title,content,
                    new PermissionPopup.onClickDone() {
                @Override
                public void onDowm() {
                    startAppSettingDetail();
                }
            });
        }
        new XPopup.Builder(this)
                .dismissOnBackPressed(true)
                .dismissOnTouchOutside(true)
                .asCustom(permissionDialog)
                .show();
    }



    public void startAppSettingDetail() {
        String packageName = getPackageName();
        Uri packageURI = Uri.parse("package:" + packageName);
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(packageURI);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
    }
}
