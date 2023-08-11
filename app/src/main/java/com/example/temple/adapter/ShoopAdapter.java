package com.example.temple.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.ShoopBean;
import com.example.temple.utils.GlideUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class ShoopAdapter extends BaseQuickAdapter<ShoopBean.ContentBean, BaseViewHolder> {

    public ShoopAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoopBean.ContentBean item) {
        helper.setText(R.id.tv_product_tile,item.getName())
                .setText(R.id.tv_sale_count,"已售"+item.getSales())
                .setText(R.id.tv_new_price,"¥"+item.getPrice());
//                .setText(R.id.tv_old_price,"¥"+item.getSpecVO().get(0).getOrgPrice());
//                .setText(R.id.tv_order_time,"付款时间："+item.getAddTime());
//                .setText(R.id.tv_order_state,)

        GlideUtils.loadRoundCircleImage(getContext(), item.getCoverImg(),helper.getView(R.id.iv_product),8, RoundedCornersTransformation.CornerType.TOP);

    }

}
