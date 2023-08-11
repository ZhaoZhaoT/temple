package com.example.temple.activity.user;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.MyPagerAdapter;
import com.example.temple.bean.UserInfoBean;
import com.example.temple.fragment.GongXianFragment;
import com.example.temple.utils.DataCacheUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;

public class GongXian2Activity extends BaseTitleActivity implements View.OnClickListener {


    @BindView(R.id.order_pager)
    ViewPager orderPager;

    @BindView(R.id.tv_trans_out)
    TextView tvTransOut;
    @BindView(R.id.tv_trans_detail)
    TextView tvTransDetail;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_dou)
    TextView tvDou;


    private ArrayList<Fragment> fragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    private int mIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gongxian;
    }

    @Override
    protected void initView() {
        baseTitle.setText("明细");
        statusBar.setBackgroundColor(getResources().getColor(R.color.background));
        rlTop.setBackgroundColor(getResources().getColor(R.color.background));
        fragments.add(new GongXianFragment(0));

        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), 1, fragments);
        orderPager.setOffscreenPageLimit(5);
        orderPager.setAdapter(mAdapter);
        UserInfoBean user = DataCacheUtil.getInstance(mContext).getUserInfo();
        tvBalance.setText(user.getBalance());
        tvDou.setText(user.getDou());
        mIndex=0;
        orderPager.setCurrentItem(mIndex,false);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 1000) {

        } else if (reqcode == 2000) {

        }
    }


    private String stripTrailingZeros(String s) {
        return new BigDecimal(s).stripTrailingZeros().toPlainString();
    }

    @Override
    protected void initListener() {
        super.initListener();
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
        tvTransOut.setOnClickListener(this);
        tvTransDetail.setOnClickListener(this);

    }

    private int getDay() {
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.DATE);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_trans_out) {
            Intent intent=new Intent(this, TransOutActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.tv_trans_detail) {
            Intent intent=new Intent(this, TransDetailActivity.class);
            startActivity(intent);
        }
    }
}
