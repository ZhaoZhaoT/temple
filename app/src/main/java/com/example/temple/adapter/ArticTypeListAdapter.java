
package com.example.temple.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;


public class ArticTypeListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public ArticTypeListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        LinearLayout lin_art = helper.getView(R.id.lin_art);
        ImageView iv_pic = helper.getView(R.id.iv_pic);
        LinearLayout lin_video = helper.getView(R.id.lin_video);

        if (item.equals("文章")) {
            lin_art.setVisibility(View.VISIBLE);
            iv_pic.setVisibility(View.VISIBLE);
            lin_video.setVisibility(View.GONE);
        } else if (item.equals("视频")) {
            lin_art.setVisibility(View.GONE);
            iv_pic.setVisibility(View.VISIBLE);
            lin_video.setVisibility(View.VISIBLE);

        } else if (item.equals("文章2")) {
            lin_art.setVisibility(View.VISIBLE);
            iv_pic.setVisibility(View.GONE);
            lin_video.setVisibility(View.GONE);

        } else if (item.equals("文章")) {
            lin_art.setVisibility(View.VISIBLE);
            iv_pic.setVisibility(View.VISIBLE);
            lin_video.setVisibility(View.GONE);
        }


    }

}
