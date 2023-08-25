package com.example.temple.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.temple.R;
import com.example.temple.activity.livebroadcast.LiveFragment;
import com.example.temple.adapter.MyPagerAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class WhiteFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.table_order)
    SlidingTabLayout tableOrder;
    @BindView(R.id.order_pager)
    ViewPager orderPager;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] mTitles={"热门","风水玄学","国学讲堂"};
    private MyPagerAdapter mAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {

        fragments.add(new LiveFragment("0"));
        fragments.add(new LiveFragment("1"));
        fragments.add(new LiveFragment("2"));
        mAdapter = new MyPagerAdapter(getChildFragmentManager(), 1, fragments);

        mAdapter.setmTitles(mTitles);//设置页面标题
        orderPager.setOffscreenPageLimit(mTitles.length);
        orderPager.setAdapter(mAdapter);
        tableOrder.setViewPager(orderPager);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_white;
    }

    @Override
    protected void initListener() {
        orderPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }



}
