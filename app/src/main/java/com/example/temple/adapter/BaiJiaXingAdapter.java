package com.example.temple.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;


public class BaiJiaXingAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public BaiJiaXingAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv_xing_title = helper.getView(R.id.tv_xing_title);//赵(zhào)
        TextView tv_xing = helper.getView(R.id.tv_xing);//趙

        tv_xing.setText(item);
        if(item.equals("趙")){
            tv_xing_title.setText("赵(zhào)");
        }else if(item.equals("錢")){
            tv_xing_title.setText("钱(qián)");
        }else if(item.equals("孫")){
            tv_xing_title.setText("孙(sūn)");
        }else if(item.equals("李")){
            tv_xing_title.setText("李(lǐ)");
        }else if(item.equals("周")){
            tv_xing_title.setText("周(zhōu)");
        }else if(item.equals("吳")){
            tv_xing_title.setText("吴(wú)");
        }else if(item.equals("鄭")){
            tv_xing_title.setText("郑(zhènɡ)");
        }else if(item.equals("王")){
            tv_xing_title.setText("王(wánɡ)");
        }


    }

}
