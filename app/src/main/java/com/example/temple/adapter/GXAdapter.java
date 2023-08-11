package com.example.temple.adapter;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.GongXianBean;

import java.text.SimpleDateFormat;


public class GXAdapter extends BaseQuickAdapter<GongXianBean.ContentBean, BaseViewHolder> {

    private SimpleDateFormat sdf;

    public GXAdapter(int layoutResId) {
        super(layoutResId);
        sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    @Override
    protected void convert(BaseViewHolder helper, GongXianBean.ContentBean item) {

        helper.setText(R.id.tv_title,item.getRemark()+"")
                .setText(R.id.tv_time, TimeUtils.millis2String(Long.parseLong(item.getCreatedAt()),sdf))
                .setText(R.id.tv_cost, (item.getPaymentType().equals("REVENUE")?"+":"-") +item.getAmount());
        if(item.getCoinType().equals("BALANCE")) {
            helper.setImageDrawable(R.id.iv_type,getContext().getDrawable(R.mipmap.balance));
        }else {
            helper.setImageDrawable(R.id.iv_type,getContext().getDrawable(R.mipmap.dou));
        }
    }

}
