
package com.example.temple.activity.item.order;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.base.MyThirdDelegate;
import com.example.temple.base.PayResult;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.AppManager;
import com.example.temple.utils.EventBusUtils;
import com.rxjava.rxlife.RxLife;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

/**
 * 预约订单提交
 */
public class AdvanceOrderSubmissionActivity extends BaseTitleActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_time)
    TextView tv_time;

    @BindView(R.id.tv_teacher_name)
    TextView tv_teacher_name;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_total_price)
    TextView tv_total_price;


    @BindView(R.id.ll_wechat_pay)
    LinearLayout ll_wechat_pay;
    @BindView(R.id.ck_agree1)
    CheckBox ck_agree1;
    @BindView(R.id.ck_agree2)
    CheckBox ck_agree2;
    @BindView(R.id.tv_submit_order)
    TextView tvSubmitOrder;

    private Double price;
    private String address;

    private String name;
    private String date;
    private String time;
    private String mPayType = "WX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advance_order_submit;
    }

    @Override
    protected void initView() {
        baseTitleGone();
    }


    @Override
    protected void initListener() {
        super.initListener();
        price = getIntent().getDoubleExtra("price", 0);
        address = getIntent().getStringExtra("address");
        tv_address.setText(address);
        tv_price.setText("¥" + price);
        tv_total_price.setText("¥" + price);
        name = getIntent().getStringExtra("name");
        tv_name.setText(name);//预约人
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        tv_time.setText(date + " " + time);

        tv_teacher_name.setText("预约" + getIntent().getStringExtra("teachername"));

        iv_left.setOnClickListener(this);
        tvSubmitOrder.setOnClickListener(this);


        ck_agree2.setOnCheckedChangeListener(this);
        ck_agree1.setOnCheckedChangeListener(this);
        tvSubmitOrder.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        }
        if (v.getId() == R.id.tv_submit_order) {
            if (TextUtils.isEmpty(mPayType)) {
                ToastUtils.showShort("请选择支付方式!");
                return;
            }

            onPay();
        }
    }

    protected void onPay() {
        showWaitDialog("", false);
        RxHttp.postJson(Comments.ABOUT_TEACHER)
                .add("teacherId", getIntent().getStringExtra("id"))
                .add("name", name)
                .add("phone", getIntent().getStringExtra("phone"))
                .add("startAt", date + " " + time.substring(0, time.lastIndexOf("~")) + ":00")
                .add("endAt", date + " " + time.substring(time.lastIndexOf("~") + 1, time.length()) + ":00")
                .add("payType", mPayType)
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
        if (reqcode == 3000) {
           /* ToastUtils.showShort("订单已提交");
            startActivity(new Intent(mContext, OrderListActivity.class).putExtra("index",1));*/
            if (mPayType.equals("WX")) {
                startWxPay((String) re_data);
            } else if (mPayType.equals("ZFB")) {
                startAlipay((String) re_data);
            } else if (mPayType.equals("WX_MINI")) {
//                BaseUtils.startWXMini(this,(String) re_data);
            }

        }
    }


    //微信支付
    public void startWxPay(String json) {
        if (!MyThirdDelegate.mWxApi.isWXAppInstalled()) {
            ToastUtils.showShort("请安装微信");
            return;
        }
        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
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
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }


    //支付宝支付
    public void startAlipay(String json) {
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(AdvanceOrderSubmissionActivity.this);
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
                        EventBusUtils.post(Comments.PAY_TEACHER_SUCCESS);
                        ToastUtils.showShort("支付成功");
                        nextSetp();
                    } else {
                        ToastUtils.showShort("您取消了订单");
                    }


                    break;
                }
            }
        }

        ;
    };

    public void nextSetp() {
        startActivity(new Intent(mContext, ReservationOrderSuccessfulActivity.class)
                .putExtra("address", address)
                .putExtra("name", name)
                .putExtra("time", date + " " + time));
        finish();
        AppManager.getAppManager().finishActivity(ReservationInformationActivity.class);
        AppManager.getAppManager().finishActivity(OrderTeacherDetailsActivity.class);

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked == true) {
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

}