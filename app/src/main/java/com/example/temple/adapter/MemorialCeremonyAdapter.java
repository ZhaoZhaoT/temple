package com.example.temple.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.QiYuanQiangBean;
import com.example.temple.utils.GlideUtils;

import java.text.SimpleDateFormat;


public class MemorialCeremonyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public MemorialCeremonyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }

}
