package com.example.temple.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.ShoopDetailBean;


public class SizeAdapter extends BaseQuickAdapter<ShoopDetailBean.SpecVOBean, BaseViewHolder> {

    public SizeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoopDetailBean.SpecVOBean item) {
        helper.setText(R.id.tv_type_name,item.getSpecName());

        if(item.isCheck()){
            helper.setBackgroundResource(R.id.tv_type_name,R.drawable.type_checked);
            helper.setTextColorRes(R.id.tv_type_name,R.color.white);
        }else{
            helper.setBackgroundResource(R.id.tv_type_name,R.drawable.type_normal);
            helper.setTextColorRes(R.id.tv_type_name,R.color.text_gray);
        }
    }

}
