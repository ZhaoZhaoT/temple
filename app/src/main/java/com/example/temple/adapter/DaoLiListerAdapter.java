
package com.example.temple.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;


public class DaoLiListerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public DaoLiListerAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv_title = helper.findView(R.id.tv_title);
        TextView tv_content = helper.findView(R.id.tv_content);

        tv_content.setText(item);
        if(getItemPosition(item)==0){//今日所宜
            tv_title.setBackgroundResource(R.mipmap.bg_red_two);
            tv_title.setText("今日所宜");
        }else if(getItemPosition(item)==1){//今日所忌
            tv_title.setBackgroundResource(R.mipmap.bg_green_two);
            tv_title.setText("今日所忌");
        }else if(getItemPosition(item)==2){//今日八字
            tv_title.setBackgroundResource(R.mipmap.bg_red_two);
            tv_title.setText("今日八字");
        }else if(getItemPosition(item)==3){//今日冲煞
            tv_title.setBackgroundResource(R.mipmap.bg_red_two);
            tv_title.setText("今日冲煞");
        }else if(getItemPosition(item)==4){//吉祥方位
            tv_title.setBackgroundResource(R.mipmap.bg_red_two);
            tv_title.setText("吉祥方位");
        }else if(getItemPosition(item)==5){//吉神宜趋
            tv_title.setBackgroundResource(R.mipmap.bg_green_two);
            tv_title.setText("吉神宜趋");
        }else if(getItemPosition(item)==6){//今日五行
            tv_title.setBackgroundResource(R.mipmap.bg_red_two);
            tv_title.setText("今日五行");
        }


    }

}
