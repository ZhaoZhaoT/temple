package com.example.temple.activity.livebroadcast;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.adapter.LiveBroadcastAdapter;
import com.example.temple.fragment.BaseFragment;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;

public class LiveFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_list)
    RecyclerView mViewList;

    private LiveBroadcastAdapter mAdapter;
    private int mPage = 0, mSize = 10, mTotalPage;

    String index;

    public LiveFragment(String index) {
        this.index = index;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

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
        mViewList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mViewList.setHasFixedSize(true);
        mAdapter = new LiveBroadcastAdapter(R.layout.item_live_broadcast);
        mViewList.setAdapter(mAdapter);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.empty_view, null);
        TextView tv_load_empty = view.findViewById(R.id.tv_load_empty);
        tv_load_empty.setText("暂无数据");
        ImageView iv_load_empty = view.findViewById(R.id.iv_load_empty);
        iv_load_empty.setImageResource(R.mipmap.haode_botton);

        mAdapter.setEmptyView(view);

        //假数据
        ArrayList<String> data = new ArrayList<String>();
        if (index.equals("0")) {
            data.add("https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/guoxue.mp4");
            data.add("https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/fengshui.mp4");
        } else if (index.equals("1")) {
            data.add("https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/fengshui.mp4");
        } else if (index.equals("2")) {
            data.add("https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/guoxue.mp4");
        }
        mAdapter.setList(data);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {

                startActivity(new Intent(getActivity(), LiveBroadcastDetailActivity.class)
                        .putExtra("title", mAdapter.getData().get(position).contains("guoxue")?"国学讲堂第一期兴趣班":"风水玄学第一期兴趣班")
                        .putExtra("time", "2023-02-23")
                        .putExtra("video", mAdapter.getData().get(position))
                        .putExtra("coverimg", "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/images/2023/08/01/a793d309b6014c7d279b00c6d5c8a9f.jpg")

                        .putExtra("content", "一命二运三风水，四积阴德五读经，六名七相八敬神，九交贵人十养生"));
//                if(mAdapter.getData().get(position).equals("趙")){
//                    title="赵(zhào)";
//                    content="【起源】出自嬴姓，嬴姓的出现是因为舜帝（姚姓，后代以姚为姓）赐姓给他的女婿伯益（颛顼帝孙）为“嬴”，并把自己的姚姓的女儿嫁给他。虽然使用嬴姓的祖先是伯益，但赵姓的具体始祖是造父。";
//                }
            }
        });

    }


    public void getList() {
//        RxHttp.get(Comments.HAODE_COPY)
//                .add("page", mPage)
//                .add("size", mSize)
//                .asResponse(ChaoJinListBean.class)
//                .doFinally(() -> {
        //请求结束，当前在主线程回调
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
        if (refreshLayout.isLoading()) {
            refreshLayout.finishLoadMore();
        }
//                })
//                .to(RxLife.toMain(this))
//                .subscribe(model -> {
//                    mTotalPage = model.getData().getTotalPages();
//                    onJsonDataGetSuccess(model.getData(), 2000);
//                }, (OnError) error -> {
//                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
//                });
    }


//    @Override
//    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
//        super.onJsonDataGetSuccess(re_data, reqcode);
//        if (reqcode == 2000) {
//            ChaoJinListBean bean = (ChaoJinListBean) re_data;
//            if (bean.getContent() != null && bean.getContent().size() > 0) {
//                if (mPage == 0) {
//                    jinWenListAdapter.setList(bean.getContent());
//                } else {
//                    jinWenListAdapter.addData(bean.getContent());
//                }
//            } else {
//                if (mPage == 0) {
//                    jinWenListAdapter.setList(null);
//                }
//            }
//
//        }
//    }


    protected void refreshData() {
        //分页情况用于刷新数据
        mPage = 0;
        getList();
    }

    protected void loadMoreData() {
        //分页情况用于加载更多数据
        if (mPage < mTotalPage - 1) {
            mPage++;
            getList();
        } else {
            ToastUtils.showShort("没有更多数据了");

            if (refreshLayout.isLoading()) {
                refreshLayout.finishLoadMore();
            }
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

}

