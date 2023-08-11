package com.example.temple.activity.fiveblessings.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.fiveblessings.ArticleDetailActivity;
import com.example.temple.activity.fiveblessings.ContentDetailActivity;
import com.example.temple.activity.fiveblessings.PhotoDetailActivity;
import com.example.temple.adapter.SurnameMemorailiaListAdapter;
import com.example.temple.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class SurnameMemorabiliaFragment extends BaseFragment {

    @BindView(R.id.rView)
    RecyclerView mRView;
    private SurnameMemorailiaListAdapter mAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRView.setHasFixedSize(true);
        mAdapter = new SurnameMemorailiaListAdapter(R.layout.item_surname_memorailia);
        mRView.setAdapter(mAdapter);

//        View view = LayoutInflater.from(this).inflate(R.layout.empty_view, null);
//        TextView tv_load_empty = view.findViewById(R.id.tv_load_empty);
//        tv_load_empty.setText("暂未开放");
//        ImageView iv_load_empty = view.findViewById(R.id.iv_load_empty);
//        iv_load_empty.setImageResource(R.mipmap.haode_botton);
//
//        mAdapter.setEmptyView(R.layout.empty_view);

        //假数据
        ArrayList<String> data = new ArrayList<String>();
        data.add("koko");
        data.add("ko1ko");
        data.add("ko2ko");
        data.add("koko");
        mAdapter.setList(data);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (data.get(position).equals("koko")) {//相册
                    startActivity(new Intent(getActivity(), PhotoDetailActivity.class));
                } else if (data.get(position).equals("ko1ko")) {//视频
                    startActivity(new Intent(getActivity(), ContentDetailActivity.class));
                } else if (data.get(position).equals("ko2ko")) {//文章
                    startActivity(new Intent(getActivity(), ArticleDetailActivity.class));
                }

            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wedding_banquet;
    }

    @Override
    protected void initListener() {
    }


}
