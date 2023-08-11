package com.example.temple.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.MyCauseCalcBean;


public class CauseCalcAdapter extends BaseQuickAdapter<MyCauseCalcBean.ContentBean, BaseViewHolder> {


    public CauseCalcAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCauseCalcBean.ContentBean item) {
        helper.setText(R.id.tv_time,item.getToDayDate())
                .setText(R.id.tv_size, item.getToDayMoney());
    }

}
