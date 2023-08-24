package com.example.temple.activity.order;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.BaseApplication;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.base.MyThirdDelegate;
import com.example.temple.base.PayResult;
import com.example.temple.bean.OrderBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.EventBusUtils;
import com.example.temple.utils.GlideUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.rxjava.rxlife.RxLife;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Map;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class OrderDetailActivity extends BaseTitleActivity implements View.OnClickListener {



    @BindView(R.id.layout_back)
    FrameLayout layout_left_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
  /*  @BindView(R.id.tv_order_state)
    TextView tv_order_state;
    @BindView(R.id.tv_order_tips)
    TextView tv_order_tips;*/
   /* @BindView(R.id.rView)
    RecyclerView rView;*/
//    @BindView(R.id.et_remark)
//    TextView et_remark;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_order_money)
    TextView tv_order_money;

    @BindView(R.id.tv_order_no)
    TextView tv_order_no;

    @BindView(R.id.tv_order_date)
    TextView tv_order_date;


    @BindView(R.id.tv_btn1)
    TextView tv_btn1;
    @BindView(R.id.tv_btn2)
    TextView tv_btn2;
    @BindView(R.id.tv_zoom)
    TextView tv_zoom;
    @BindView(R.id.tv_send_address)
    TextView tv_send_address;
    @BindView(R.id.tv_send_time)
    TextView tv_send_time;

    @BindView(R.id.tv_pay_type)
    TextView tv_pay_type;

    @BindView(R.id.tv_pay_time)
    TextView tv_pay_time;



    @BindView(R.id.iv_picture)
    ImageView ivPicture;
    @BindView(R.id.tv_product_tile)
    TextView tvProductTile;
//    @BindView(R.id.tv_size)
//    TextView tvSize;
    @BindView(R.id.tv_choose_number)
    TextView tvChooseNumber;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    @BindView(R.id.tv_pay_money)
    TextView tvPayMoney;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    @BindView(R.id.rl_sendtime)
    RelativeLayout rl_sendtime;

    @BindView(R.id.rl_pay_type)
    RelativeLayout rl_pay_type;
    @BindView(R.id.rl_pay_time)
    RelativeLayout rl_pay_time;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.line6)
    View line6;
    @BindView(R.id.line7)
    View line7;
    @BindView(R.id.copy)
    TextView copy;

    private String idNo;
    private OrderBean.ContentBean detailBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {
        rlTop.setVisibility(View.GONE);
        statusBar.setVisibility(View.GONE);
//        tv_title.setText("订单详情");
        detailBean= (OrderBean.ContentBean) getIntent().getSerializableExtra("data");
        if(detailBean!=null){
            idNo=detailBean.getIdNo();
            initContent(detailBean);
        }else{
            idNo=getIntent().getStringExtra("idNo");
            getDetail();
        }
//        mWxApi = WXAPIFactory.createWXAPI(mContext, Comments.WX_APP_ID, true);
//        mWxApi.registerApp(Comments.WX_APP_ID);
    }

    @Override
    protected void initListener() {
        super.initListener();
        tv_btn1.setOnClickListener(this);
        tv_btn2.setOnClickListener(this);
        layout_left_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                mClipboardManager.setPrimaryClip(ClipData.newPlainText(null, tv_order_no.getText().toString()));
                ToastUtils.showShort("复制成功");
            }
        });
    }

    private void initContent(OrderBean.ContentBean detailBean) {
        if(detailBean !=null){
            tvProductTile.setText(detailBean.getOrderItemVO().getGoodName());
            tvPrice.setText("¥"+ detailBean.getOrderItemVO().getPrice());
//            tvSize.setText("规格: "+ detailBean.getOrderItemVO().getSpecVO().getSpecName());
            tvChooseNumber.setText("X"+ detailBean.getOrderItemVO().getAmount());
            GlideUtils.loadRoundCircleImage(mContext, detailBean.getOrderItemVO().getSpecVO().getSpecCover(),ivPicture,4);



            if(detailBean.getOrderItemVO().getTypeEnum().equals("ONE")){
                tv_zoom.setText("特供区");
            }else if(detailBean.getOrderItemVO().getTypeEnum().equals("TWO")){
                tv_zoom.setText("文创区");
            }else if(detailBean.getOrderItemVO().getTypeEnum().equals("THREE")){
                tv_zoom.setText("互换区");
            }else if(detailBean.getOrderItemVO().getTypeEnum().equals("FOUR")){
                tv_zoom.setText("典藏区");
            }

            tv_send_address.setText(detailBean.getContractAddress());
            tv_name.setText(detailBean.getContractName() + detailBean.getContractPhone());
//            tvSendPhone.setText(detailBean.getContractPhone());

            tv_order_money.setText("¥"+ detailBean.getSumMoney());
            tvPayMoney.setText("¥"+ detailBean.getPayMoney());

            tv_order_no.setText(detailBean.getIdNo());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            tv_order_date.setText(sdf.format(detailBean.getOrderItemVO().getCreatedAt()));

            if(detailBean.getUpdatedAt() == null || detailBean.getUpdatedAt().isEmpty()) {
                line6.setVisibility(View.GONE);
                line7.setVisibility(View.GONE);
                rl_pay_type.setVisibility(View.GONE);
                rl_pay_time.setVisibility(View.GONE);
            }else {
                if(detailBean.getPayType().equals("WX")){
                    tv_pay_type.setText("微信支付");
                }else if(detailBean.getPayType().equals("ZFB")){
                    tv_pay_type.setText("支付宝支付");
                }
                tv_pay_time.setText(sdf.format(Long.parseLong(detailBean.getUpdatedAt())));
            }

            initState(sdf);
        }
    }

    private void initState(SimpleDateFormat sdf) {
        if(detailBean.getStatus().equals("ONE")){
            tv_btn1.setText("取消订单");
            tv_btn2.setText("去支付");
            llBottom.setVisibility(View.VISIBLE);
            tv_title.setText("待付款");

            rl_sendtime.setVisibility(View.GONE);
            line5.setVisibility(View.GONE);

        }else if(detailBean.getStatus().equals("TWO")){
            llBottom.setVisibility(View.GONE);
            tv_title.setText("待发货");

            rl_sendtime.setVisibility(View.GONE);
            line5.setVisibility(View.GONE);

        }else if(detailBean.getStatus().equals("THREE")){
//                tv_btn1.setVisibility(View.GONE);

            tv_btn1.setText("确认收货");
            tv_btn2.setText("查看物流");
            llBottom.setVisibility(View.VISIBLE);
            tv_title.setText("待收货");

            rl_sendtime.setVisibility(View.VISIBLE);
            line5.setVisibility(View.VISIBLE);
            if(detailBean.getOrderExpressVO()!=null){
                tv_send_time.setText(sdf.format(detailBean.getOrderExpressVO().getCreatedAt()));
            }
        }else if(detailBean.getStatus().equals("FOUR")){
//                tv_btn2.setText("评价");
            llBottom.setVisibility(View.GONE);
            tv_title.setText("待评价");

            rl_sendtime.setVisibility(View.VISIBLE);
            line5.setVisibility(View.VISIBLE);

            if(detailBean.getOrderExpressVO()!=null){
                tv_send_time.setText(sdf.format(detailBean.getOrderExpressVO().getCreatedAt()));
            }

        }else if(detailBean.getStatus().equals("FIVE")){
//                helper.setVisible(R.id.tv_btn1,false).setVisible(R.id.tv_btn2,false);
            llBottom.setVisibility(View.GONE);
            tv_title.setText("已取消");

            rl_sendtime.setVisibility(View.GONE);
            line5.setVisibility(View.GONE);
        }
    }


    public void getDetail() {
        showWaitDialog("",true);
        RxHttp.get(Comments.ORDER_DETAIL)
                .add("idNO",idNo)
                .asResponse(OrderBean.ContentBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 1000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }

    public void cancelOrder() {
        showWaitDialog("",true);
        RxHttp.get(Comments.CANCEL_ORDER)
                .add("idNO",idNo)
                .asResponse(String.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 2000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }


    private String mPayType;
    protected void buyNow(OrderBean.ContentBean bean) {
        showWaitDialog("",false);
        BaseApplication.payMoney=bean.getOrderItemVO().getPrice();

        if(bean.getPayType().equals("WX")){
            mPayType="WX";
        }else if(bean.getPayType().equals("ZFB")){
            mPayType="ZFB";
        }else if(bean.getPayType().equals("WX_MINI")){
            mPayType="WX_MINI";
        }

        RxHttp.postJson(Comments.TAKE_ORDER)
//                .add("addressId", addressId)
                .add("buyNumber", bean.getOrderItemVO().getAmount())
                .add("goodId",bean.getId())
                .add("goodSpecId",bean.getOrderItemVO().getSpecVO().getId())
                .add("remark",bean.getRemark())
                .add("payType",mPayType)
                .add("idNo",bean.getIdNo())
                .asResponse(String.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 4000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 4000);
                });
    }


    public void confirmOrder(String idNo) {
        showWaitDialog("",true);
        RxHttp.get(Comments.CONFIRM_LOGISSTICS)
                .add("idNO",idNo)
                .asResponse(String.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 3000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 3000);
                });
    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if(reqcode==2000){
            tv_title.setText("已取消");
            tv_btn1.setVisibility(View.GONE);
            tv_btn2.setVisibility(View.GONE);
            EventBusUtils.post(Comments.ON_ORDER);

        }else if(reqcode==1000){
            detailBean= (OrderBean.ContentBean) re_data;
            initContent(detailBean);
        }else if(reqcode==4000){
            if(mPayType.equals("WX")){
                startWxPay((String) re_data);
            }else if(mPayType.equals("ZFB")){
                startAlipay((String) re_data);
            }else if(mPayType.equals("WX_MINI")){
//                BaseUtils.startWXMini(this,(String) re_data);
            }
        }else if(reqcode==3000){
            getDetail();
        }else if(reqcode==5000){
            buyNow(detailBean);
        }
    }

    @Override
    public void onClick(View view) {
        TextView textView= (TextView) view;
        if(view.getId()==R.id.tv_btn1){
            if(textView.getText().toString().equals("取消订单")){
                showClearDialog();
            }else if(textView.getText().toString().contains("确认收货")){
                confirmOrder(detailBean.getIdNo());
            }
        }else if(view.getId()==R.id.tv_btn2){
            if(textView.getText().toString().contains("支付")){
                buyNow(detailBean);

            }else if(textView.getText().toString().contains("查看物流")){
                if(detailBean.getOrderExpressVO()!=null){
                    startActivity(new Intent(mContext,
                            LogisticsActivity.class).putExtra("no",detailBean.getOrderExpressVO().getExpressNo()));
                }
            }
        }
    }

    private void showClearDialog() {
        new XPopup.Builder(this)
                .dismissOnBackPressed(true)
                .dismissOnTouchOutside(true)
                .asConfirm("提示", "你确定取消订单",
                        "取消", "确定",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                cancelOrder();
                            }
                        }, null, false).show();
    }


    public void startWxPay(String json){
        PayReq req = new PayReq();
        req.appId = MyThirdDelegate.WX_APP_ID;// 微信开放平台审核通过的应用APPID
        try {
            JSONObject jsonObject=new JSONObject(json);
            req.partnerId = jsonObject.getString("partnerid");// 微信支付分配的商户号
            req.prepayId = jsonObject.getString("prepayid");// 预支付订单号，app服务器调用“统一下单”接口获取
            req.nonceStr = jsonObject.getString("noncestr");// 随机字符串，不长于32位，服务器小哥会给咱生成
            req.timeStamp = jsonObject.getString("timestamp");// 时间戳，app服务器小哥给出
            req.packageValue = jsonObject.getString("package");// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
            req.sign = jsonObject.getString("sign");// 签名，服务器小哥给出，他会根据：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=4_3指导得到这个
            MyThirdDelegate.mWxApi.sendReq(req);
        } catch (JSONException e) {
            ToastUtils.showShort("服务器数据异常");
            e.printStackTrace();
        }
    }

//    public void startWXMini(String json){
//        String arr[] = json.split("&");
//        String appId = "wx21a6c1fa22a0441c"; // 填移动应用(App)的 AppId，非小程序的 AppID
//        IWXAPI api = WXAPIFactory.createWXAPI(this, appId);
//
//        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
//        req.userName = "gh_26856b40c4eb"; // 填小程序原始id
//        req.path = "pages/pays/pays?outTradeNo="+arr[0]+"&payMoney="+arr[1];                  ////拉起小程序页面的可带参路径，不填默认拉起小程序首页，对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"。
//        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_PREVIEW;// 可选打开 开发版，体验版和正式版
//        api.sendReq(req);
//    }


    public void startAlipay(String json){
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderDetailActivity.this);
                Map<String, String> result = alipay.payV2(json, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = Comments.SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Comments.SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);

                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        startActivity(new Intent(mContext, PayResultActivity.class));
                    } else {
                        getDetail();
                    }
                    break;
                }
            }
        };
    };

    /*protected void getList() {
        showWaitDialog("",true);
        RxHttp.get(Comments.ORDER_DETAIL)
                .add("idNO", getIntent().getStringExtra("idNO"))
                .asResponse(NewAddressBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 1000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }*/
}