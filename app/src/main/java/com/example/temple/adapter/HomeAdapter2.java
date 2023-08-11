package com.example.temple.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.HomeBean;
import com.example.temple.utils.GlideUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class HomeAdapter2 extends BaseQuickAdapter<HomeBean.GoodVOBean.ContentBean, BaseViewHolder> {

    public HomeAdapter2(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.GoodVOBean.ContentBean item) {
        helper.setText(R.id.tv_product_tile,item.getName())
                .setText(R.id.tv_sale_count,"已售"+item.getSales())

                .setText(R.id.tv_new_price,"¥"+item.getPrice());
//                .setText(R.id.tv_old_price,"¥"+item.getPrice());
//                .setText(R.id.tv_order_time,"付款时间："+item.getAddTime());
//                .setText(R.id.tv_order_state,)
        ImageView image=helper.getView(R.id.iv_product);
        ViewGroup.LayoutParams lp = image.getLayoutParams();
        if(getItemPosition(item)==0){
            lp.height= SizeUtils.dp2px(130);
        }else{
            lp.height= SizeUtils.dp2px(167);
        }
        image.setLayoutParams(lp);
        GlideUtils.loadRoundCircleImage(getContext(), item.getCoverImg(),image,8, RoundedCornersTransformation.CornerType.TOP);

    }

}
