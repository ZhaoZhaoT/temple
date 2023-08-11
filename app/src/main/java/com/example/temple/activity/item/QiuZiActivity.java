package com.example.temple.activity.item;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.MyPagerAdapter;
import com.example.temple.fragment.QiyuanFragment;
import com.example.temple.fragment.QiyuanQiangFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class QiuZiActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.table_order)
    SlidingTabLayout tableOrder;
    @BindView(R.id.order_pager)
    ViewPager orderPager;

    @BindView(R.id.tv_title)
    TextView mTitle;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private final String[] mTitles = {"祈愿", "祈愿墙"};
    private MyPagerAdapter mAdapter;
    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qiuzi;
    }

    @Override
    protected void initView() {
        baseTitleGone();

        mIndex=getIntent().getIntExtra("index",0);
        if(mIndex == 0) {
            mTitle.setText("求子");
        } else if(mIndex == 1) {
            mTitle.setText("姻缘");
        } else if(mIndex == 2) {
            mTitle.setText("平安");
        } else if(mIndex == 3) {
            mTitle.setText("学业");
        } else if(mIndex == 4) {
            mTitle.setText("财富");
        } else if(mIndex == 5) {
            mTitle.setText("健康");
        }
        fragments.add(new QiyuanFragment(mIndex));
        fragments.add(new QiyuanQiangFragment(mIndex));
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), 1, fragments);
        mAdapter.setmTitles(mTitles);//设置页面标题
        orderPager.setOffscreenPageLimit(2);
        orderPager.setAdapter(mAdapter);
        tableOrder.setViewPager(orderPager);
//        orderPager.setCurrentItem(mIndex,false);
    }
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;


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
                mIndex=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_left) {
            finish();
        }
    }


}