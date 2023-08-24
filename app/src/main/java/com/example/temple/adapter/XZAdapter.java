package com.example.temple.adapter;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.DirectUserBean;
import com.example.temple.utils.BaseUtils;

import java.text.SimpleDateFormat;


public class XZAdapter extends BaseQuickAdapter<DirectUserBean, BaseViewHolder> {

    private SimpleDateFormat sdf;

    public XZAdapter(int layoutResId) {
        super(layoutResId);
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    @Override
    protected void convert(BaseViewHolder helper, DirectUserBean item) {

        helper.setText(R.id.tv_nikename, item.getNickName())
                .setText(R.id.tv_time, "注册时间：" + TimeUtils.millis2String(Long.parseLong(item.getCreatedAt()), sdf))
                .setText(R.id.tv_phone, BaseUtils.phoneEncode(item.getPhone()))
                .setText(R.id.tv_gongde, item.getSumDou())
                .setText(R.id.tv_xiuxin, item.getSumMoney());

        if (item.getUserLevelName().equals("ZERO")) {
            helper.setBackgroundResource(R.id.user_level, R.drawable.yk_bg);
            helper.setText(R.id.user_level, "游客");

        } else if (item.getUserLevelName().equals("ONE")) {
            helper.setBackgroundResource(R.id.user_level, R.drawable.xz_bg);
            helper.setText(R.id.user_level, "信众");
        } else if (item.getUserLevelName().equals("TWO")) {
            helper.setBackgroundResource(R.id.user_level, R.drawable.tz_bg);
            helper.setText(R.id.user_level, "堂主");
        }

    }

}
