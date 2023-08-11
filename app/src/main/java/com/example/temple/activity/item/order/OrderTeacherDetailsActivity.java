
package com.example.temple.activity.item.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.utils.GlideUtils;

import butterknife.BindView;

/**
 * 预约老师 详情页
 */
public class OrderTeacherDetailsActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_remark)
    TextView tv_remark;
    @BindView(R.id.tv_mark)
    TextView tv_mark;

    @BindView(R.id.tv_click)
    TextView tv_click;
    String id,teacherimg,teacherremark,teacheraddress,teachername;
    Integer teachercount;
    Double teacherprice;
    float teachermark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_teacher_details;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        teacherimg=getIntent().getStringExtra("teacherimg");
        teacherremark=getIntent().getStringExtra("teacherremark");
        teacheraddress=getIntent().getStringExtra("teacheraddress");
        teachername=getIntent().getStringExtra("teachername");
        teachercount=getIntent().getIntExtra("teachercount",0);
        teacherprice=getIntent().getDoubleExtra("teacherprice",0);
        teachermark=getIntent().getFloatExtra("teachermark",0);

        GlideUtils.loadRoundCircleSeatImage(this, teacherimg, iv_head,R.mipmap.no_empty_one, 40);
        tv_price.setText("¥"+teacherprice+"/次");
        tv_number.setText(teachercount+"");
        tv_address.setText(teacheraddress);
        tv_remark.setText(teacherremark);
        tv_title.setText(teachername);
        tv_mark.setText(teachermark+"分");

    }


    @Override
    protected void initListener() {
        super.initListener();

        iv_left.setOnClickListener(this);
        tv_click.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_left) {
            finish();
        }else if(v.getId() == R.id.tv_click) {
            startActivity(new Intent(mContext, ReservationInformationActivity.class)
                    .putExtra("id",getIntent().getStringExtra("id"))
                    .putExtra("price",teacherprice)
                    .putExtra("teachername",teachername)
                    .putExtra("address",teacheraddress));

        }
    }


}