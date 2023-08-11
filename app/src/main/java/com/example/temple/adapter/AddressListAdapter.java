package com.example.temple.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.bean.address.NewAddressBean;


public class AddressListAdapter extends BaseQuickAdapter<NewAddressBean.ContentBean, BaseViewHolder> {

    public AddressListAdapter(int layoutResId) {
        super(layoutResId);
        addChildClickViewIds(R.id.tv_edit,R.id.tv_del);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewAddressBean.ContentBean item) {
//        mobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        helper.setText(R.id.tv_user,item.getName()+" "+item.getPhone())
//                .setText(R.id.tv_address,item.getAdress())
//                .setText(R.id.tv_title,item.getDetail())
                .setGone(R.id.tv_default,item.getIsDefault().equals("YES")? false:true);

        if(item.getProvince().contains("北京")||item.getProvince().contains("天津")||item.getProvince().contains("上海")||item.getProvince().contains("重庆")){
            helper.setText(R.id.tv_title,item.getCity()+item.getArea()+item.getDetail());
        }else{
            helper.setText(R.id.tv_title,item.getProvince()+item.getCity()+item.getArea()+item.getDetail());
        }
    }



}
