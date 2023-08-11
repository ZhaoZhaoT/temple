package com.example.temple.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.blankj.utilcode.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaseUtils {


    public String getJson(Context context,String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void initWebSetting(WebView detailWeb, int bgColor){
        WebSettings settings = detailWeb.getSettings();
        settings.setDefaultTextEncodingName("utf-8") ;
        if(bgColor!=0){
            detailWeb.setBackgroundColor(bgColor); // 设置背景色
//            detailWeb.getBackground().setAlpha(0); // 设置填充透明度 范围：0-255
        }
        detailWeb.setWebChromeClient(new WebChromeClient());
        detailWeb.setWebViewClient(new ArticleWebViewClient());

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);//也就是自适应页面大小 不能左右滑动
        settings.setLoadWithOverviewMode(true);// 这种加载模式，是缩小内容以适配屏幕宽度

        settings.setJavaScriptEnabled(true);

    }

    public static String phoneEncode(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return "";
        }
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

//    public static void startWXMini(Context context,String json){
//        String arr[] = json.split("&");
//        String appId = "wx21a6c1fa22a0441c"; // 填移动应用(App)的 AppId，非小程序的 AppID
//        IWXAPI api = WXAPIFactory.createWXAPI(context, appId);
//
//        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
//        req.userName = "gh_26856b40c4eb"; // 填小程序原始id
//        req.path = "pages/pays/pays?outTradeNo="+arr[0]+"&payMoney="+arr[1];                  ////拉起小程序页面的可带参路径，不填默认拉起小程序首页，对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"。
//        req.miniprogramType = Comments.miniprogramType;// 可选打开 开发版，体验版和正式版
//        api.sendReq(req);
//    }

//    public static void startAllianceZoneWXMini(Context context,String json){
//        String arr[] = json.split("&");
//        String appId = "wx21a6c1fa22a0441c"; // 填移动应用(App)的 AppId，非小程序的 AppID
//        IWXAPI api = WXAPIFactory.createWXAPI(context, appId);
//
//        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
//        req.userName = "gh_26856b40c4eb"; // 填小程序原始id
//        req.path = "pages/shop/shop?outTradeNo="+arr[0]+"&payMoney="+arr[1];                  ////拉起小程序页面的可带参路径，不填默认拉起小程序首页，对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"。
//        req.miniprogramType = Comments.miniprogramType;// 可选打开 开发版，体验版和正式版
//        api.sendReq(req);
//    }
}
