package com.example.temple.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.GenealogyDetailsAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 族谱详情
 */
public class GenealogyDetailsActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.line_edit)
    RelativeLayout line_edit;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.rView)
    RecyclerView mRView;
    GenealogyDetailsAdapter genealogyDetailsAdapter;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_genealogy_details;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        name = getIntent().getStringExtra("name");
        tv_name.setText(name);

        mRView.setLayoutManager(new GridLayoutManager(this, 2));
        mRView.setHasFixedSize(true);
        genealogyDetailsAdapter = new GenealogyDetailsAdapter(mContext,R.layout.item_genealogy_details_list);
        mRView.setAdapter(genealogyDetailsAdapter);

        //假数据
        ArrayList<String> data = new ArrayList<String>();
        data.add("添加成员");
        data.add("父亲");
        data.add("母亲");
        data.add("女儿");
        data.add("奶奶");
        genealogyDetailsAdapter.setList(data);

        genealogyDetailsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(data.get(position).equals("添加成员")){
                    //去添加
                    startActivity(new Intent(mContext, AddMemberActivity.class));
                }else{
                    //详情
                    startActivity(new Intent(mContext, MemberInformationActivity.class));
                }

            }
        });

    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        line_edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.line_edit) {
            startActivity(new Intent(GenealogyDetailsActivity.this, GenealogyEditDetailsActivity.class));
        }

    }


}