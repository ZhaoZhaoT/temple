package com.example.temple.inter;

/**
 * Created by lenovo on 2016/7/13.
 */
public interface JsonLoaderCallBack {
    void onJsonDataGetSuccess(Object re_data, int reqcode);
//    void onJsonDataGetFailed(int re_code, String re_info, int reqcode);
    void onJsonDataGetFailed(String re_code, String re_info, int reqcode);
}
