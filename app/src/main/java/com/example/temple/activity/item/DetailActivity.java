package com.example.temple.activity.item;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.MyPagerAdapter;
import com.example.temple.fragment.DetailFragment;
import com.example.temple.fragment.DirectoryFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class DetailActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.table_order)
    SlidingTabLayout tableOrder;
    @BindView(R.id.order_pager)
    ViewPager orderPager;

    @BindView(R.id.tv_title)
    TextView mTitle;

    @BindView(R.id.video_view)
    VideoView mVv;

    @BindView(R.id.iv_start)
    ImageView ivStart;
    @BindView(R.id.banner)
    ImageView banner;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private final String[] mTitles = {"简介", "目录"};
    private MyPagerAdapter mAdapter;
    private int mIndex;
    private boolean play = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {
        baseTitleGone();

        //添加播放控制条,还是自定义好点
        mVv.setMediaController(new MediaController(this));

        // 播放在线视频
        Uri rawUri = Uri.parse("https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/6be6fed3bb634cd1ee695a4f9c4904ab.mp4");
        mVv.setVideoPath(rawUri.toString());

        fragments.add(new DetailFragment());
        fragments.add(new DirectoryFragment());
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
        ivStart.setOnClickListener(this);

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
        }else if(v.getId() == R.id.iv_start) {
            if(play) {
                play=!play;
                mVv.pause();
                ivStart.setVisibility(View.VISIBLE);
                banner.setVisibility(View.VISIBLE);
            }else {
                play=!play;
                mVv.start();
                ivStart.setVisibility(View.GONE);
                banner.setVisibility(View.GONE);
            }
        }
    }


}