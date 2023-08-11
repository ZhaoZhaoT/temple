
package com.example.temple.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;


public class WeddingBanquetListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public WeddingBanquetListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        TextView tv_mark=helper.getView(R.id.tv_mark);
        if(item.equals("koko")){
            tv_mark.setText("相册");
        }else if(item.equals("ko1ko")){
            tv_mark.setText("视频");
        }else if(item.equals("ko2ko")){
            tv_mark.setText("文章");
        }


    }

}
