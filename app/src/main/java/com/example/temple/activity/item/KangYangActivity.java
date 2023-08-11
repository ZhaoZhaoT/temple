package com.example.temple.activity.item;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.bean.changshou.ChangShouListBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.BaseUtils;
import com.example.temple.utils.ListUtils;
import com.rxjava.rxlife.RxLife;

import java.util.List;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class KangYangActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.view)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_kangyang;
    }


    @Override
    protected void initView() {
        baseTitleGone();
        BaseUtils.initWebSetting(webView, Color.parseColor("#F1EBD8"));
        getKangYangInfo();
    }


    public void getKangYangInfo() {
        RxHttp.get(Comments.ZHANGSHOU_INFO)
                .add("type", "ZERO")
                .asResponseList(ChangShouListBean.class)
                .doFinally(() -> {

                })
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 3000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        List<ChangShouListBean> bean = (List<ChangShouListBean>) re_data;
        if (!ListUtils.isEmpty(bean)) {
            webView.loadDataWithBaseURL(null, bean.get(0).getContent(), "text/html", "utf-8", null);
        }

    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        }
    }


}