
package com.example.temple.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;


public class ZhuziBaijiaDirectoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ZhuziBaijiaDirectoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv_text = helper.getView(R.id.tv_text);
        tv_text.setText(item);


    }

}
