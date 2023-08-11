package com.example.temple.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.temple.R;
import com.example.temple.activity.agent.AgentActivity;
import com.example.temple.activity.fiveblessings.FiveBlessingsActivity;
import com.example.temple.activity.home.LongevityChapterActivity;
import com.example.temple.activity.item.GoodEndingActivity;
import com.example.temple.activity.item.GoodMoralityActivity;
import com.example.temple.activity.item.HealthActivity;
import com.example.temple.activity.item.MoreLifeActivity;
import com.example.temple.activity.item.OrderTeacherActivity;
import com.example.temple.activity.item.RichActivity;
import com.example.temple.activity.item.daoli.DaoLiActivity;
import com.example.temple.activity.shop.ShopActivity;
import com.example.temple.bean.BannerBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.rxjava.rxlife.RxLife;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.xw.banner.Banner;
import com.xw.banner.BannerConfig;
import com.xw.banner.Transformer;
import com.xw.banner.listener.OnBannerListener;
import com.xw.banner.loader.ImageLoaderInterface;
import com.xw.banner.view.RoundAngleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.main_banner)
    Banner banner;

    @BindView(R.id.ll_changshou)
    LinearLayout llChangshou;

    @BindView(R.id.ll_fugui)
    LinearLayout llFugui;

    @BindView(R.id.ll_haode)
    LinearLayout llHaode;

    @BindView(R.id.ll_kangning)
    LinearLayout llKangning;

    @BindView(R.id.ll_shanzhong)
    LinearLayout llShanzhong;
    @BindView(R.id.ll_yuyuelaoshi)
    LinearLayout ll_yuyuelaoshi;
    @BindView(R.id.ll_shop)
    LinearLayout ll_shop;
    @BindView(R.id.ll_dailishang)
    LinearLayout ll_dailishang;
    @BindView(R.id.ll_daoli)
    LinearLayout ll_daoli;
    @BindView(R.id.ll_wufu)
    LinearLayout ll_wufu;

    @BindView(R.id.iv_longevity_chapter)
    ImageView iv_longevity_chapter;
    @BindView(R.id.iv_wealth_honor_chapter)
    ImageView iv_wealth_honor_chapter;
    @BindView(R.id.iv_haode)
    ImageView iv_haode;
    @BindView(R.id.iv_kangning)
    ImageView iv_kangning;
    @BindView(R.id.iv_shanzhong)
    ImageView iv_shanzhong;


    @Override
    protected void initView(Bundle savedInstanceState) {
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getBannerList();
            }
        });
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setHeaderTriggerRate(0.3f);

        banner.setBannerStyle(BannerConfig.CUSTOM_INDICATOR);
        banner.setImageLoader(new CustomRoundedImageLoader());
        // 设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        // 设置轮播时间
        banner.setDelayTime(3000);
        // 设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        // 设置标题集合（当banner样式有显示title时）
        //xw_banner.setBannerTitles(stringList);
        //banner.setBannerTitles(titleDatas);

        getBannerList();
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home2;
    }



    @Override
    protected void initListener() {
        llChangshou.setOnClickListener(this);
        llFugui.setOnClickListener(this);
        llHaode.setOnClickListener(this);
        llKangning.setOnClickListener(this);
        llShanzhong.setOnClickListener(this);
        ll_yuyuelaoshi.setOnClickListener(this);
        ll_shop.setOnClickListener(this);
        ll_dailishang.setOnClickListener(this);
        ll_daoli.setOnClickListener(this);
        ll_wufu.setOnClickListener(this);

        iv_longevity_chapter.setOnClickListener(this);
        iv_wealth_honor_chapter.setOnClickListener(this);
        iv_haode.setOnClickListener(this);
        iv_kangning.setOnClickListener(this);
        iv_shanzhong.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.ll_changshou) {
            startActivity(new Intent(getActivity(), MoreLifeActivity.class));
        } else if (v.getId() == R.id.ll_fugui) {
            startActivity(new Intent(getActivity(), RichActivity.class));
        } else if (v.getId() == R.id.ll_haode) {
            startActivity(new Intent(getActivity(), GoodMoralityActivity.class));
        } else if (v.getId() == R.id.ll_kangning) {
            startActivity(new Intent(getActivity(), HealthActivity.class));
        } else if (v.getId() == R.id.ll_shanzhong) {
            startActivity(new Intent(getActivity(), GoodEndingActivity.class));
        } else if (v.getId() == R.id.ll_yuyuelaoshi) {
            startActivity(new Intent(getActivity(), OrderTeacherActivity.class));
        } else if (v.getId() == R.id.ll_shop) {
            startActivity(new Intent(getActivity(), ShopActivity.class));
        } else if (v.getId() == R.id.ll_shop) {
            startActivity(new Intent(getActivity(), ShopActivity.class));
        } else if (v.getId() == R.id.ll_wufu) {
            startActivity(new Intent(getActivity(), FiveBlessingsActivity.class));
        } else if (v.getId() == R.id.ll_dailishang) {
            startActivity(new Intent(getActivity(), AgentActivity.class));
        } else if (v.getId() == R.id.ll_daoli) {
            startActivity(new Intent(getActivity(), DaoLiActivity.class));

        } else if (v.getId() == R.id.iv_longevity_chapter) {//长寿篇
            startActivity(new Intent(getActivity(), LongevityChapterActivity.class).putExtra("type", 0));
        } else if (v.getId() == R.id.iv_wealth_honor_chapter) {//富贵篇
            startActivity(new Intent(getActivity(), LongevityChapterActivity.class).putExtra("type", 1));
        } else if (v.getId() == R.id.iv_haode) {//好德篇
            startActivity(new Intent(getActivity(), LongevityChapterActivity.class).putExtra("type", 2));
        } else if (v.getId() == R.id.iv_kangning) {//康宁篇
            startActivity(new Intent(getActivity(), LongevityChapterActivity.class).putExtra("type", 3));
        } else if (v.getId() == R.id.iv_shanzhong) {//善终篇
            startActivity(new Intent(getActivity(), LongevityChapterActivity.class).putExtra("type", 4));
        }

    }

    public void getBannerList() {
        RxHttp.get(Comments.BANNER)
                .asResponseList(BannerBean.class)
                .doFinally(() -> {
                    //请求结束，当前在主线程回调
                    if (refreshLayout.isRefreshing()) {
                        refreshLayout.finishRefresh();
                    }
                    if (refreshLayout.isLoading()) {
                        refreshLayout.finishLoadMore();
                    }
                })
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 3000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 3000);
                });
    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);

        if (reqcode == 3000) {
            List<BannerBean> blist = (List<BannerBean>) re_data;
            if (blist != null) {
                if (blist.size() > 0) {
                    ArrayList<String> data = new ArrayList<>();
                    for (int i = 0; i < blist.size(); i++) {
                        data.add(blist.get(i).getImagePath());
                    }
                    banner.setImages(data);
                    banner.start();
                    banner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                        }
                    });

                }
            }
        }
    }


    private class CustomRoundedImageLoader implements ImageLoaderInterface {

        @Override
        public void displayImage(Context context, Object path, View imageView) {
            Glide.with(context).load(path).into((ImageView) imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            RoundAngleImageView roundedImg = new RoundAngleImageView(context);
            return roundedImg;
        }
    }


}
