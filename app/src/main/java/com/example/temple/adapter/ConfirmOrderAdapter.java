package com.example.temple.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.CarBean;
import com.example.temple.utils.GlideUtils;


public class ConfirmOrderAdapter extends BaseQuickAdapter<CarBean.ContentBean, BaseViewHolder> {

    public ConfirmOrderAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarBean.ContentBean item) {
        helper.setText(R.id.tv_product_tile,item.getGoodsVO().getName())
                .setText(R.id.tv_choose_number,"X"+item.getAmount())
                .setText(R.id.tv_price,"¥"+item.getGoodsVO().getSpecVO().get(0).getPrice());

        if(item.getGoodsVO().getSpecVO().size()>0){
            helper.setText(R.id.tv_size,"规格: "+item.getGoodsVO().getSpecVO().get(0).getSpecName());
        }
        if(item.getGoodsVO().getBusinessFirmVO()!=null){
            helper.setText(R.id.tv_shoop_name,item.getGoodsVO().getBusinessFirmVO().getFirmName());
        }
        GlideUtils.loadRoundCircleImage(getContext(), item.getGoodsVO().getSpecVO().get(0).getSpecCover(),helper.getView(R.id.iv_picture),4);
    }



}
