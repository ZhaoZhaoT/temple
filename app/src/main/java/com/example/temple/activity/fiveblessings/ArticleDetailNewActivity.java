
package com.example.temple.activity.fiveblessings;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.utils.BaseUtils;

import butterknife.BindView;

public class ArticleDetailNewActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout mIvLeft;
    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.title)
    TextView titleView;
    @BindView(R.id.tv_author)
    TextView mTvAuthor;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.view)
    WebView webView;

    private String title, authorName, content, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail_new;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        BaseUtils.initWebSetting(webView, Color.parseColor("#FFFFFF"));
        title = getIntent().getStringExtra("title");
        authorName = getIntent().getStringExtra("authorName");
        content = getIntent().getStringExtra("content");
        time = getIntent().getStringExtra("time");
        if (!TextUtils.isEmpty(content)) {
            webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        }
        titleView.setText(title);
        mTvAuthor.setText(authorName);
        mTvTime.setText(time);
    }


    @Override
    protected void initListener() {
        super.initListener();
        mIvLeft.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        }
    }


}