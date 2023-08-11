package com.example.temple.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.GenealogyEditAdapter;
import com.example.temple.dialog.HintSelectPopup;
import com.example.temple.utils.AppManager;
import com.lxj.xpopup.XPopup;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 族谱详情 操作
 */
public class GenealogyEditDetailsActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.line_delect)
    RelativeLayout line_delect;
    @BindView(R.id.line_finish)
    RelativeLayout line_finish;

    @BindView(R.id.rView)
    RecyclerView mRView;
    private GenealogyEditAdapter mAdapter;
    private HintSelectPopup hintSelectPopup;
    private HintSelectPopup hintSelectPopupTwo;
    private String name;
    //假数据
    private ArrayList<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_genealogy_edit_details;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        name = getIntent().getStringExtra("name");


        mRView.setLayoutManager(new GridLayoutManager(this, 2));
        mRView.setHasFixedSize(true);
        mAdapter = new GenealogyEditAdapter(this, R.layout.item_genealogy_details_list, new GenealogyEditAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                if (hintSelectPopup == null) {
                    hintSelectPopup = new HintSelectPopup(GenealogyEditDetailsActivity.this, "家族成员删除后所有相关数据将无法恢复，您确定要永久删除吗？",
                            "取消","确定",new HintSelectPopup.onClickDone() {
                        @Override
                        public void selectAffrim() {
                            mAdapter.removeAt(position);
                            hintSelectPopup.dismiss();
                        }

                        @Override
                        public void selectCancle() {
                            hintSelectPopup.dismiss();
                        }

                    });
                }
                new XPopup.Builder(GenealogyEditDetailsActivity.this)
                        .dismissOnBackPressed(true)
                        .dismissOnTouchOutside(true)
                        .asCustom(hintSelectPopup)
                        .show();

            }
        });
        mRView.setAdapter(mAdapter);


        //假数据
        data.add("添加成员");
        data.add("父亲");
        data.add("母亲");
        data.add("女儿");
        data.add("奶奶");
        mAdapter.setList(data);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (data.get(position).equals("添加成员")) {
                    //去添加
                    startActivity(new Intent(mContext, AddMemberActivity.class));
                } else {
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
        line_finish.setOnClickListener(this);
        line_delect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.line_delect) {
            if (hintSelectPopupTwo == null) {
                hintSelectPopupTwo = new HintSelectPopup(GenealogyEditDetailsActivity.this, "家族删除后所有相关数据将无法恢复，您确定要永久删除吗？",
                        "取消","确定",new HintSelectPopup.onClickDone() {
                    @Override
                    public void selectAffrim() {
                        hintSelectPopupTwo.dismiss();
                        finish();
                        AppManager.getAppManager().finishActivity(GenealogyDetailsActivity.class);
                    }

                    @Override
                    public void selectCancle() {
                        hintSelectPopupTwo.dismiss();
                    }

                });
            }
            new XPopup.Builder(GenealogyEditDetailsActivity.this)
                    .dismissOnBackPressed(true)
                    .dismissOnTouchOutside(true)
                    .asCustom(hintSelectPopupTwo)
                    .show();

        } else if (v.getId() == R.id.line_finish) {
            finish();
        }

    }


}