package com.example.temple.activity;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.example.temple.MainActivity;
import com.example.temple.R;
import com.example.temple.activity.base.BaseActivity;
import com.example.temple.activity.login.LoginActivity;
import com.example.temple.utils.DataCacheUtil;


public class SplashActivity extends BaseActivity {

    private boolean haveAdv = false;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        baseTitleBar.setVisibility(View.GONE);
//        boolean first= DataCacheUtil.getInstance(mContext).getmSharedPreferences().getBoolean("first",true);
        String token = DataCacheUtil.getInstance(mContext).getmSharedPreferences().getString("token", "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (haveAdv) {
//                    startActivity(new Intent(SplashActivity.this,AdversionActivity.class));
                } else {
                    Intent intent = new Intent();
                    if (TextUtils.isEmpty(token)) {
                        intent.setClass(SplashActivity.this, LoginActivity.class);
                    } else {
                        intent.setClass(SplashActivity.this, MainActivity.class);
                    }
                    startActivity(intent);
                    finish();
//
                }

            }
        }, 2000);
    }


   /* public void showMessage(){
        StartPopup startPopup = new StartPopup(mContext, new StartPopup.onClickDone() {
            @Override
            public void comfirm() {
                UMConfigure.init(mContext, "62be6b2205844627b5d3466a", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");

                Intent intent=new Intent();
                intent.setClass(SplashActivity.this,GuideActivity.class);
                startActivity(intent);
                DataCacheUtil.getInstance(mContext).getmSharedPreferences().saveBoolean("first",false);
            }

            @Override
            public void cancel() {
                finish();
            }
        });
        new XPopup.Builder(this)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(startPopup)
                .show();
    }*/
}