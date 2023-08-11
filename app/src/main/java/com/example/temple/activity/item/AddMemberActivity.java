package com.example.temple.activity.item;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.dialog.WhetherNotPopup;
import com.lxj.xpopup.XPopup;

import butterknife.BindView;

/**
 * 族谱 添加成员
 */
public class AddMemberActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.tv_save)
    TextView tv_save;

    @BindView(R.id.layout_be_alive)
    LinearLayout layout_be_alive;//是否健在
    @BindView(R.id.tv_alive)
    TextView tv_alive;

    @BindView(R.id.lin_address)
    LinearLayout lin_address;//健在 需要填地址
    @BindView(R.id.lin_no_blive)
    LinearLayout lin_no_blive;//不健在 填去世日期

    WhetherNotPopup whetherNotPopup;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_genealogy_add_member;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        name = getIntent().getStringExtra("name");

    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        layout_be_alive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.layout_be_alive) {
            if (whetherNotPopup == null) {
                whetherNotPopup = new WhetherNotPopup(this, new WhetherNotPopup.onClickDone() {
                    @Override
                    public void selectData(String name) {
                        tv_alive.setText(name);
                        if ("是".equals(name)) {
                            lin_address.setVisibility(View.VISIBLE);
                            lin_no_blive.setVisibility(View.GONE);
                        } else {
                            lin_address.setVisibility(View.GONE);
                            lin_no_blive.setVisibility(View.VISIBLE);
                        }
                    }

                });
            }
            new XPopup.Builder(this)
                    .dismissOnBackPressed(true)
                    .dismissOnTouchOutside(true)
                    .asCustom(whetherNotPopup)
                    .show();

        } else if (v.getId() == R.id.tv_save) {
            finish();
        }
    }


}