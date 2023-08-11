package com.example.temple;

import android.Manifest;
import android.content.Intent;
import android.location.Criteria;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.azhon.appupdate.manager.DownloadManager;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.activity.base.BaseActivity;
import com.example.temple.adapter.FmPagerAdapter;
import com.example.temple.bean.TabEntity;
import com.example.temple.bean.VersionBean;
import com.example.temple.fragment.HomeFragment;
import com.example.temple.fragment.MeFragment;
import com.example.temple.fragment.MessageFragment;
import com.example.temple.fragment.ShopFragment;
import com.example.temple.fragment.WhiteFragment;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.AppManager;
import com.example.temple.utils.EventBusUtils;
import com.example.temple.view.NoScrollViewPager;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.rxjava.rxlife.RxLife;
import com.tbruyelle.rxpermissions3.RxPermissions;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_pager)
    NoScrollViewPager mainPager;
    @BindView(R.id.tab_view)
    CommonTabLayout tabView;


    private String[] mTitles = {"首页", "商城", "直播课","消息", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.home_normal, R.mipmap.type_normal, R.mipmap.shoop_normal,R.mipmap.message_normal, R.mipmap.me_normal};
    private int[] mIconSelectIds = {
            R.mipmap.home_press, R.mipmap.type_press, R.mipmap.shoop_press,R.mipmap.message_press, R.mipmap.me_press};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private FmPagerAdapter pagerAdapter;

    public HomeFragment mfrg1;
    public ShopFragment mfrg2;
    public WhiteFragment mfrg3;
    public MessageFragment mfrg4;
    public MeFragment mfrg5;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DLocationUtils.init(mContext);
    }*/

    @Override
    protected void initView() {
        baseTitleBar.setVisibility(View.GONE);
        BarUtils.setStatusBarLightMode(MainActivity.this, true);
        checkUpdate();
        //屏幕适配绑定，一定要写，否则加载不出来
//        ScreenUtil.init(this);
        mfrg1 = new HomeFragment();
        mfrg2 = new ShopFragment();
        mfrg3 = new WhiteFragment();
        mfrg4 = new MessageFragment();
        mfrg5 = new MeFragment();

        fragments.add(mfrg1);
        fragments.add(mfrg2);
        fragments.add(mfrg3);
        fragments.add(mfrg4);
        fragments.add(mfrg5);

        pagerAdapter = new FmPagerAdapter(getSupportFragmentManager(), 1, fragments);
        mainPager.setAdapter(pagerAdapter);
        mainPager.setOffscreenPageLimit(4);
        mainPager.setNoScroll(true);

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabView.setTabData(mTabEntities);
        isToLogin = false;
        EventBusUtils.register(this);

    }

    public void checkUpdate() {
        RxHttp.get(Comments.CHECK_UPDATE)
                .add("code", AppUtils.getAppVersionCode())//AppUtils.getAppVersionName()
                .asResponse(VersionBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        if (model.getData().getIsDownload() == 1) {
                            showUpdate(model.getData());
                        } else {
//                            ToastUtils.showLong("当前已是最新版本");
                        }
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }

    private void showUpdate(VersionBean bean) {
        new XPopup.Builder(this)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asConfirm("版本更新", "发现新版本，是否更新!",
                        null, "确定",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                getPermission(bean);
                            }
                        }, null, true).show();
    }

    private DownloadManager manager;
    public void getPermission(VersionBean bean) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> { // will emit 2 Permission objects
                    if (permission.granted) {
                        manager = DownloadManager.getInstance(mContext);
                        manager.setApkName("temple.apk")
                                .setApkUrl(bean.getUrl())//http://192.168.0.130/android/app-release.apk  //http://download.shpsz.cn/android/app-release.apk
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .download();
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                    } else {
                        // Denied permission with ask never again
                        // Need to go to the settings
//                        showDialog();
                    }
                });
    }


    @Override
    protected void onResume() {
        checkUpdate();
        super.onResume();
    }




    @Override
    protected void initListener() {
        tabView.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mainPager.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabView.setCurrentTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        ClickUtils.back2HomeFriendly("再按一次退出", 2000, new ClickUtils.Back2HomeFriendlyListener() {
            @Override
            public void show(CharSequence text, long duration) {
                ToastUtils.showShort(text);
            }

            @Override
            public void dismiss() {
                ToastUtils.cancel();
                AppManager.getAppManager().finishAllActivity();
                MainActivity.this.getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }



    private static Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);
        criteria.setBearingRequired(false);
        criteria.setAltitudeRequired(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return criteria;
    }




    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("test", "onNewIntent");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(Object event) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }

}