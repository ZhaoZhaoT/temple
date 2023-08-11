package com.example.temple.utils;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ArticleWebViewClient extends WebViewClient {

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        //重置webview中img标签的图片大小
//        imgReset();

        view.loadUrl("javascript:(function(){ var objs = document.getElementsByTagName('img'); for(var i=0;i<objs.length;i++) { var img=objs[i]; img.style.maxWidth = '100%';img.style.height = 'auto';}})()");

       /* view.loadUrl("javascript:(function(){ var objs = document.getElementsByTagName('img'); for(var i=0;i<objs.length;i++) { var img=objs[i]; img.style.maxWidth = '100%';img.style.height = 'auto';} var texts = document.getElementsByTagName('p');"+
                "for(var i=0;i<texts.length;i++){var txt=texts[i];txt.style.color = '#999999'; }})()"
        );*/
    }




    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    /**
     * 对图片进行重置大小，宽度就是手机屏幕的宽度，高度根据宽度自动缩放
     */
   /* private void imgReset() {
        details_web.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img');" +
                "for(var i=0;i<objs.length;i++)" +
                "{" +
                "var img=objs[i];" +
                "img.style.maxWidth = '100%';img.style.height = 'auto';" +
                "}" +
                "})()");

    }*/
}



