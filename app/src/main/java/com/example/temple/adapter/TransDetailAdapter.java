package com.example.temple.adapter;

import android.view.View;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.TransDetailBean;
import com.example.temple.utils.BigDecimalUtils;

import java.text.SimpleDateFormat;


public class TransDetailAdapter extends BaseQuickAdapter<TransDetailBean.ContentBean, BaseViewHolder> {

    private SimpleDateFormat sdf;

    public TransDetailAdapter(int layoutResId) {
        super(layoutResId);
        sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    @Override
    protected void convert(BaseViewHolder helper, TransDetailBean.ContentBean item) {

        helper.setText(R.id.tv_shuliang, BigDecimalUtils.stripTrailingZeros(item.getAmount()))
                .setText(R.id.tv_zhanghao,item.getBankNo())
                .setText(R.id.tv_time, TimeUtils.millis2String(Long.parseLong(item.getCreatedAt()),sdf))
                .setText(R.id.tv_pay_money,item.getPayMoney())
                .setText(R.id.tv_server_money,item.getServerMoney())
                .setText(R.id.tv_status,item.getStatus().equals("ONE")?"待审核":(item.getStatus().equals("TWO")?"已通过":"已拒绝"));

        if(item.getStatus().equals("THREE")){
            helper.getView(R.id.tv_reason).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_reason,"原因：" + item.getRemark());
        }else {
            helper.getView(R.id.tv_reason).setVisibility(View.GONE);
        }

    }

}
