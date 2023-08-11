package com.example.temple.activity.shop;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.BaseApplication;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.activity.order.ConfirmOrderActivity;
import com.example.temple.activity.user.AddressListActivity;
import com.example.temple.bean.ShoopDetailBean;
import com.example.temple.bean.UserInfoBean;
import com.example.temple.bean.address.NewAddressBean;
import com.example.temple.dialog.BottomListPopup;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.BaseUtils;
import com.example.temple.utils.DataCacheUtil;
import com.example.temple.utils.GlideUtils;
import com.lxj.xpopup.XPopup;
import com.rxjava.rxlife.RxLife;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class ShoopDetailActivity extends BaseTitleActivity implements View.OnClickListener {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_shoop_name)
    TextView tvShoopName;
    @BindView(R.id.tv_new_price)
    TextView tvNewPrice;
    @BindView(R.id.tv_old_price)
    TextView tvOldPrice;
    @BindView(R.id.tv_sale_count)
    TextView tvSaleCount;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;

    @BindView(R.id.tv_size_name)
    TextView tvSizeName;
    @BindView(R.id.ll_choose_size)
    LinearLayout llChooseSize;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    @BindView(R.id.detail_web)
    WebView detailWeb;

    @BindView(R.id.tv_pic_index)
    TextView tvPicIndex;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.tv_type_name)
    TextView tv_type_name;
    @BindView(R.id.tv_user_address)
    TextView tvUserAddress;
    @BindView(R.id.iv_choose_address)
    ImageView ivChooseAddress;


    private ShoopDetailBean mBean;
    private Map<String,Object> checkMap=new HashMap();
    private NewAddressBean.ContentBean mAddress;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shoop_detail;
    }

    @Override
    protected void initView() {
        baseTitle.setText("商品详情");
        BaseUtils.initWebSetting(detailWeb, 0);
        getDetail();
        //顶部banner
        banner.isAutoLoop(false)
                .addBannerLifecycleObserver(this);

    }

    @Override
    protected void initListener() {
        super.initListener();
        llChooseSize.setOnClickListener(this);
        llAddress.setOnClickListener(this);
        tvBuy.setOnClickListener(this);
        banner.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tvPicIndex.setText(position + 1 + "/" + mBean.getFilesVO().size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void getDetail() {
        RxHttp.get(Comments.GET_DETAIL)
                .add("goodId", getIntent().getIntExtra("goodId", 0))
                .asResponse(ShoopDetailBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 1000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }

    protected void getList() {
        RxHttp.get(Comments.ADDRESS_LIST)
                .add("page", 0)
                .add("size", 2)
                .asResponse(NewAddressBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 4000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 4000);
                });
    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 1000) {
            llBottom.setVisibility(View.VISIBLE);
            mBean = (ShoopDetailBean) re_data;

            List<ShoopDetailBean.FilesVOBean> blist = mBean.getFilesVO();
            if (blist.size() > 0) {
                banner.setAdapter(new BannerImageAdapter<ShoopDetailBean.FilesVOBean>(blist) {//banner轮播图
                    @Override
                    public void onBindView(BannerImageHolder holder, ShoopDetailBean.FilesVOBean data, int position, int size) {
                        GlideUtils.loadImage(mContext, data.getFilePath(), holder.imageView);
                    }
                }).setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        String[] ids=new String[blist.size()];
                        for(int i=0;i<blist.size();i++){
                            ids[i]=blist.get(i).getFilePath();
                        }
                        Intent intent = new Intent(mContext, ImagePreViewActivity.class);
                        intent.putExtra("imgIds", ids);
                        intent.putExtra("curPosition", position);
                        startActivity(intent);
                    }
                });
                tvPicIndex.setText("1/" + blist.size());
                tvPicIndex.setVisibility(View.VISIBLE);
            }
            tvShoopName.setText(mBean.getName());
            if(mBean.getTypeEnum().equals("ONE")){
                tv_type_name.setText("特供区");
            }else if(mBean.getTypeEnum().equals("TWO")){
                tv_type_name.setText("文创区");
            }else if(mBean.getTypeEnum().equals("THREE")){
                tv_type_name.setText("互换区");
            }


            if (mBean.getSpecVO().size() > 0) {
                ShoopDetailBean.SpecVOBean item = mBean.getSpecVO().get(0);
                tvNewPrice.setText("¥" + item.getPrice());
                tvOldPrice.setText("¥" + item.getOrgPrice());
                tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                tvOldPrice.getPaint().setAntiAlias(true);
                tvSaleCount.setText("已售" + mBean.getSales());
                tvSizeName.setText(mBean.getSpecVO().get(0).getSpecName());

                checkMap.put("goodSpecId",mBean.getSpecVO().get(0).getGoodSpecId());

            }
            detailWeb.loadDataWithBaseURL(null, mBean.getDescription(), "text/html", "utf-8", null);
        }else if(reqcode==4000){
            NewAddressBean bean = (NewAddressBean) re_data;
            List<NewAddressBean.ContentBean> list = bean.getContent();
            if (list.size() > 0) {
                mAddress=list.get(0);
                initParams(list.get(0));
            } else {
                if(!TextUtils.isEmpty(BaseApplication.location.getProvince())){
                    initParams2(BaseApplication.location);
                }else{
                    tvUserAddress.setText("请选择收货地址");
                }
//                tvUserAddress.setText(BaseApplication.userAddress);
            }

        }
    }


    private void initParams(NewAddressBean.ContentBean item) {
        checkMap.put("province", item.getProvince());
        checkMap.put("city", item.getCity());
        checkMap.put("area", item.getArea());
        checkMap.put("street", item.getDetail());
        checkMap.put("description", item.getDetail());
        checkMap.put("number",chooseCount);
        checkMap.put("consignee", item.getName());
        checkMap.put("phone", item.getPhone());
        tvUserAddress.setText(item.getAll());
    }

    private void initParams2(NewAddressBean.ContentBean item) {
        checkMap.put("province", item.getProvince());
        checkMap.put("city", item.getCity());
        checkMap.put("area", item.getArea());
        checkMap.put("street", item.getDetail());
        checkMap.put("description", item.getDetail());
        checkMap.put("number",chooseCount);
        UserInfoBean user = DataCacheUtil.getInstance(mContext).getUserInfo();
        if(user!=null){
            checkMap.put("consignee", user.getPhone());
            checkMap.put("phone", user.getPhone());
        }

        tvUserAddress.setText(item.getAll());
    }

    private int chooseCount=1;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ll_choose_size) {
            showChooseDialog(mBean.getSpecVO(), tvShoopName.getText().toString());
        } else if (view.getId() == R.id.ll_address) {
            startActivityForResult(new Intent(mContext, AddressListActivity.class).putExtra("type",1),Comments.TO_ADDRESS);
        }else if (view.getId() == R.id.tv_buy) {
            if(tvUserAddress.getText().toString().equals("请选择收货地址")){
                ToastUtils.showShort("请选择收货地址");
            }else{
                showChooseDialog(mBean.getSpecVO(), tvShoopName.getText().toString());
            }

        }
    }


    private BottomListPopup dialog;
    private ShoopDetailBean.SpecVOBean mCheckSpec;

    public void showChooseDialog(List<ShoopDetailBean.SpecVOBean> mList, String proName) {
        if(dialog==null){
            dialog = new BottomListPopup(this, mList, proName, new BottomListPopup.onClickDone() {
                @Override
                public void comfirm(int buyAmount, ShoopDetailBean.SpecVOBean specVOBean) {
                    chooseCount=buyAmount;
                    mCheckSpec=specVOBean;
                    startActivity(new Intent(mContext, ConfirmOrderActivity.class)
                            .putExtra("address",mAddress)
                            .putExtra("spec", mCheckSpec)
                            .putExtra("buyCount", chooseCount)
                            .putExtra("bean", mBean));
                }

                @Override
                public void choose(String name) {
                    tvSizeName.setText(name);
                }
            });
//            dialog.set
        }

        new XPopup.Builder(this)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(dialog)
                .show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Comments.TO_ADDRESS && data != null) {
                NewAddressBean.ContentBean bean = (NewAddressBean.ContentBean) data.getSerializableExtra("bean");
                mAddress=bean;
                initParams(bean);
            }
        }

    }

}