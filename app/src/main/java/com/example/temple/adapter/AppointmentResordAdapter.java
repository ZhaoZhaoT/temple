package com.example.temple.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.teacher.MyTeacherListBean;


public class AppointmentResordAdapter extends BaseQuickAdapter<MyTeacherListBean.ContentBean, BaseViewHolder> {

    public AppointmentResordAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyTeacherListBean.ContentBean item) {
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_op_name = helper.getView(R.id.tv_op_name);
        TextView tv_time = helper.getView(R.id.tv_time);
        TextView tv_state = helper.getView(R.id.tv_state);
        TextView tv_teacher_name = helper.getView(R.id.tv_teacher_name);

        tv_name.setText("预约" + item.getTeacherVO().getTeacherName());
        tv_teacher_name.setText(item.getTeacherVO().getTeacherName());

        tv_op_name.setText(item.getName());

        tv_time.setText(
                TimeUtils.millis2String(item.getStartAt(), "yyyy-MM-dd") + " " +
                        TimeUtils.millis2String(item.getStartAt(), "HH:mm") + "~" +
                        TimeUtils.millis2String(item.getEndAt(), "HH:mm"));

        if (item.getStatus().equals("FIVE")) {//已取消
            tv_state.setText("已取消");
            tv_state.setTextColor(Color.parseColor("#999999"));
        } else if (item.getStatus().equals("FOUR")) {//已完成
            tv_state.setText("已完成");
            tv_state.setTextColor(Color.parseColor("#333333"));
        } else if (item.getStatus().equals("TWO")) {//已预约
            tv_state.setText("已预约");
            tv_state.setTextColor(Color.parseColor("#6D1B13"));
        }


    }

}
