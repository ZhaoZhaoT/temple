package com.example.temple.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.HomeBean;
import com.example.temple.utils.GlideUtils;


public class HomeAdapter1 extends BaseQuickAdapter<HomeBean.GoodRankingVOBean.ContentBean, BaseViewHolder> {

    public HomeAdapter1(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.GoodRankingVOBean.ContentBean item) {
        helper.setText(R.id.tv_product_tile,item.getName())
                .setText(R.id.tv_sale_count,"已售"+item.getSales())
                .setText(R.id.tv_new_price,"¥"+item.getPrice());
//                .setText(R.id.tv_old_price,"¥"+item.getPrice());
//                .setText(R.id.tv_order_time,"付款时间："+item.getAddTime());
//                .setText(R.id.tv_order_state,)

        GlideUtils.loadRoundCircleImage(getContext(),item.getCoverImg(),helper.getView(R.id.iv_product),8);
      /*  TextView old=(TextView) helper.getView(R.id.tv_old_price);
        old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //中划线，会有锯齿
        old.getPaint().setAntiAlias(true);*/
    }

}
