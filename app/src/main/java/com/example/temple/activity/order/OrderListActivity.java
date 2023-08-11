package com.example.temple.activity.order;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.MyPagerAdapter;
import com.example.temple.fragment.OrderFragment;
import com.example.temple.network.Comments;
import com.example.temple.utils.EventBusUtils;
import com.flyco.tablayout.SlidingTabLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

public class OrderListActivity extends BaseTitleActivity {

    @BindView(R.id.table_order)
    SlidingTabLayout tableOrder;
    @BindView(R.id.order_pager)
    ViewPager orderPager;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private final String[] mTitles = {"全部", "待付款", "待发货", "待收货"};
    private MyPagerAdapter mAdapter;
    private int mIndex;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void initView() {
        baseTitle.setText("我的订单");
        grayTitle();
        fragments.add(new OrderFragment(0));
        fragments.add(new OrderFragment(1));
        fragments.add(new OrderFragment(2));
        fragments.add(new OrderFragment(3));
//        fragments.add(new OrderFragment(3));

        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), 1, fragments);
        mAdapter.setmTitles(mTitles);//设置页面标题
        orderPager.setOffscreenPageLimit(5);
        orderPager.setAdapter(mAdapter);
        tableOrder.setViewPager(orderPager);
        EventBusUtils.register(this);

        mIndex=getIntent().getIntExtra("index",0);
        orderPager.setCurrentItem(mIndex,false);
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
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(Object event) {
        if(event.equals(Comments.ON_ORDER)){
            Log.i("eventbus","refresh"+mIndex);
            OrderFragment fragment=(OrderFragment)fragments.get(mIndex);
            fragment.refreshData();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(int type) {
        Log.i("eventbus","update"+mIndex);

        if(type==0){
            OrderFragment fragment1=(OrderFragment)fragments.get(1);
            fragment1.refreshData();
        }else if(type==1){
            OrderFragment fragment3=(OrderFragment)fragments.get(3);
            fragment3.refreshData();
        }
        OrderFragment fragment0=(OrderFragment)fragments.get(0);
        fragment0.refreshData();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }
}
