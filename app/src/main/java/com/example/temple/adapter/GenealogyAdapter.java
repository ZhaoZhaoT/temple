
package com.example.temple.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;


public class GenealogyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public GenealogyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv_name = helper.getView(R.id.tv_name);
        tv_name.setText(item);

    }

}
