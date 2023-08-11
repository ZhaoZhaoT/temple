package com.example.temple.activity.item.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.example.temple.R;
import com.example.temple.fragment.BaseFragment;
import com.example.temple.utils.BaseUtils;

import butterknife.BindView;

public class GongFaListFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.view)
    WebView webView;

    String content;

    public GongFaListFragment(String content) {
        this.content = content;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        BaseUtils.initWebSetting(webView, Color.parseColor("#F1EBD8"));
        webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gongfa;
    }

    @Override
    protected void initListener() {
//        item1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }


}
