
package com.example.temple.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.WuPianInfoBean;
import com.example.temple.utils.GlideUtils;

import java.text.SimpleDateFormat;


public class ArticTypeListAdapter extends BaseQuickAdapter<WuPianInfoBean.ContentBean, BaseViewHolder> {


    public ArticTypeListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WuPianInfoBean.ContentBean item) {
        LinearLayout lin_art = helper.getView(R.id.lin_art);
        ImageView iv_pic = helper.getView(R.id.iv_pic);
        LinearLayout lin_video = helper.getView(R.id.lin_video);
        TextView tv_time = helper.getView(R.id.tv_time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tv_time.setText(sdf.format(item.getCreatedAt()));

        TextView tv_title = helper.getView(R.id.tv_title);
        tv_title.setText(item.getTitle());

        if (item.getContentType().equals("ONE")) {//视频
            lin_art.setVisibility(View.GONE);
            iv_pic.setVisibility(View.VISIBLE);
            lin_video.setVisibility(View.VISIBLE);
            ImageView video_view = helper.getView(R.id.video_view);
            GlideUtils.loadRoundCircleSeatImage(getContext(), item.getCoverImg(), video_view, R.mipmap.no_empty_one,8);

        } else if (item.getContentType().equals("TWO")) {//文章
            lin_art.setVisibility(View.VISIBLE);

            lin_video.setVisibility(View.GONE);

            if (!TextUtils.isEmpty(item.getCoverImg())) {
                iv_pic.setVisibility(View.VISIBLE);
                GlideUtils.loadRoundCircleSeatImage(getContext(), item.getCoverImg(), iv_pic,R.mipmap.no_empty_one, 8);
            } else {
                iv_pic.setVisibility(View.GONE);
            }


        }


    }

}
