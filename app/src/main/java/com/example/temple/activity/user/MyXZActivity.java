package com.example.temple.activity.user;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.SizeUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseListActivity;
import com.example.temple.adapter.XZAdapter;
import com.example.temple.bean.DirectUserBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.SpaceDecoration;
import com.rxjava.rxlife.RxLife;

import java.util.List;

import rxhttp.wrapper.param.RxHttp;

public class MyXZActivity extends BaseListActivity {

    private XZAdapter mAdapter;
//    private int page=0,size=10;

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return super.getLayoutInflater();
    }

    @Override
    protected void initView() {
        baseTitle.setText("我的道友");

        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setHasFixedSize(true);
        mAdapter = new XZAdapter(R.layout.item_xz_list);
        rView.setAdapter(mAdapter);
        rView.addItemDecoration(new SpaceDecoration(0,0,0, SizeUtils.dp2px(10)));
        mAdapter.setEmptyView(R.layout.empty_view);

    }


    @Override
    protected void getList() {
        super.getList();
        RxHttp.get(Comments.GET_MY_DIRECT_USER)
                .asResponseList(DirectUserBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 1000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if(reqcode==1000){
            List<DirectUserBean> blist = (List<DirectUserBean>) re_data;
            if(blist!=null){
                if (blist!= null && blist.size() > 0) {
                    if (mPage == 0) {
                        mAdapter.setList(blist);
                    } else {
                        mAdapter.addData(blist);
                    }
                } else {
                    mAdapter.setList(null);
                }
            }
        }
    }
}