package com.example.temple.activity.item;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.BaseApplication;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.activity.order.OrderListActivity;
import com.example.temple.activity.order.PayResultActivity;
import com.example.temple.base.MyThirdDelegate;
import com.example.temple.base.PayResult;
import com.example.temple.bean.CarBean;
import com.example.temple.bean.address.NewAddressBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.EventBusUtils;
import com.rxjava.rxlife.RxLife;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rxhttp.wrapper.param.RxHttp;

/**
 * 神位 确认订单
 */
public class SpiritTableConfirmOrderActivity extends BaseTitleActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.tv_submit_order)
    TextView tvSubmitOrder;

    @BindView(R.id.iv_picture)
    ImageView ivPicture;
    @BindView(R.id.tv_product_tile)
    TextView tvProductTile;
    @BindView(R.id.tv_size)
    TextView tvSize;

    @BindView(R.id.tv_choose_number)
    TextView tvChooseNumber;
    @BindView(R.id.tv_price)
    TextView tvPrice;


    @BindView(R.id.ck_agree1)
    CheckBox ck_agree1;
    @BindView(R.id.ck_agree2)
    CheckBox ck_agree2;

    @BindView(R.id.ll_wechat_pay)
    LinearLayout ll_wechat_pay;


    private DecimalFormat df = new DecimalFormat("#0.00");
    private Double mTotalPrice;
    private String mPayType;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_spirit_confirm_order;
    }

    @Override
    protected void initView() {
        baseTitle.setText("确认订单");
        grayTitle();

        List<CarBean.ContentBean> list = (List<CarBean.ContentBean>) getIntent().getSerializableExtra("list");

//        GlideUtils.loadRoundCircleImage(mContext, specVOBean.getSpecCover(), ivPicture, 4);

//        BigDecimal bigDecimal = new BigDecimal(mTotalPrice).setScale(2, RoundingMode.HALF_UP);
//        mTotalPrice = bigDecimal.doubleValue();

        mPayType = "WX";
//        mWxApi = WXAPIFactory.createWXAPI(mContext, Comments.WX_APP_ID, true);
//        mWxApi.registerApp(Comments.WX_APP_ID);
//        //建议动态监听微信启动广播进行注册到微信
//        registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//                // 将该 app 注册到微信
//                mWxApi.registerApp(Comments.WX_APP_ID);
//            }
//        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));

        EventBusUtils.register(this);

    }

    @Override
    protected void initListener() {
        super.initListener();
        tvSubmitOrder.setOnClickListener(this);

        ck_agree2.setOnCheckedChangeListener(this);
        ck_agree1.setOnCheckedChangeListener(this);
    }

    protected void getList() {
        showWaitDialog("", true);
        RxHttp.get(Comments.ADDRESS_LIST)
                .add("page", 0)
                .add("size", 2)
                .asResponse(NewAddressBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 1000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }


    private List<Integer> ids = new ArrayList<>();

    protected void carBuyNow() {
        showWaitDialog("", false);
        RxHttp.postJson(Comments.CAR_ORDER)
                .add("addressId", "addressId")
                .add("cartIds", ids)
                .add("payType", mPayType)
                .add("remark", "")
                .asResponse(String.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 3000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 3000);
                });
    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 2000) {
           /* ToastUtils.showShort("订单已提交");
            startActivity(new Intent(mContext, OrderListActivity.class).putExtra("index",1));*/
//            JsonObject object=new JsonObject((String)re_data);
            if (mPayType.equals("WX")) {
                startWxPay((String) re_data);
            } else if (mPayType.equals("ZFB")) {
                startAlipay((String) re_data);
            } else if (mPayType.equals("WX_MINI")) {
//               BaseUtils.startWXMini(this,(String) re_data);
            }

        } else if (reqcode == 3000) {
           /* ToastUtils.showShort("订单已提交");
            startActivity(new Intent(mContext, OrderListActivity.class).putExtra("index",1));*/
            if (mPayType.equals("WX")) {
                startWxPay((String) re_data);
            } else if (mPayType.equals("ZFB")) {
                startAlipay((String) re_data);
            } else if (mPayType.equals("WX_MINI")) {
//                BaseUtils.startWXMini(this,(String) re_data);
            }
            EventBusUtils.post(Comments.ON_CAR);

        } else if (reqcode == 4000) {
            ToastUtils.showShort("兑换成功");
            startActivity(new Intent(mContext, OrderListActivity.class).putExtra("index", 2));
        } else if (reqcode == 5000) {
            onPay();
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b == true) {
            if (compoundButton.getId() == R.id.ck_agree1) {
                ck_agree2.setChecked(false);
                mPayType = "WX";
            }
            if (compoundButton.getId() == R.id.ck_agree2) {
                ck_agree1.setChecked(false);
                mPayType = "ZFB";
            }

        } else {
            if (compoundButton.getId() == R.id.ck_agree1) {
                mPayType = "";
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_submit_order) {
            if (TextUtils.isEmpty(mPayType)) {
                ToastUtils.showShort("请选择支付方式!");
                return;
            }
//调 支付
//            onPay();
        }
    }

    public void onPay() {
        carBuyNow();
        BaseApplication.payMoney = mTotalPrice;
    }


    private boolean isUpdate;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                int code = data.getIntExtra("payResult", 0);
                Log.i("test", "code: " + code);
            }
        }
    }


    public void startWxPay(String json) {
        PayReq req = new PayReq();
        req.appId = MyThirdDelegate.WX_APP_ID;// 微信开放平台审核通过的应用APPID
        try {
            JSONObject jsonObject = new JSONObject(json);
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

    public void startAlipay(String json) {
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(SpiritTableConfirmOrderActivity.this);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(Object event) {
        if (event.equals(Comments.ON_ORDER_CONFIRM)) {
//            Log.i("eventbus","ConfirmOrderActivity");
            startActivity(new Intent(mContext, OrderListActivity.class).putExtra("index", 0));
            finish();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
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
                        startActivity(new Intent(mContext, OrderListActivity.class).putExtra("index", 0));
                        finish();
                    }
                    break;
                }
            }
        }

        ;
    };


    /* */

    /**
     * 请求app服务器得到的回调结果
     *//*
    @Override
    public void onGet(JSONObject jsonObject) {
        if (mWxApi != null) {
            PayReq req = new PayReq();

            req.appId = WX_APPID;// 微信开放平台审核通过的应用APPID
            try {
                req.partnerId = jsonObject.getString("partnerid");// 微信支付分配的商户号
                req.prepayId = jsonObject.getString("prepayid");// 预支付订单号，app服务器调用“统一下单”接口获取
                req.nonceStr = jsonObject.getString("noncestr");// 随机字符串，不长于32位，服务器小哥会给咱生成
                req.timeStamp = jsonObject.getString("timestamp");// 时间戳，app服务器小哥给出
                req.packageValue = jsonObject.getString("package");// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
                req.sign = jsonObject.getString("sign");// 签名，服务器小哥给出，他会根据：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=4_3指导得到这个
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mWxApi.sendReq(req);
            Log.d("发起微信支付申请");
        }

        PayReq request = new PayReq();
        request.appId = "wxd930ea5d5a258f4f";
        request.partnerId = "1900000109";
        request.prepayId= "1101000000140415649af9fc314aa427";
        request.packageValue = "Sign=WXPay";
        request.nonceStr= "1101000000140429eb40476f8896f4c9";
        request.timeStamp= "1398746574";
        request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";

        wxapi.sendReq(request);

    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}