package com.example.temple.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.QiYuanQiangBean;
import com.example.temple.utils.GlideUtils;

import java.text.SimpleDateFormat;


public class QiYuanQiangAdapter extends BaseQuickAdapter<QiYuanQiangBean.ContentBean, BaseViewHolder> {


    public QiYuanQiangAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, QiYuanQiangBean.ContentBean item) {
        TextView tv_nikename = helper.findView(R.id.tv_nikename);
        tv_nikename.setText(item.getNickname());
        TextView tv_money = helper.findView(R.id.tv_money);
        tv_money.setText("¥" + item.getMoney());

        TextView tv_qiyuan = helper.findView(R.id.tv_qiyuan);
        tv_qiyuan.setText(item.getContent());

        TextView tv_time = helper.findView(R.id.tv_time);
        tv_time.setText(new SimpleDateFormat("yyyy-MM-dd").format(
                new java.util.Date(item.getTime())));

        ImageView head = helper.findView(R.id.head);
        GlideUtils.loadCircleImage(getContext(), item.getAvatarUrl(), R.mipmap.default_head, head);

    }

}
