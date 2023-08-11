package com.example.temple.activity.memorialplaza;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.MemorialCeremonyAdapter;
import com.example.temple.dialog.JiDianRecordPopup;
import com.example.temple.dialog.JiNianPopup;
import com.lxj.xpopup.XPopup;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 祭奠
 */
public class MemorialCeremonyActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.lin_flow)
    LinearLayout lin_flow;
    @BindView(R.id.lin_shentai)
    RelativeLayout lin_shentai;

    @BindView(R.id.view_list)
    RecyclerView view_list;
    MemorialCeremonyAdapter memorialCeremonyAdapter;

    @BindView(R.id.tv_jinian)
    TextView tv_jinian;


    JiDianRecordPopup jiDianRecordPopup;

    JiNianPopup jiNianPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_memorial_ceremony;
    }


    @Override
    protected void initView() {
        baseTitleGone();
        view_list.setLayoutManager(new LinearLayoutManager(this));
        view_list.setHasFixedSize(true);
        memorialCeremonyAdapter = new MemorialCeremonyAdapter(R.layout.item_memorial_ceremony_list);
        view_list.setAdapter(memorialCeremonyAdapter);

        //假数据
        ArrayList<String> data2 = new ArrayList<String>();
        data2.add("净心神咒");
        data2.add("净口神咒");
        data2.add("安土地神咒");
        data2.add("净天地神咒");
        data2.add("祝香神咒");
        memorialCeremonyAdapter.setList(data2);

        memorialCeremonyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (jiDianRecordPopup == null) {
                    jiDianRecordPopup = new JiDianRecordPopup(MemorialCeremonyActivity.this);
                }
                new XPopup.Builder(MemorialCeremonyActivity.this)
                        .dismissOnBackPressed(true)
                        .dismissOnTouchOutside(true)
                        .hasShadowBg(false) // 去掉半透明背景
                        .asCustom(jiDianRecordPopup)
                        .show();
            }
        });
    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);

        tv_jinian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jiNianPopup == null) {
                    jiNianPopup = new JiNianPopup(MemorialCeremonyActivity.this);
                }
                new XPopup.Builder(MemorialCeremonyActivity.this)
                        .dismissOnBackPressed(true)
                        .dismissOnTouchOutside(true)
                        .hasShadowBg(false) // 去掉半透明背景
                        .asCustom(jiNianPopup)
                        .show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        }
    }


}