package com.example.temple.activity.user;


import static com.example.temple.network.Comments.TO_ADDRESS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.AddressListAdapter;
import com.example.temple.bean.address.NewAddressBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.SpaceDecoration;
import com.rxjava.rxlife.RxLife;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rxhttp.wrapper.param.RxHttp;

public class AddressListActivity extends BaseTitleActivity {

    @BindView(R.id.rView)
    RecyclerView rView;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private AddressListAdapter mAdapter;

    private int page=0,size=10,mTotalPage;
    private int mType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_list;
    }

    @Override
    protected void initView() {
        baseTitle.setText("收货地址");
        grayTitle();
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setHasFixedSize(true);
        mAdapter = new AddressListAdapter(R.layout.item_address);
        rView.setAdapter(mAdapter);
        rView.addItemDecoration(new SpaceDecoration(0, 0, 0, SizeUtils.dp2px(10)));
        mAdapter.setEmptyView(R.layout.empty_view);

        mType=getIntent().getIntExtra("type",0);
       /* View empty=View.inflate(mContext,R.layout.empty_view,null);
        ImageView iv=empty.findViewById(R.id.iv_load_empty);
        iv.setImageResource(R.mipmap.lo);*/
        getList();
    }


    private int index;
    @Override
    protected void initListener() {
        super.initListener();
        refreshLayout.setOnLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {}
        });
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setHeaderTriggerRate(0.3f);

        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                if(view.getId()==R.id.tv_edit){
                    startActivityForResult(new Intent(mContext, AddAddressActivity.class)
                            .putExtra("data", mAdapter.getData().get(position)),TO_ADDRESS);
                }else if(view.getId()==R.id.tv_del){
                    index=position;
                    delAddress(mAdapter.getData().get(position).getId()+"");
                }

            }
        });
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(mType==1){
                    NewAddressBean.ContentBean bean=mAdapter.getData().get(position);
                    Intent intent=new Intent();
                    intent.putExtra("bean",bean);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(mContext, AddAddressActivity.class),TO_ADDRESS);
            }
        });
    }

    protected void getList() {
        RxHttp.get(Comments.ADDRESS_LIST)
                .add("page",page)
                .add("size",size)
                .asResponse(NewAddressBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        mTotalPage=model.getData().getTotalPages();
                        onJsonDataGetSuccess(model.getData(), 1000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }

    protected void delAddress(String id) {
        RxHttp.postJson(Comments.DEL_ADDRESS)
                .add("id",id)
                .asResponse(String.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 2000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }

    public void refreshData() {
        //分页情况用于刷新数据
        page = 0;
        getList();
    }

    protected void loadMoreData() {
        //分页情况用于加载更多数据
        if (page < mTotalPage-1) {
            page++;
            getList();
        } else {
            ToastUtils.showShort("暂无更多数据！");
            if (refreshLayout.isLoading()) {
                refreshLayout.finishLoadMore();
            }
        }
    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 1000) {
            NewAddressBean bean= (NewAddressBean) re_data;

            List<NewAddressBean.ContentBean> list = bean.getContent();
            if (list != null && list.size() > 0) {
                if (page == 0) {
                    mAdapter.setList(list);
                } else {
                    mAdapter.addData(list);
                }
            } else {
                mAdapter.setList(null);
            }
        }else if(reqcode==2000){
//            mAdapter.notifyItemRemoved(index);
            List<NewAddressBean.ContentBean> mlist=mAdapter.getData();
            mlist.remove(index);
            mAdapter.setList(mlist);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            refreshData();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}