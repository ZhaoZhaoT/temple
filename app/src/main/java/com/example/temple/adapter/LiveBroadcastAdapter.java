package com.example.temple.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.utils.GlideUtils;


public class LiveBroadcastAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public LiveBroadcastAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView video_view = helper.getView(R.id.video_view);

        TextView tv_time = helper.getView(R.id.tv_time);
        if(item.contains("guoxue")){
            tv_time.setText("国学讲堂第一期兴趣班");
        }else{
            tv_time.setText("风水玄学第一期兴趣班");
        }

        // 设置视频封面图片
        GlideUtils.loadRoundCircleSeatImage(getContext(), "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/images/2023/08/01/a793d309b6014c7d279b00c6d5c8a9f.jpg",
                video_view, R.mipmap.no_empty_one, 5);

    }

}
