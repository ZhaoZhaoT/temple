
package com.example.temple.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.fiveblessings.ArticleDetailActivity;
import com.example.temple.activity.fiveblessings.ContentDetailActivity;
import com.example.temple.adapter.ArticTypeListAdapter;
import com.example.temple.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 文章类型列表  视频  文章
 */
public class ArticTypeListFragment extends BaseFragment {

    @BindView(R.id.rView)
    RecyclerView mRView;
    private ArticTypeListAdapter mAdapter;
    int type;

    public ArticTypeListFragment(int type) {
        this.type = type;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRView.setHasFixedSize(true);
        mAdapter = new ArticTypeListAdapter(R.layout.item_artic_type);
        mRView.setAdapter(mAdapter);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.empty_view, null);
        TextView tv_load_empty = view.findViewById(R.id.tv_load_empty);
        tv_load_empty.setText("暂无数据");
        ImageView iv_load_empty = view.findViewById(R.id.iv_load_empty);
        iv_load_empty.setImageResource(R.mipmap.haode_botton);

        mAdapter.setEmptyView(R.layout.empty_view);

        //假数据
        ArrayList<String> data = new ArrayList<String>();
        if (type == 0) {
            data.add("文章");
            data.add("视频");
            data.add("文章2");
            data.add("文章");
        } else if (type == 1) {
            data.add("视频");
            data.add("视频");
            data.add("视频");
            data.add("视频");
        } else if (type == 2) {
            data.add("文章");
            data.add("文章");
            data.add("文章2");
            data.add("文章");
        }

        mAdapter.setList(data);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (data.get(position).equals("视频")) {//视频
                    startActivity(new Intent(getActivity(), ContentDetailActivity.class));
                } else if (data.get(position).equals("文章") || data.get(position).equals("文章2")) {//文章
                    startActivity(new Intent(getActivity(), ArticleDetailActivity.class));
                }

            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_artic_type;
    }

    @Override
    protected void initListener() {
    }


}
