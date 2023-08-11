package com.example.temple.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.GenealogyAdapter;
import com.example.temple.dialog.CreateFamilyPopup;
import com.lxj.xpopup.XPopup;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 传家宝 族谱
 */
public class GenealogyActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.line_creat)
    RelativeLayout line_creat;

    @BindView(R.id.rView)
    RecyclerView mRView;
    private GenealogyAdapter mAdapter;
    CreateFamilyPopup createFamilyPopup;

    //假数据
    ArrayList<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_genealogy;
    }

    @Override
    protected void initView() {
        baseTitleGone();

        mRView.setLayoutManager(new GridLayoutManager(this, 2));
        mRView.setHasFixedSize(true);
        mAdapter = new GenealogyAdapter(R.layout.item_genealogy_list);
        mRView.setAdapter(mAdapter);

        View view = LayoutInflater.from(this).inflate(R.layout.empty_view, null);
        TextView tv_load_empty = view.findViewById(R.id.tv_load_empty);
        tv_load_empty.setText("开始建立族谱吧");
        ImageView iv_load_empty = view.findViewById(R.id.iv_load_empty);
        iv_load_empty.setImageResource(R.mipmap.icon_no_framily);

        mAdapter.setEmptyView(R.layout.empty_view);

        //假数据
        data.add("我家");
        data.add("大伯家");
        data.add("二伯家");
        mAdapter.setList(data);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(mContext, GenealogyDetailsActivity.class).putExtra("name",data.get(position)));
            }
        });

    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        line_creat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        }else if(v.getId() == R.id.line_creat){
            //这个是正确的
            if (createFamilyPopup == null) {
                createFamilyPopup = new CreateFamilyPopup(this, new CreateFamilyPopup.onClickDone() {
                    @Override
                    public void selectData(String surname) {
//                        tv_all.setText(surname);
                        mAdapter.addData(surname);
                        mAdapter.notifyDataSetChanged();
                    }


                });
            }
            new XPopup.Builder(this)
                    .dismissOnBackPressed(true)
                    .dismissOnTouchOutside(true)
                    .asCustom(createFamilyPopup)
                    .show();
        }

    }


}