package com.example.temple.adapter;

import android.util.Log;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.activity.order.LogisticsActivity;

import java.text.SimpleDateFormat;


public class LogisticsAdapter extends BaseQuickAdapter<LogisticsActivity.LogisticsBean, BaseViewHolder> {

    private SimpleDateFormat sdf;
    private LinearLayout.LayoutParams params;
    public LogisticsAdapter(int layoutResId) {
        super(layoutResId);
//        sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogisticsActivity.LogisticsBean item) {
        helper.setText(R.id.tv_text,item.getStatus())
                .setText(R.id.tv_time, item.getTime());
//                .setText(R.id.tv_time, TimeUtils.millis2String(item.getTime(),sdf));
        int position=getItemPosition(item);
        if(position==getData().size()-1){
            Log.i("test","last:"+position);

            helper.setGone(R.id.iv_bottom,false);
            helper.setGone(R.id.empty,true);
        }else{
            helper.setGone(R.id.iv_bottom,true);
            if(position==0){
                helper.setGone(R.id.empty,false);
            }else{
                helper.setGone(R.id.empty,true);
            }
        }
//        ((ImageView)helper.getView(R.id.iv_top)).setLayoutParams(params);

    }


}
