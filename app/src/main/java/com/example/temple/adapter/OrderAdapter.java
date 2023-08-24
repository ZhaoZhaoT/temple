package com.example.temple.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.OrderBean;
import com.example.temple.utils.GlideUtils;


public class OrderAdapter extends BaseQuickAdapter<OrderBean.ContentBean, BaseViewHolder> {

    public OrderAdapter(int layoutResId) {
        super(layoutResId);
        addChildClickViewIds(R.id.tv_btn1,R.id.tv_btn2);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean.ContentBean item) {
        helper.setText(R.id.tv_product_tile,item.getGoodName())
                .setText(R.id.tv_choose_number,"X"+item.getOrderItemVO().getAmount())
                .setText(R.id.tv_size,"规格："+item.getOrderItemVO().getSpecVO().getSpecName())
                .setText(R.id.tv_price,"¥"+item.getPayMoney())
                .setText(R.id.tv_zoom,item.getOrderItemVO().getTypeEnum().equals("ONE")?"特供区":item.getOrderItemVO().getTypeEnum().equals("TWO")?"文创区":
                        item.getOrderItemVO().getTypeEnum().equals("THREE")?"互换区":"典藏区");
        helper.setText(R.id.tv_shoop_name,"共"+item.getOrderItemVO().getAmount()+"件商品，实付：");
        helper.setText(R.id.all_money,"¥" + item.getPayMoney());

        GlideUtils.loadRoundCircleImage(getContext(), item.getOrderItemVO().getSpecVO().getSpecCover(),helper.getView(R.id.iv_picture),4);

        if(item.getStatus().equals("ONE")){
            helper.setText(R.id.tv_btn1,"取消订单").setText(R.id.tv_order_state,"待付款")
                    .setText(R.id.tv_btn2,"去支付").setVisible(R.id.tv_btn1,true).setVisible(R.id.tv_btn2,true);
            helper.setGone(R.id.ll_btn,false);

        }else if(item.getStatus().equals("TWO")){
            helper.setText(R.id.tv_order_state,"待发货").setGone(R.id.ll_btn,true);
        }else if(item.getStatus().equals("THREE")){
            helper .setText(R.id.tv_btn1,"确认收货") .setText(R.id.tv_btn2,"查看物流").setText(R.id.tv_order_state,"待收货")
                    .setVisible(R.id.tv_btn1,true).setVisible(R.id.tv_btn2,true);
            helper.setGone(R.id.ll_btn,false);

        }else if(item.getStatus().equals("FOUR")){
            helper  .setText(R.id.tv_btn2,"评价").setText(R.id.tv_order_state,"待评价")
                    .setVisible(R.id.tv_btn1,false).setVisible(R.id.tv_btn2,true);
            helper.setGone(R.id.ll_btn,true);

        }else if(item.getStatus().equals("FIVE")){
//            helper.setVisible(R.id.tv_btn1,false).setVisible(R.id.tv_btn2,false);
            helper .setText(R.id.tv_order_state,"交易关闭").setGone(R.id.ll_btn,true);
        }
    }

}
