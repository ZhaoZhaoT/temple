package com.example.temple;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDex;

import com.example.temple.base.MyThirdDelegate;
import com.example.temple.bean.address.NewAddressBean;
import com.example.temple.network.RxHttpManager;
import com.example.temple.utils.DataCacheUtil;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;

/**
 * BaseApplication
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;
    public static List<Activity> activities = new LinkedList<>();
    public static Double payMoney;
    private MediaPlayer mediaPlayer = null;

    public static NewAddressBean.ContentBean location;

    private int mFinalCount;
    private Boolean firstIn = false;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        RxHttpManager.init(this);
        AutoSize.initCompatMultiProcess(this);
        AutoSizeConfig.getInstance().setExcludeFontScale(true);
        handleSSLHandshake();

        //初始化第三方服务
        MyThirdDelegate.onCreateApplication(instance);
        location = new NewAddressBean.ContentBean();

        playSound();
        firstIn = DataCacheUtil.getInstance(instance).getmSharedPreferences().getBoolean("myActivityName", false);
        if (!firstIn) {
            DataCacheUtil.getInstance(instance).getmSharedPreferences().saveBoolean("myActivityName", true);
            DataCacheUtil.getInstance(instance).getmSharedPreferences().saveBoolean("bgMusic", true);
        }
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

                mFinalCount++;
                //如果mFinalCount ==1，说明是从后台到前台
                if (mFinalCount == 1) {
                    if (DataCacheUtil.getInstance(instance).getmSharedPreferences().getBoolean("bgMusic", false)) {
                        mediaPlayer.start();
                    }
                }
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

                mFinalCount--;
                //如果mFinalCount ==0，说明是前台到后台
                if (mFinalCount == 0) {
                    if (DataCacheUtil.getInstance(instance).getmSharedPreferences().getBoolean("bgMusic", false)) {
                        mediaPlayer.pause();
                    }
                }

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public void add(Activity activity) {
        activities.add(activity);
    }


    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {

                }
            }};
            SSLContext sc = SSLContext.getInstance("TLS");
            // trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception ignored) {

        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void playSound() {
        try {
            if (mediaPlayer == null) {
                //在初始化MediaPlayer时，通过create方法设置数据源。则不能写MediaPlayer.prepare()方法，这时，会报错
                mediaPlayer = MediaPlayer.create(instance, R.raw.guzheng_score);
                //播放完成触发此事件
                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        return false;
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (DataCacheUtil.getInstance(instance).getmSharedPreferences().getBoolean("bgMusic", false)) {
                            mediaPlayer.start();
                        }
                    }
                });

                if (DataCacheUtil.getInstance(this).getmSharedPreferences().getBoolean("bgMusic", false)) {
                    mediaPlayer.start();
                }

            } else {
                mediaPlayer.stop();
                //在播放音频资源之前，必须调用Prepare方法完成些准备工作
                mediaPlayer.prepare();
                if (DataCacheUtil.getInstance(instance).getmSharedPreferences().getBoolean("bgMusic", false)) {
                    mediaPlayer.start();
                }
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout refreshLayout) {
                ClassicsHeader header = new ClassicsHeader(context);
                /*header.setAccentColorId(ThemeTypeUtils.getType() == 1 ? R.color.text_color2 : R.color.text_color2_night);//主题色
                header.setPrimaryColorId(ThemeTypeUtils.getType() == 1 ? R.color.app_bg : R.color.app_bg_night);//背景色*/
                header.setTextSizeTitle(12f);
                header.setTextSizeTime(10);
                header.setFinishDuration(200);
                header.setDrawableSize(18);
                return header;
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout refreshLayout) {
                ClassicsFooter footer = new ClassicsFooter(context);
                /*footer.setAccentColorId(ThemeTypeUtils.getType() == 1 ? R.color.text_color2 : R.color.text_color2_night);//主题色
                footer.setPrimaryColorId(ThemeTypeUtils.getType() == 1 ? R.color.app_bg : R.color.app_bg_night);//背景色*/
                footer.setTextSizeTitle(12);
                footer.setFinishDuration(200);
                footer.setDrawableSize(18);
                return footer;
            }
        });
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }


}
