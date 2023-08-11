package com.example.temple.adapter;

import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.haode.SongJinListBean;


public class SongJinWenListAdapter extends BaseQuickAdapter<SongJinListBean.ContentBean, BaseViewHolder> {

    public SongJinWenListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SongJinListBean.ContentBean item) {
        TextView name = helper.findView(R.id.name);
        name.setText(item.getTitle());
        TextView word_count = helper.findView(R.id.word_count);
        word_count.setText("经文时长: " + item.getVideoTime());
        TextView copy_count = helper.findView(R.id.copy_count);
        copy_count.setText("诵经次数: " +  (TextUtils.isEmpty(item.getCount())?0:item.getCount()));


    }

}
