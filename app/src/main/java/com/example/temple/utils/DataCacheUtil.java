package com.example.temple.utils;

import android.content.Context;

import com.example.temple.bean.UserBean;
import com.example.temple.bean.UserInfoBean;


public class DataCacheUtil {

    private SharedPreferencesUtils mSharedPreferences;
    private static DataCacheUtil mInstance;

    /**
     * 通过单例模式实例化对象
     * @param context
     */
    private DataCacheUtil(Context context){
        mSharedPreferences=new SharedPreferencesUtils(context);
    }

    public static DataCacheUtil getInstance(Context context){
        if (mInstance==null){
            synchronized (DataCacheUtil.class){
                if (mInstance==null){
                    mInstance=new DataCacheUtil(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    public static DataCacheUtil getInstance(){
        if (mInstance==null){
           return null;
        }
        return mInstance;
    }

    public SharedPreferencesUtils getmSharedPreferences() {
        return mSharedPreferences;
    }

    /**
     * 保存单个用户数据存储
     * @param userBean
     */
    public void saveUser(UserBean userBean){
        mSharedPreferences.saveObject("user", userBean);
    }


    public UserBean getUser(){
        return mSharedPreferences.getObject("user", UserBean.class);
    }

    public void saveUserInfo(UserInfoBean userBean){
        mSharedPreferences.saveObject("user_info", userBean);
    }


    public UserInfoBean getUserInfo(){
        return mSharedPreferences.getObject("user_info", UserInfoBean.class);
    }
  /*  *//**
     * 保存List<User>对象
     * @param userBeans
     *//*
    public void saveUserList(List<UserBean> userBeans){
        mSharedPreferences.saveList("user_list_info", userBeans);
    }

    *//**
     * 获取List<User>对象
     * @return
     *//*
    public List<UserBean> getUserList(){
        return  mSharedPreferences.getList("user_list_info",new TypeToken<List<UserBean>>(){});
    }*/





}
