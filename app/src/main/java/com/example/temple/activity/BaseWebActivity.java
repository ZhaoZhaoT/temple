package com.example.temple.activity;

import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.bean.AboutUsBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.BaseUtils;
import com.rxjava.rxlife.RxLife;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class BaseWebActivity extends BaseTitleActivity {


    @BindView(R.id.webview)
    WebView webView;
    private String title;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_web;
    }

    @Override
    protected void initView() {
        title = getIntent().getStringExtra("title");
        if (TextUtils.isEmpty(title)) {
            rlTop.setVisibility(View.GONE);
        } else {
            baseTitle.setText(title);
        }

        initWebView();
    }

    public void initWebView() {
        String url=getIntent().getStringExtra("url");
        if(url!=null){
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        webView.getSettings()
                                .setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
                    }
                    handler.proceed();
                }

            });

            settings.setDatabaseEnabled(true);
            settings.setSaveFormData(true);
            settings.setDomStorageEnabled(true);


            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            webView.loadUrl(url);

        }else{
            BaseUtils.initWebSetting(webView, 0);

            if(title.equals("用户协议")||title.equals("隐私政策")){
                getInfo();
            }else{
                String content=getIntent().getStringExtra("content");
                webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
            }

        }
    }


    public void getInfo() {
        showWaitDialog("",true);
        RxHttp.get(Comments.GET_INFO)
                .asResponse(AboutUsBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 2000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
         if (reqcode == 2000) {
            AboutUsBean bean= (AboutUsBean) re_data;
            if(title.equals("用户协议")){
                webView.loadDataWithBaseURL(null, bean.getUser_agreement(), "text/html", "utf-8", null);
            }else if(title.equals("隐私政策")){
                webView.loadDataWithBaseURL(null, bean.getPrivate_sign(), "text/html", "utf-8", null);
            }
        }
    }


}