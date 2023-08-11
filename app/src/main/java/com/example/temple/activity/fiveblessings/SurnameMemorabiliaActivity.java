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
import com.example.temple.activity.fiveblessings.fragment.SurnameMemorabiliaFragment;
import com.example.temple.activity.fiveblessings.publish.PublishArticleActivity;
import com.example.temple.adapter.MyPagerAdapter;
import com.example.temple.dialog.AllSurNamePopup;
import com.example.temple.dialog.CreateContentPopup;
import com.flyco.tablayout.SlidingTabLayout;
import com.lxj.xpopup.XPopup;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 姓氏 祭祀大事记
 */
public class SurnameMemorabiliaActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_base_title)
    TextView tv_base_title;
    @BindView(R.id.tv_all)
    TextView tv_all;

    @BindView(R.id.table_order)
    SlidingTabLayout tableOrder;
    @BindView(R.id.order_pager)
    ViewPager orderPager;

    @BindView(R.id.tv_add)
    TextView tv_add;

    AllSurNamePopup allSurNamePopup;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private final String[] mTitles = {"全部", "我发布的"};
    private MyPagerAdapter mAdapter;
    private int mIndex;
    private int from;//0 姓氏  1 宗亲

    CreateContentPopup createContentPopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_surname_memorabilia;
    }

    @Override
    protected void initView() {
        baseTitleGone();

        mIndex = getIntent().getIntExtra("index", 0);
        from = getIntent().getIntExtra("from", 0);
//

        fragments.add(new SurnameMemorabiliaFragment());
        fragments.add(new SurnameMemorabiliaFragment());
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), 1, fragments);
        mAdapter.setmTitles(mTitles);//设置页面标题
        orderPager.setOffscreenPageLimit(2);
        orderPager.setAdapter(mAdapter);
        tableOrder.setViewPager(orderPager);
        if (mIndex != 0) {
            orderPager.setCurrentItem(mIndex);
        }

        tv_all.setOnClickListener(this);

        tv_add.setOnClickListener(this);

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
                        tv_all.setText(surname);
                    }


                });
            }
            new XPopup.Builder(this)
                    .dismissOnBackPressed(true)
                    .dismissOnTouchOutside(true)
                    .asCustom(allSurNamePopup)
                    .show();
        } else if (v.getId() == R.id.tv_add) {
            //去发布

            //这个是正确的
//            if (createContentPopup == null) {
//                createContentPopup = new CreateContentPopup(this, new CreateContentPopup.onClickDone() {
//                    @Override
//                    public void selectData(String surname) {
//                        tv_all.setText(surname);
//                    }
//
//
//                });
//            }
//            new XPopup.Builder(this)
//                    .dismissOnBackPressed(true)
//                    .dismissOnTouchOutside(true)
//                    .asCustom(createContentPopup)
//                    .show();




            startActivity(new Intent(SurnameMemorabiliaActivity.this, PublishArticleActivity.class));
        }

    }


}