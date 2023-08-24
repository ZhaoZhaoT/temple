package com.example.temple.activity.fiveblessings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.activity.fiveblessings.fragment.WeddingBanquetListFragment;
import com.example.temple.adapter.MyPagerAdapter;
import com.example.temple.dialog.AllSurNamePopup;
import com.flyco.tablayout.SlidingTabLayout;
import com.lxj.xpopup.XPopup;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 姓氏祭祀
 */
public class SurnameSacrificeActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout mIvLeft;
    @BindView(R.id.tv_all)
    TextView mTvAll;
    @BindView(R.id.relat_memorabilia)
    RelativeLayout mRelatMemorabilia;
    @BindView(R.id.relat_schedule)
    RelativeLayout mRelatSchedule;
    @BindView(R.id.table_order)
    SlidingTabLayout tableOrder;
    @BindView(R.id.order_pager)
    ViewPager orderPager;

    @BindView(R.id.tv_my_publishing)
    TextView mTvMyPublishing;
    @BindView(R.id.tv_add)
    TextView mTvAdd;

    AllSurNamePopup allSurNamePopup;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private final String[] mTitles = {"全部", "视频", "相册", "文章"};
    private MyPagerAdapter mAdapter;
    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_surname_sacrifice;
    }

    @Override
    protected void initView() {
        baseTitleGone();

        mIndex = getIntent().getIntExtra("index", 1);
//
        fragments.add(new WeddingBanquetListFragment());
        fragments.add(new WeddingBanquetListFragment());
        fragments.add(new WeddingBanquetListFragment());
        fragments.add(new WeddingBanquetListFragment());
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), 1, fragments);
        mAdapter.setmTitles(mTitles);//设置页面标题
        orderPager.setOffscreenPageLimit(4);
        orderPager.setAdapter(mAdapter);
        tableOrder.setViewPager(orderPager);

    }


    @Override
    protected void initListener() {
        super.initListener();
        mIvLeft.setOnClickListener(this);
        mTvAll.setOnClickListener(this);
        mRelatMemorabilia.setOnClickListener(this);
        mRelatSchedule.setOnClickListener(this);
        mTvMyPublishing.setOnClickListener(this);
        mTvAdd.setOnClickListener(this);

        orderPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndex = position;
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
        } else if (v.getId() == R.id.tv_all) {
            if (allSurNamePopup == null) {
                allSurNamePopup = new AllSurNamePopup(this, new AllSurNamePopup.onClickDone() {
                    @Override
                    public void selectData(String surname) {
                        mTvAll.setText(surname);
                    }

                });
            }
            new XPopup.Builder(this)
                    .dismissOnBackPressed(true)
                    .dismissOnTouchOutside(true)
                    .asCustom(allSurNamePopup)
                    .show();


        } else if (v.getId() == R.id.relat_schedule) {//日程表
            startActivity(new Intent(SurnameSacrificeActivity.this, ScheduleActivity.class));

        } else if (v.getId() == R.id.relat_memorabilia) {//大事记
            startActivity(new Intent(SurnameSacrificeActivity.this, SurnameMemorabiliaActivity.class));

        } else if (v.getId() == R.id.tv_my_publishing) {
            startActivity(new Intent(SurnameSacrificeActivity.this, SurnameMemorabiliaActivity.class).putExtra("index", 1));

        } else if (v.getId() == R.id.tv_add) {

        }

    }


}