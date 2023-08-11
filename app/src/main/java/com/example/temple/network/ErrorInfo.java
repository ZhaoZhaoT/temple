package com.example.temple.network;

import android.text.TextUtils;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import rxhttp.wrapper.exception.HttpStatusCodeException;
import rxhttp.wrapper.exception.ParseException;

/**
 * Http请求错误信息
 */
public class ErrorInfo {

    private String errorCode;  //仅指服务器返回的错误码
    private String errorMsg; //错误文案，网络错误、请求失败错误、服务器返回的错误等文案
    private Throwable throwable; //异常信息

    public ErrorInfo(Throwable throwable) {

        this.throwable = throwable;
        String errorMsg = "";
        if (throwable instanceof UnknownHostException) {
            if (!NetworkUtils.isAvailable()) {//手机网络异常
                errorMsg = "当前无网络，请检查你的网络设置";
            } else {//服务器网络异常
                errorMsg = "网络连接不可用，请稍后重试！";
            }
        } else if (throwable instanceof SocketTimeoutException || throwable instanceof TimeoutException) {
            //前者是通过OkHttpClient设置的超时引发的异常，后者是对单个请求调用timeout方法引发的超时异常
//            this.errorCode = 10000;
            errorMsg = "连接超时,请稍后再试";
        } else if (throwable instanceof ConnectException) {
            errorMsg = "网络不给力，请稍候重试！";
        } else if (throwable instanceof HttpStatusCodeException) { //请求失败异常
            String code = throwable.getLocalizedMessage();
           /* this.errorCode = Integer.parseInt(code);
            if (code.startsWith("5")) {
                errorMsg = "服务器异常" + code;
            } else if(code.startsWith("4")) {
                errorMsg = "请求失败"+code;
            }else{*/
                errorMsg = throwable.getMessage();
//            }
        }
        else if (throwable instanceof JsonSyntaxException) { //请求成功，但Json语法异常,导致解析失败
            if(!TextUtils.isEmpty(throwable.getMessage())){
                errorMsg = throwable.getMessage();
            }else{
                errorMsg = "数据解析异常";
            }
        }
        else if (throwable instanceof ParseException) { // ParseException异常表明请求成功，但是数据不正确
            String errorCode = throwable.getLocalizedMessage();
            this.errorCode = errorCode;
            errorMsg = throwable.getMessage();
            if (TextUtils.isEmpty(errorMsg)) errorMsg = errorCode;//errorMsg为空，显示errorCode
           /* if (this.errorCode == 401) {//未登录
                *//*DataCacheUtil.getInstance().getmSharedPreferences().saveString("token","");
                DataCacheUtil.getInstance().saveUser(null);
                errorMsg = "未登录";
                return;*//*
            }*/
           /* else if(errorCode.startsWith("4")){
                errorMsg="请求失败"+errorCode;
            }*/
        } else {
            errorMsg = throwable.getMessage();
        }
        this.errorMsg = errorMsg;
        if (ThreadUtils.isMainThread()&&!TextUtils.isEmpty(errorMsg)) {
            ToastUtils.showLong(errorMsg);
        }

    }



    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
