
package com.example.temple.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;


public class GoodEndingAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public GoodEndingAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView iv_pic= helper.getView(R.id.iv_pic);
        if ("屈原".equals(item)) {
            iv_pic.setImageResource(R.mipmap.quyuan);
        } else if ("霍去病".equals(item)) {

        } else if ("岳飞".equals(item)) {
            iv_pic.setImageResource(R.mipmap.yuefei);

        } else if ("文天祥".equals(item)) {
        } else if ("戚继光".equals(item)) {
            iv_pic.setImageResource(R.mipmap.qijigaung);

        } else if ("王船山".equals(item)) {
        } else if ("林则徐".equals(item)) {
            iv_pic.setImageResource(R.mipmap.linzexu);

        } else if ("左宗棠".equals(item)) {
            iv_pic.setImageResource(R.mipmap.zuozongtang);

        } else if ("孙中山".equals(item)) {
        } else if ("邱少云".equals(item)) {
            iv_pic.setImageResource(R.mipmap.qiushaoyun);

        } else if ("董存瑞".equals(item)) {
            iv_pic.setImageResource(R.mipmap.dongcunrui);

        } else if ("黄继光".equals(item)) {
            iv_pic.setImageResource(R.mipmap.huangjiguang);

        }

    }

}
