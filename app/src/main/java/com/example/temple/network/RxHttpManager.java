package com.example.temple.network;

import android.app.Application;

import com.example.temple.BuildConfig;
import com.example.temple.utils.DataCacheUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.cookie.CookieStore;
import rxhttp.wrapper.ssl.HttpsUtils;
import rxhttp.wrapper.ssl.HttpsUtils.SSLParams;

/**
 * 本类所有配置都是非必须的，根据自己需求选择就好
 * Date: 2019-11-26
 * Time: 20:44
 */
public class RxHttpManager {
    public static void init(Application context) {
        File file = new File(context.getExternalCacheDir(), "RxHttpCookie");
        SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new CookieStore(file))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .hostnameVerifier((hostname, session) -> true)
                .build();

        RxHttpPlugins.init(client)
                .setDebug(BuildConfig.DEBUG,true)

        .setOnParamAssembly(p -> {

            String token= DataCacheUtil.getInstance(context).getmSharedPreferences().getString("token","");
            return p.addHeader("Authorization", token);
        });
    }
}
