
package com.example.temple.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;


public class GoodEndingDetailsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public GoodEndingDetailsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView iv_head = helper.getView(R.id.iv_head);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_time = helper.getView(R.id.tv_time);
        TextView tv_content = helper.getView(R.id.tv_content);

        tv_name.setText(item);
        tv_content.setText("创建时间:2023年7月28日");
        if ("屈原".equals(item)) {
            iv_head.setImageResource(R.mipmap.quyuan);
            tv_time.setText("约公元前340年—公元前278年");
        } else if ("霍去病".equals(item)) {
            tv_time.setText("公元前140年-前117年");
        } else if ("岳飞".equals(item)) {
            tv_time.setText("1103年3月24日～1142年1月27日");
            iv_head.setImageResource(R.mipmap.yuefei);
        } else if ("文天祥".equals(item)) {
            tv_time.setText("1236年6月6日－1283年1月9日");
        } else if ("戚继光".equals(item)) {
            tv_time.setText("1528年11月12日－1588年1月5日");
            iv_head.setImageResource(R.mipmap.qijigaung);
        } else if ("王船山".equals(item)) {
            tv_time.setText("1619-1692");
        } else if ("林则徐".equals(item)) {
            tv_time.setText("1785年8月30日 [52] －1850年11月22日");
            iv_head.setImageResource(R.mipmap.linzexu);
        } else if ("左宗棠".equals(item)) {
            tv_time.setText("1812年-1885年");
            iv_head.setImageResource(R.mipmap.zuozongtang);
        } else if ("孙中山".equals(item)) {
            tv_time.setText("1866年11月12日-1925年3月12日");
        } else if ("邱少云".equals(item)) {
            tv_time.setText("1926年7月12日 [12] -1952年10月12日");
            iv_head.setImageResource(R.mipmap.qiushaoyun);
        } else if ("董存瑞".equals(item)) {
            tv_time.setText("1929年10月15日～1948年5月25日");
            iv_head.setImageResource(R.mipmap.dongcunrui);
        } else if ("黄继光".equals(item)) {
            tv_time.setText("1931年1月8日～1952年10月20日");
            iv_head.setImageResource(R.mipmap.huangjiguang);

        }

    }

}
