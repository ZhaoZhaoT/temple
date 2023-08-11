package com.example.temple.base;

import android.content.Context;

import androidx.annotation.NonNull;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class MyThirdDelegate {

    public static String WX_APP_ID="wx5acc316fe13f3e8a";
    public static String mWxAppSecret = " ";

    public static IWXAPI mWxApi;

    public static void onCreateApplication(@NonNull Context context) {

        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        mWxApi = WXAPIFactory.createWXAPI(context, WX_APP_ID, false);
        // 将该app注册到微信
        mWxApi.registerApp(WX_APP_ID);
    }


}
