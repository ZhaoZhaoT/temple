package com.example.temple.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.JinWenListAdapter;
import com.example.temple.adapter.JinWenRankAdapter;
import com.example.temple.bean.haode.ChaoJinListBean;
import com.example.temple.bean.haode.RankListBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.EventBusUtils;
import com.example.temple.utils.GlideUtils;
import com.example.temple.utils.ListUtils;
import com.rxjava.rxlife.RxLife;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class ChaojingActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.lin_my)
    RelativeLayout lin_my;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.tv_nikename)
    TextView tv_nikename;
    @BindView(R.id.tv_copy_count)
    TextView tv_copy_count;

    @BindView(R.id.lin_rank)
    LinearLayout lin_rank;

    @BindView(R.id.view_list)
    RecyclerView view_list;
    JinWenRankAdapter jinWenRankAdapter;

    @BindView(R.id.view_list_two)
    RecyclerView view_list_two;
    JinWenListAdapter jinWenListAdapter;

    private int page = 0, size = 10, mTotalPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jinwen_rank;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        EventBusUtils.register(this);
        mTitle.setText("抄经");

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

        view_list.setLayoutManager(new LinearLayoutManager(this));
        view_list.setHasFixedSize(true);
        jinWenRankAdapter = new JinWenRankAdapter(R.layout.item_progress_rank, "部");
        view_list.setAdapter(jinWenRankAdapter);


        view_list_two.setLayoutManager(new LinearLayoutManager(this));
        view_list_two.setHasFixedSize(true);
        jinWenListAdapter = new JinWenListAdapter(R.layout.item_jinwen_list);
        view_list_two.setAdapter(jinWenListAdapter);
        View view = LayoutInflater.from(this).inflate(R.layout.empty_view, null);
        TextView tv_load_empty = view.findViewById(R.id.tv_load_empty);
        tv_load_empty.setText("暂无数据");
        ImageView iv_load_empty = view.findViewById(R.id.iv_load_empty);
        iv_load_empty.setImageResource(R.mipmap.no_data);
        jinWenListAdapter.setEmptyView(view);
        jinWenListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                getCheckHaoder(jinWenListAdapter.getData().get(position).getId(),
                        jinWenListAdapter.getData().get(position).getContent(),
                        jinWenListAdapter.getData().get(position).getTitle());//检验金文是否存在

            }
        });

        //抄经 列表数据
        getHaoderCopyList();
        getHaoderCopyRankList();
    }


    protected void loadMoreData() {
        //分页情况用于加载更多数据
        if (page < mTotalPage - 1) {
            page++;
            getHaoderCopyList();
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
        getHaoderCopyList();
        getHaoderCopyRankList();
    }


    public void getHaoderCopyRankList() {
        RxHttp.get(Comments.HAODE_RANK)
                .add("type", "ZERO")
                .asResponse(RankListBean.class)
                .doFinally(() -> {

                })
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 3000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }


    public void getHaoderCopyList() {
        RxHttp.get(Comments.HAODE_COPY)
                .add("page", page)
                .add("size", size)
                .asResponse(ChaoJinListBean.class)
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


    public void getCheckHaoder(String id, String content, String title) {
        RxHttp.get(Comments.HAODE_CHECK)
                .add("id", id)
                .asResponse(ChaoJinListBean.class)
                .doFinally(() -> {

                })
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    startActivity(new Intent(ChaojingActivity.this, CopyJingshuActivity.class)
                            .putExtra("content", content)
                            .putExtra("title", title)
                            .putExtra("id", id));
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 2000) {
            ChaoJinListBean bean = (ChaoJinListBean) re_data;
            if (bean.getContent() != null && bean.getContent().size() > 0) {
                if (page == 0) {
                    jinWenListAdapter.setList(bean.getContent());
                } else {
                    jinWenListAdapter.addData(bean.getContent());
                }
            } else {
                if (page == 0) {
                    jinWenListAdapter.setList(null);
                }
            }
        } else if (reqcode == 3000) {
            RankListBean bean = (RankListBean) re_data;
            jinWenRankAdapter.setList(bean.getRanking());
            if (!ListUtils.isEmpty(bean.getRanking())) {
                lin_rank.setVisibility(View.VISIBLE);
            } else {
                lin_rank.setVisibility(View.GONE);
            }

            if (bean.getMyRanking() != null) {

                if (bean.getRanking().size() > 3) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.height = SizeUtils.dp2px((39 * 4)) + SizeUtils.dp2px((30));
                    lin_rank.setLayoutParams(params);
                } else {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.height = SizeUtils.dp2px((39 * (bean.getRanking().size() + 1))) + SizeUtils.dp2px((30));
                    lin_rank.setLayoutParams(params);
                }

                lin_my.setVisibility(View.VISIBLE);
                GlideUtils.loadCircleImage(mContext, bean.getMyRanking().getAvatar_url(), R.mipmap.default_head, head);
                tv_nikename.setText(bean.getMyRanking().getNick_name() + "(我)");
                tv_copy_count.setText(bean.getMyRanking().getCounts() + "部");
            } else {
                lin_my.setVisibility(View.GONE);

                if (bean.getRanking().size() > 3) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.height = SizeUtils.dp2px((39 * 3)) + SizeUtils.dp2px((30));
                    lin_rank.setLayoutParams(params);
                } else {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.height = SizeUtils.dp2px((39 * bean.getRanking().size())) + SizeUtils.dp2px((30));
                    lin_rank.setLayoutParams(params);
                }

            }

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(Object event) {
        if (event.equals(Comments.HAODE_SAVE_SUCCESS)) {
            refreshData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }

    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        }
    }


}