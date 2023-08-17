package com.example.temple.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.MyPagerAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 长寿篇
 */
public class LongevityChapterActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.lin_content_top)
    RelativeLayout lin_content_top;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.table_order)
    SlidingTabLayout tableOrder;
    @BindView(R.id.order_pager)
    ViewPager orderPager;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private final String[] mTitles = {"全部", "视频", "文章"};
    private MyPagerAdapter mAdapter;
    private String type;// ZERO 长寿篇 ONE 富贵篇 TWO 好德篇  THREE 康宁篇 FOUR 善终篇


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_longevity_chapter;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        type = getIntent().getStringExtra("type");
        if (type.equals("ZERO")) {
            tv_title.setText("长寿篇");
            lin_content_top.setBackgroundColor(Color.parseColor("#D9D9D9"));
        } else if (type .equals("ONE")) {
            tv_title.setText("富贵篇");
            lin_content_top.setBackgroundColor(Color.parseColor("#E7D6C7"));
        } else if (type .equals("TWO")) {
            tv_title.setText("好德篇");
            lin_content_top.setBackgroundColor(Color.parseColor("#EFE2CF"));
        } else if (type .equals("THREE")) {
            tv_title.setText("康宁篇");
            lin_content_top.setBackgroundColor(Color.parseColor("#DEE5E9"));
        } else if (type.equals("FOUR")) {
            tv_title.setText("善终篇");
            lin_content_top.setBackgroundColor(Color.parseColor("#CCCCCA"));
        }

        fragments.add(new ArticTypeListFragment("ZERO",type));
        fragments.add(new ArticTypeListFragment("ONE",type));
        fragments.add(new ArticTypeListFragment("TWO",type));
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), 1, fragments);
        mAdapter.setmTitles(mTitles);//设置页面标题
        orderPager.setOffscreenPageLimit(3);
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