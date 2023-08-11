
package com.example.temple.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;


public class HonorHallRankAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public HonorHallRankAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView iv_head = helper.getView(R.id.iv_head);
        if (item.equals("ko1ko")) {
            iv_head.setBackgroundResource(R.mipmap.rank_one);
            iv_head.setText("");
        } else if (item.equals("ko2ko")) {
            iv_head.setBackgroundResource(R.mipmap.rank_two);
            iv_head.setText("");
        } else if (item.equals("ko3ko")) {
            iv_head.setBackgroundResource(R.mipmap.rank_there);
            iv_head.setText("");
        } else if (item.equals("ko4ko")) {
            iv_head.setBackgroundResource(0);
            iv_head.setText("4");
        } else if (item.equals("ko5ko")) {
            iv_head.setBackgroundResource(0);
            iv_head.setText("5");
        }

    }

}
