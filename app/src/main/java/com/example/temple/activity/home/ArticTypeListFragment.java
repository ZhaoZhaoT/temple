
package com.example.temple.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.fiveblessings.ArticleDetailNewActivity;
import com.example.temple.activity.fiveblessings.ContentDetailActivity;
import com.example.temple.adapter.ArticTypeListAdapter;
import com.example.temple.bean.WuPianInfoBean;
import com.example.temple.fragment.BaseFragment;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.rxjava.rxlife.RxLife;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

/**
 * 文章类型列表  视频  文章
 */
public class ArticTypeListFragment extends BaseFragment {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rView)
    RecyclerView mRView;
    private ArticTypeListAdapter mAdapter;
    String infoType, contentType;
    private int page = 0, size = 10, mTotalPage;

    public ArticTypeListFragment(String contentType, String infoType) {
        this.contentType = contentType;
        this.infoType = infoType;
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
        refreshLayout.setHeaderTriggerRate(0.3f);
        mRView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRView.setHasFixedSize(true);
        mAdapter = new ArticTypeListAdapter(R.layout.item_artic_type);
        mRView.setAdapter(mAdapter);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.empty_view, null);
        TextView tv_load_empty = view.findViewById(R.id.tv_load_empty);
        tv_load_empty.setText("暂无数据");
        mAdapter.setEmptyView(view);

        getListInfo();

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                if (mAdapter.getData().get(position).getContentType().equals("ONE")) {//视频
                    startActivity(new Intent(getActivity(), ContentDetailActivity.class)
                            .putExtra("title", mAdapter.getData().get(position).getTitle())
                            .putExtra("authorName", mAdapter.getData().get(position).getAuthorName())
                            .putExtra("content", mAdapter.getData().get(position).getContent())
                            .putExtra("time", sdf.format( mAdapter.getData().get(position).getCreatedAt()))
                            .putExtra("video", mAdapter.getData().get(position).getVideoUrl())
                            .putExtra("coverimg", mAdapter.getData().get(position).getCoverImg()));


                } else if (mAdapter.getData().get(position).getContentType().equals("TWO")) {//文章

                    startActivity(new Intent(getActivity(), ArticleDetailNewActivity.class)
                            .putExtra("title", mAdapter.getData().get(position).getTitle())
                            .putExtra("authorName", mAdapter.getData().get(position).getAuthorName())
                            .putExtra("content", mAdapter.getData().get(position).getContent())
                            .putExtra("time", sdf.format( mAdapter.getData().get(position).getCreatedAt())));
                }

            }
        });

    }


    public void getListInfo() {
        RxHttp.get(Comments.CONSULT_INFO)
                .add("contentType", contentType)
                .add("type", infoType)
                .add("page", page)
                .add("page", page)
                .add("size", size)
                .asResponse(WuPianInfoBean.class)
                .doFinally(() -> {
                    //请求结束，当前在主线程回调
                    if (refreshLayout.isRefreshing()) {
                        refreshLayout.finishRefresh();
                    }
                    if (refreshLayout.isLoading()) {
                        refreshLayout.finishLoadMore();
                    }
                })
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    mTotalPage = model.getData().getTotalPages();
                    onJsonDataGetSuccess(model.getData(), 2000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        WuPianInfoBean bean = (WuPianInfoBean) re_data;
        if (bean.getContent() != null && bean.getContent().size() > 0) {
            if (page == 0) {
                mAdapter.setList(bean.getContent());
            } else {
                mAdapter.addData(bean.getContent());
            }
        } else {
            if (page == 0) {
                mAdapter.setList(null);
            }
        }

    }


    protected void loadMoreData() {
        //分页情况用于加载更多数据
        if (page < mTotalPage - 1) {
            page++;
            getListInfo();
        } else {
            ToastUtils.showShort("暂无更多数据！");
            if (refreshLayout.isLoading()) {
                refreshLayout.finishLoadMore();
            }
        }
    }

    public void refreshData() {
        //分页情况用于刷新数据
        page = 0;
        getListInfo();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_artic_type;
    }

    @Override
    protected void initListener() {
    }


}
