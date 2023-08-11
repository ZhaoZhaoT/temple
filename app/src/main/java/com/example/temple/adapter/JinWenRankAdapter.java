package com.example.temple.adapter;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.haode.RankListBean;
import com.example.temple.utils.GlideUtils;


public class JinWenRankAdapter extends BaseQuickAdapter<RankListBean.RankBean, BaseViewHolder> {

    String unit;

    public JinWenRankAdapter(int layoutResId, String unit) {
        super(layoutResId);
        this.unit = unit;
    }

    @Override
    protected void convert(BaseViewHolder helper, RankListBean.RankBean item) {
        ImageView head = helper.getView(R.id.head);
        GlideUtils.loadCircleImage(getContext(), item.getAvatar_url(), R.mipmap.default_head, head);
        TextView tv_nikename = helper.getView(R.id.tv_nikename);
        tv_nikename.setText(item.getNick_name());
        TextView tv_copy_count = helper.getView(R.id.tv_copy_count);
        tv_copy_count.setText(item.getCounts() + unit);
        int position = getItemPosition(item);
        if (position == 0) {
            tv_copy_count.setTextColor(Color.parseColor("#FA3C00"));
        } else if (position == 1) {
            tv_copy_count.setTextColor(Color.parseColor("#8BC343"));
        } else if (position == 2) {
            tv_copy_count.setTextColor(Color.parseColor("#68B0F2"));
        } else {
            tv_copy_count.setTextColor(Color.parseColor("#886860"));
        }


    }

}
