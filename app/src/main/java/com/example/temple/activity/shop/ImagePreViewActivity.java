package com.example.temple.activity.shop;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.utils.GlideUtils;
import com.example.temple.view.PhotoViewPager;
import com.luck.picture.lib.photoview.PhotoView;

import butterknife.BindView;


public class ImagePreViewActivity extends BaseTitleActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView titleView;
    @BindView(R.id.vp_products)
    PhotoViewPager viewPager;

    private String[] imgIds;
    private int curPosition;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_pre_view;
    }

    @Override
    protected void initView() {
        rlTop.setVisibility(View.GONE);
        statusBar.setBackgroundColor(getResources().getColor(R.color.black));
        imgIds = getIntent().getStringArrayExtra("imgIds");
        curPosition = getIntent().getIntExtra("curPosition", 0);
        titleView.setText((curPosition + 1) + "/" + imgIds.length);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(this, imgIds);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(curPosition, true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                titleView.setText((position + 1) + "/" + imgIds.length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       /* ivDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ConvertUtils.view2Bitmap(viewPager.getChildAt(0));
                SaveIconUtils.getInstance().save(ImagePreViewActivity.this, bitmap);
            }
        });*/
    }


    private static class MyViewPagerAdapter extends PagerAdapter {

        private String[] imagUrls;
        private Activity mContext;

        public MyViewPagerAdapter(Activity context, String[] imagUrls) {
            mContext = context;
            this.imagUrls = imagUrls;
        }

        @Override
        public int getCount() {
            return imagUrls.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView iv = new PhotoView(container.getContext());
            String url = imagUrls[position];


            if ("屈原".equals(url)) {
                iv.setImageResource(R.mipmap.quyuan);
            } else if ("霍去病".equals(url)) {

            } else if ("岳飞".equals(url)) {
                iv.setImageResource(R.mipmap.yuefei);
            } else if ("文天祥".equals(url)) {
            } else if ("戚继光".equals(url)) {
                iv.setImageResource(R.mipmap.qijigaung);
            } else if ("王船山".equals(url)) {
            } else if ("林则徐".equals(url)) {
                iv.setImageResource(R.mipmap.linzexu);
            } else if ("左宗棠".equals(url)) {
                iv.setImageResource(R.mipmap.zuozongtang);
            } else if ("孙中山".equals(url)) {
            } else if ("邱少云".equals(url)) {
                iv.setImageResource(R.mipmap.qiushaoyun);
            } else if ("董存瑞".equals(url)) {
                iv.setImageResource(R.mipmap.dongcunrui);
            } else if ("黄继光".equals(url)) {
                iv.setImageResource(R.mipmap.huangjiguang);
            } else {
                GlideUtils.loadImage(mContext, url, iv);
            }

            container.addView(iv, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.finish();
                }
            });

            /*iv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Bitmap bitmap = ConvertUtils.view2Bitmap(iv);
                    SaveIconUtils.getInstance().save(mContext, bitmap);
                    return false;
                }
            });*/
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
