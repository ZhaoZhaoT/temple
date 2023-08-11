package com.example.temple.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.BaseWebActivity;
import com.example.temple.activity.order.OrderListActivity;
import com.example.temple.activity.shop.GoodListActivity;
import com.example.temple.activity.shop.RankingListActivity;
import com.example.temple.activity.shop.ShoopDetailActivity;
import com.example.temple.adapter.HomeAdapter1;
import com.example.temple.adapter.HomeAdapter2;
import com.example.temple.bean.BannerBean;
import com.example.temple.bean.HomeBean;
import com.example.temple.bean.UserInfoBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.DataCacheUtil;
import com.example.temple.utils.GlideUtils;
import com.example.temple.view.CustomImageText;
import com.example.temple.view.SpaceItemDecoration;
import com.rxjava.rxlife.RxLife;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class ShopFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.ll_home_search)
    LinearLayout llHomeSearch;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.menu1)
    CustomImageText menu1;
    @BindView(R.id.menu2)
    CustomImageText menu2;
    @BindView(R.id.menu3)
    CustomImageText menu3;
    @BindView(R.id.menu4)
    CustomImageText menu4;

    @BindView(R.id.rView1)
    RecyclerView rView1;
    @BindView(R.id.tv_recomd_title)
    TextView tvRecomdTitle;
    @BindView(R.id.rView2)
    RecyclerView rView2;
    @BindView(R.id.edit_search)
    TextView editSearch;
    @BindView(R.id.tv_home_more)
    TextView tvHomeMore;
    @BindView(R.id.order)
    ImageView order;

    private HomeAdapter1 mAdapter1;
    private HomeAdapter2 mAdapter2;
    private int page = 0, size = 10,mTotalPage;

    @Override
    protected void initView(Bundle savedInstanceState) {
        banner.setIndicator(new CircleIndicator(getActivity()))
                .setLoopTime(3000)
                .setScrollTime(800)
                .setBannerRound(8)
                .addBannerLifecycleObserver(this);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        mAdapter1 = new HomeAdapter1(R.layout.item_shoop2);
        rView1.setLayoutManager(manager);
        rView1.addItemDecoration(new SpaceItemDecoration(SizeUtils.dp2px(10), 2));
        rView1.setAdapter(mAdapter1);

//        rView2.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rView2.setLayoutManager( new GridLayoutManager(getActivity(), 2));
        mAdapter2 = new HomeAdapter2(R.layout.item_shoop2);
        rView2.addItemDecoration(new SpaceItemDecoration(SizeUtils.dp2px(10), 2));
        rView2.setAdapter(mAdapter2);


        showWaitDialog("",false);
        getData();
    }

    public void getData() {
        RxHttp.get(Comments.GET_USER_INFO)
                .asResponse(UserInfoBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 2000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });

        RxHttp.get(Comments.BANNER)
                .asResponseList(BannerBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 3000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 3000);
                });

        RxHttp.get(Comments.HOME_DATA)
                .add("page", page)
                .add("size", size)
                .add("numberData", 2)
                .asResponse(HomeBean.class)
                .doFinally(() -> {
                    if (refreshLayout.isRefreshing()) {
                        refreshLayout.finishRefresh();
                    }
                    if (refreshLayout.isLoading()) {
                        refreshLayout.finishLoadMore();
                    }
                })
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        mTotalPage=model.getData().getTopData().getTotalPages();
                        onJsonDataGetSuccess(model.getData(), 1000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop;
    }



    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 1000) {
            HomeBean bean = (HomeBean) re_data;

            List<HomeBean.BannerListVOBean> blist = bean.getBannerListVO();
            if(blist!=null){
                if (blist.size() > 0) {
                    banner.setAdapter(new BannerImageAdapter<HomeBean.BannerListVOBean>(blist) {
                        @Override
                        public void onBindView(BannerImageHolder holder, HomeBean.BannerListVOBean data, int position, int size) {
                            GlideUtils.loadImage(getActivity(), data.getImagePath(), holder.imageView);
                        }
                    }).setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(Object data, int position) {
                            HomeBean.BannerListVOBean item= (HomeBean.BannerListVOBean) data;
                            if(item.getGoodId()>0){
                                startActivity(new Intent(getActivity(), ShoopDetailActivity.class)
                                        .putExtra("title","商品详情")
                                        .putExtra("goodId", item.getGoodId()));
                            }else{
                                startActivity(new Intent(getActivity(), BaseWebActivity.class)
                                        .putExtra("title","活动详情")
                                        .putExtra("content",item.getBannerDesc()));
                            }
                        }
                    });
                }
            }

            if (bean.getTopData() != null) {
                mAdapter1.setList(bean.getTopData().getContent());
            }

            /*if (bean.getGoodVO() != null) {
                mAdapter2.setList(bean.getGoodVO().getContent());
            }*/
            if (bean.getThingData()!= null && bean.getThingData().getContent().size() > 0) {
                if (page == 0) {
                    mAdapter2.setList(bean.getThingData().getContent());
                } else {
                    mAdapter2.addData(bean.getThingData().getContent());
                }
            } else {
                if (page == 0) {
                    mAdapter2.setList(null);
                }
            }
        }else if(reqcode == 2000)  {
            UserInfoBean user= (UserInfoBean) re_data;
            DataCacheUtil.getInstance(getActivity()).getmSharedPreferences().saveString("randomId", user.getRandomId());
            DataCacheUtil.getInstance(getActivity()).saveUserInfo(user);
        }else if(reqcode == 3000) {

            List<BannerBean> blist = (List<BannerBean>) re_data;
            if(blist!=null){
                if (blist.size() > 0) {
                    banner.setAdapter(new BannerImageAdapter<BannerBean>(blist) {
                        @Override
                        public void onBindView(BannerImageHolder holder, BannerBean data, int position, int size) {
                            GlideUtils.loadImage(getActivity(), data.getImagePath(), holder.imageView);
                        }
                    }).setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(Object data, int position) {
                            BannerBean item= (BannerBean) data;
                            if(item.getGoodId()>0){
                                startActivity(new Intent(getActivity(), ShoopDetailActivity.class)
                                        .putExtra("title","商品详情")
                                        .putExtra("goodId", item.getGoodId()));
                            }else{
                                startActivity(new Intent(getActivity(), BaseWebActivity.class)
                                        .putExtra("title","活动详情")
                                        .putExtra("content",item.getBannerDesc()));
                            }
                        }
                    });;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.menu1) {
            startActivity(new Intent(getActivity(), GoodListActivity.class).putExtra("type",1));
        } else if (v.getId() == R.id.menu2) {
            startActivity(new Intent(getActivity(), GoodListActivity.class).putExtra("type",2));
        } else if (v.getId() == R.id.menu3) {
            startActivity(new Intent(getActivity(), GoodListActivity.class).putExtra("type",3));

        } else if (v.getId() == R.id.menu4) {
            startActivity(new Intent(getActivity(), GoodListActivity.class).putExtra("type",4));

        }else if(v.getId() == R.id.order) {
            startActivity(new Intent(getActivity(), OrderListActivity.class).putExtra("index",0));
        }else if (v.getId() == R.id.tv_home_more) {
            startActivity(new Intent(getActivity(), RankingListActivity.class).putExtra("type",1));
        }else if (v.getId() == R.id.back) {
            getActivity().finish();
        }
    }

    @Override
    protected void initListener() {
        menu1.setOnClickListener(this);
        menu2.setOnClickListener(this);
        menu3.setOnClickListener(this);
        menu4.setOnClickListener(this);
        order.setOnClickListener(this);
        back.setOnClickListener(this);
        mAdapter1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(getActivity(), ShoopDetailActivity.class)
                        .putExtra("name", mAdapter1.getData().get(position).getName())
                        .putExtra("goodId", mAdapter1.getData().get(position).getId()));
            }
        });
        mAdapter2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(getActivity(), ShoopDetailActivity.class)
                        .putExtra("name", mAdapter2.getData().get(position).getName())
                        .putExtra("goodId", mAdapter2.getData().get(position).getId()));
            }
        });
        llHomeSearch.setOnClickListener(this);
        tvHomeMore.setOnClickListener(this);

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData();
            }
        });
    }



    public void refreshData() {
        page = 0;
        getData();
    }

    protected void loadMoreData() {
        if (page < mTotalPage-1) {
            page++;
            getData();
        } else {
            ToastUtils.showShort("暂无更多数据！");
            if (refreshLayout.isLoading()) {
                refreshLayout.finishLoadMore();
            }
        }
    }




}
