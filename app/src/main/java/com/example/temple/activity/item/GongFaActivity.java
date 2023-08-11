package com.example.temple.activity.item;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.activity.item.fragment.GongFaListFragment;
import com.example.temple.adapter.MyPagerAdapter;
import com.example.temple.bean.changshou.ChangShouListBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.flyco.tablayout.SlidingTabLayout;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class GongFaActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.table_order)
    SlidingTabLayout tableOrder;
    @BindView(R.id.order_pager)
    ViewPager orderPager;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private final String[] mTitles = {"太极养生功法", "道家洗髓功", "静坐行气功法", "气功养生"};
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gongfa;
    }

    @Override
    protected void initView() {
        baseTitleGone();

        getKangYangInfo();
    }


    public void getKangYangInfo() {
        RxHttp.get(Comments.ZHANGSHOU_INFO)
                .add("type", "ONE")
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
        for (int i = 0; i < bean.size(); i++) {
            fragments.add(new GongFaListFragment(bean.get(i).getContent()));
            mTitles[i] = bean.get(i).getTitle();
        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), 1, fragments);
        mAdapter.setmTitles(mTitles);//设置页面标题
        orderPager.setOffscreenPageLimit(bean.size());
        orderPager.setAdapter(mAdapter);
        tableOrder.setViewPager(orderPager);
    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);

        orderPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                mIndex=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        }
    }


}