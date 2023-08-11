package com.example.temple.activity.user;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.SizeUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseListActivity;
import com.example.temple.adapter.TransDetailAdapter;
import com.example.temple.bean.TransDetailBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.SpaceDecoration;
import com.rxjava.rxlife.RxLife;

import rxhttp.wrapper.param.RxHttp;

public class TransDetailActivity extends BaseListActivity {

    private TransDetailAdapter mAdapter;
//    private int page=0,size=10;

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return super.getLayoutInflater();
    }

    @Override
    protected void initView() {
        baseTitle.setText("明细");

        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setHasFixedSize(true);
        mAdapter = new TransDetailAdapter(R.layout.item_trans_detail);
        rView.setAdapter(mAdapter);
        rView.addItemDecoration(new SpaceDecoration(0,0,0, SizeUtils.dp2px(10)));
        mAdapter.setEmptyView(R.layout.empty_view);

    }


    @Override
    protected void getList() {
        super.getList();
        RxHttp.get(Comments.GET_MY_WITHDRAW)
                .add("page",mPage)
                .add("size",10)
                .asResponse(TransDetailBean.class)
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

    @Override
    protected void onResume() {
        getList();
        super.onResume();
    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if(reqcode==1000){
            TransDetailBean bean = (TransDetailBean)re_data;
            if (bean.getContent()!= null && bean.getContent().size() > 0) {
                if (mPage == 0) {
                    mAdapter.setList(bean.getContent());
                } else {
                    mAdapter.addData(bean.getContent());
                }
            } else {
                mAdapter.setList(null);
            }
        }
    }
}