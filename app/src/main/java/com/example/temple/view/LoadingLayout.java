package com.example.temple.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.temple.R;


public class LoadingLayout extends FrameLayout {

    /**
     * 空数据View
     */
    private int mEmptyView;
    /**
     * 加载失败View
     */
    private int mErrorView;
    /**
     * 加载中View
     */
    private int mLoadingView;

    public LoadingLayout(Context context) {
        this(context, null);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LoadingLayout, 0, 0);
        try {
            mErrorView = a.getResourceId(R.styleable.LoadingLayout_errorView, R.layout.error_view);
            mLoadingView = a.getResourceId(R.styleable.LoadingLayout_loadingView, R.layout.loading_view);
            mEmptyView = a.getResourceId(R.styleable.LoadingLayout_emptyView, R.layout.empty_view);

            LayoutInflater inflater = LayoutInflater.from(getContext());
            inflater.inflate(mErrorView, this, true);
            inflater.inflate(mLoadingView, this, true);
            inflater.inflate(mEmptyView, this, true);
            getChildAt(0).setVisibility(View.GONE);
            getChildAt(1).setVisibility(View.GONE);
            getChildAt(2).setVisibility(View.GONE);
        } finally {
            a.recycle();
        }
    }


    /**
     * State View
     */
    public void showError(OnClickListener listener) {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 0) {
                child.setVisibility(VISIBLE);
                child.findViewById(R.id.iv_load_error).setOnClickListener(listener);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    public void showError(OnClickListener listener,String error) {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 0) {
                child.setVisibility(VISIBLE);
                child.findViewById(R.id.iv_load_error).setOnClickListener(listener);
                child.findViewById(R.id.tv_load_error).setOnClickListener(listener);
                ((TextView)child.findViewById(R.id.tv_load_error)).setText(error);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    public void showLoading() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 1) {
                child.setVisibility(VISIBLE);
//                ImageView imageView = child.findViewById(R.id.global_loading_anim_iv);

                //开启加载中的帧动画
//                AnimationDrawable mAnimationDrawable = (AnimationDrawable) imageView.getDrawable();
//                if (mAnimationDrawable != null) {
//                    mAnimationDrawable.start();
//                }
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    public void showEmpty() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 2) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    /**
     * 展示内容
     */
    public void showContent() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i > 2 ) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }


}
