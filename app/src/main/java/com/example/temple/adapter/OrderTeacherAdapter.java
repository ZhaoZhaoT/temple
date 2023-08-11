
package com.example.temple.adapter;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.teacher.TeacherListBean;
import com.example.temple.utils.GlideUtils;


public class OrderTeacherAdapter extends BaseQuickAdapter<TeacherListBean.ContentBean, BaseViewHolder> {


    public OrderTeacherAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeacherListBean.ContentBean item) {
        ImageView iv_head=helper.getView(R.id.iv_head);

        GlideUtils.loadRoundCircleSeatImage(getContext(), item.getTeacherImg(), iv_head,R.mipmap.no_empty_one, 40);

        TextView tv_nikename=helper.getView(R.id.tv_nikename);
        tv_nikename.setText(item.getTeacherName());

        TextView tv_content=helper.getView(R.id.tv_content);
        tv_content.setText(item.getTeacherRemark());

        TextView tv_price=helper.getView(R.id.tv_price);
        tv_price.setText("¥"+item.getTeacherPrice()+"/次");

        TextView tv_number=helper.getView(R.id.tv_number);
        tv_number.setText("预约: "+item.getTeacherCount()+"次");

        TextView tv_mark=helper.getView(R.id.tv_mark);
        if(item.getTeacherMark()!=0){
            tv_mark.setBackgroundResource(R.drawable.red_fillet_corner);
            tv_mark.setText("评分 "+item.getTeacherMark()+"分");
            tv_mark.setTextColor(Color.parseColor("#FFFFFFFF"));
        }else{
            tv_mark.setBackgroundResource(R.drawable.red_fillet_corner_hem_width);
            tv_mark.setText("暂无评分");
            tv_mark.setTextColor(Color.parseColor("#6D1B13"));
        }


    }

}
