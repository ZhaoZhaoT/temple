package com.example.temple.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.BaseApplication;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.activity.shop.ShoopDetailActivity;
import com.example.temple.adapter.ShoopAdapter;
import com.example.temple.bean.ShoopBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.AppManager;
import com.example.temple.utils.EventBusUtils;
import com.example.temple.view.SpaceItemDecoration;
import com.rxjava.rxlife.RxLife;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class PayResultActivity extends BaseTitleActivity implements View.OnClickListener{

    @BindView(R.id.iv_left_back)
    ImageView iv_left_back;
    @BindView(R.id.tv_exchange_title)
    TextView tv_exchange_title;
    @BindView(R.id.iv_check)
    ImageView iv_check;
    @BindView(R.id.tv_state_name)
    TextView tv_state_name;
    @BindView(R.id.tv_shifu)
    TextView tv_shifu;
    @BindView(R.id.tv_pay_money)
    TextView tv_pay_money;
    @BindView(R.id.rl_content)
    RelativeLayout rl_content;
    @BindView(R.id.tv_btn1)
    TextView tv_btn1;
    @BindView(R.id.tv_btn2)
    TextView tv_btn2;
    @BindView(R.id.rView)
    RecyclerView rView;
    private ShoopAdapter mAdapter;
    private int page = 0, size = 10, mTotalPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_result;
    }

    @Override
    protected void initView() {
        rlTop.setVisibility(View.GONE);
        statusBar.setVisibility(View.GONE);

        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        rView.setLayoutManager(manager);
        mAdapter = new ShoopAdapter(R.layout.item_shoop2);
        rView.addItemDecoration(new SpaceItemDecoration(SizeUtils.dp2px(10), 2));
        rView.setAdapter(mAdapter);
        mAdapter.setEmptyView(R.layout.empty_view);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(mContext, ShoopDetailActivity.class)
                        .putExtra("name", mAdapter.getData().get(position).getName())
                        .putExtra("goodId", mAdapter.getData().get(position).getId()));
            }
        });
        getList();
        tv_pay_money.setText(BaseApplication.payMoney+"");
        AppManager.getAppManager().finishActivity(ConfirmOrderActivity.class,OrderDetailActivity.class);

        EventBusUtils.post(Comments.ON_ORDER);

    }

    @Override
    protected void initListener() {
        super.initListener();
        tv_btn1.setOnClickListener(this);
        tv_btn2.setOnClickListener(this);
        iv_left_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ClickUtils.expandClickArea(iv_left_back, SizeUtils.dp2px(30));

    }

    public void getList() {
        showWaitDialog("",false);
        RxHttp.get(Comments.GET_RECOMMED)
                .add("page",page)
                .add("size",size)
                .asResponse(ShoopBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
//                    mTotalPage=model.getData().getTotalPages();
                    onJsonDataGetSuccess(model.getData(), 2000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if(reqcode==2000){
            ShoopBean bean= (ShoopBean) re_data;
            if (bean.getContent()!= null && bean.getContent().size() > 0) {
                if (page == 0) {
                    mAdapter.setList(bean.getContent());
                } else {
                    mAdapter.addData(bean.getContent());
                }
            } else {
                if (page == 0) {
                    mAdapter.setEmptyView(R.layout.empty_view);
                    mAdapter.setList(null);
                }
            }
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_btn1:
                AppManager.getAppManager().finishOtherActivity();
                break;
            case R.id.tv_btn2:
                AppManager.getAppManager().finishActivity(OrderListActivity.class);
                startActivity(new Intent(mContext, OrderListActivity.class).putExtra("index",0));
                finish();
                break;
        }
    }
}