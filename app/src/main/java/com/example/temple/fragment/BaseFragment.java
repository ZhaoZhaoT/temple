package com.example.temple.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.activity.login.LoginActivity;
import com.example.temple.inter.JsonLoaderCallBack;
import com.example.temple.network.Comments;
import com.example.temple.utils.DataCacheUtil;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wj on 2018/1/11.
 * 无MVP基类
 */

public abstract class BaseFragment extends Fragment implements JsonLoaderCallBack, View.OnClickListener {

    private Unbinder bind;
    private LoadingPopupView mWaitDialog = null;

    private boolean isLoaded = false;
    protected boolean isToLogin=true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(getLayoutId(), container, false);
        bind = ButterKnife.bind(this, itemView);
        initView(savedInstanceState);
        initListener();
        initData();

        return itemView;
    }

    /**
     * 初始化界面
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);


    protected abstract int getLayoutId();

    protected abstract void initListener();

    protected void initData() { }


    @Override
    public void onResume() {
        super.onResume();
        if (!isLoaded) {
            lazyInit();
//            Log.d("tag","lazyInit");
            isLoaded = true;
        }
    }

    protected void lazyInit() {
//        showWaitDialog("",true);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isLoaded=false;
    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        if(re_data!=null){
            doThingsByJsonOk(re_data, reqcode);
        }
    }

    public void doThingsByJsonOk(Object re_data, int reqcode) {
        hideWaitDialog();
    }


    @Override
    public void onJsonDataGetFailed(String re_code, String re_info, int reqcode) {
        if (re_info.contains("登陆信息已过期")) {
            DataCacheUtil dataUtils = DataCacheUtil.getInstance(getActivity());
            dataUtils.saveUser(null);
            dataUtils.saveUserInfo(null);
            dataUtils.getmSharedPreferences().saveString("token", "");
//            EventBusUtils.post(Comments.ON_EXIT);
            if(isToLogin){
                startActivityForResult(new Intent(getActivity(), LoginActivity.class), Comments.UNABLE_TO_LOGIN);
            }else{
                ToastUtils.showShort(re_info);
            }

        } else {
            if (!TextUtils.isEmpty(re_info)) {
                ToastUtils.showShort(re_info);
            }

        }
        doThingsByJsonFail(re_code, re_info, reqcode);
    }

    public void doThingsByJsonFail(String re_code, String re_info, int reqcode) {
        hideWaitDialog();
    }


    public void showWaitDialog(String title, boolean cancelable) {
        if (mWaitDialog == null) {
            mWaitDialog = new XPopup.Builder(getContext())
                    .dismissOnTouchOutside(cancelable)
                    .hasShadowBg(false)

                    .asLoading();
        }
        if(title!=null){
            mWaitDialog.setTitle(title);
        }
        mWaitDialog.show();
    }

    public void hideWaitDialog() {
        if (mWaitDialog != null && mWaitDialog.isShow())
//            mWaitDialog.dismiss();
            mWaitDialog.delayDismiss(500);
    }



  /*  public void checkLogin(){
        String token = DataCacheUtil.getInstance(getActivity()).getmSharedPreferences().getString("token", "");
        if(TextUtils.isEmpty(token)){
            startActivity(new Intent(getActivity(), LoginActivity.class));
            return;
        }
    }*/


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bind != null)
            bind.unbind();
    }

}
