package com.example.temple.activity.item;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.activity.order.PayResultActivity;
import com.example.temple.base.MyThirdDelegate;
import com.example.temple.base.PayResult;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.EventBusUtils;
import com.example.temple.utils.StringUtils;
import com.example.temple.wheelview.TimePickerBuilder;
import com.example.temple.wheelview.TimePickerView;
import com.rxjava.rxlife.RxLife;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class GongfengActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.tv_name)
    EditText tv_name;
    @BindView(R.id.group1)
    RadioGroup group1;

    @BindView(R.id.tv_select_birth)
    TextView tv_select_birth;

    @BindView(R.id.ed_content)
    EditText ed_content;
    @BindView(R.id.et_money)
    EditText et_money;
    @BindView(R.id.ck_wechat)
    CheckBox ck_wechat;
    @BindView(R.id.ck_alypay)
    CheckBox ck_alypay;
    @BindView(R.id.lin_to_pay)
    LinearLayout lin_to_pay;


    @BindView(R.id.lin_deng)
    RelativeLayout lin_deng;

    @BindView(R.id.lin_iv_shen)
    LinearLayout lin_iv_shen;
    @BindView(R.id.group)
    RadioGroup group;

    TimePickerView pvTime;

    String sex = "男";
    String birthday;
    private String mPayType = "WX";
    private String healthyType, enshrineType;
    private String godPrice;//奉神金额
    private String selectGodPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gongfeng;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        healthyType = getIntent().getStringExtra("type");
        enshrineType = getIntent().getStringExtra("enshrineType");
        godPrice = getIntent().getStringExtra("godPrice");

        if (StringUtils.isEmpty(godPrice)) {//奉灯
            lin_deng.setVisibility(View.VISIBLE);
            lin_iv_shen.setVisibility(View.GONE);
        } else {//奉神
            lin_deng.setVisibility(View.GONE);
            lin_iv_shen.setVisibility(View.VISIBLE);
            ArrayList<String> price = StringUtils.splitToList(godPrice, ",");

            for (int i = 0; i < price.size(); i++) {
                RadioButton radioButton = new RadioButton(this);
                RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                //设置RadioButton的样式
                radioButton.setButtonDrawable(R.drawable.selector_checkbox3);
                //设置文字距离四周的距离
                radioButton.setPadding(20, 0, 20, 0);
                //设置文字
                radioButton.setText("¥" + price.get(i));
                radioButton.setTextSize(14);
                radioButton.setTextColor(Color.parseColor("#E85B35"));
                //设置radioButton的点击事件
                int finalI1 = i;
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectGodPrice = price.get(finalI1);
                    }
                });
                //将radioButton添加到radioGroup中
                group.addView(radioButton);
            }
        }

    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        tv_select_birth.setOnClickListener(this);
        lin_to_pay.setOnClickListener(this);
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radio1:
                        sex = "男";
                    case R.id.radio2:
                        sex = "女";
                }
            }
        });
        ck_wechat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b == true) {
                    mPayType = "WX";
                    ck_wechat.setEnabled(false);
                    ck_alypay.setEnabled(true);
                    ck_alypay.setChecked(false);
                }
            }
        });

        ck_alypay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b == true) {
                    mPayType = "ZFB";
                    ck_alypay.setEnabled(false);
                    ck_wechat.setEnabled(true);
                    ck_wechat.setChecked(false);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.tv_select_birth) {
            pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    birthday = format.format(date);
                    tv_select_birth.setText(birthday);
                }
            }).setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                        @Override
                        public void customLayout(View v) {
                            TextView tv_affrim = v.findViewById(R.id.tv_affrim);
                            tv_affrim.setOnClickListener(v1 -> {
                                pvTime.returnData();
                                pvTime.dismiss();
                            });
                        }
                    })
                    .setCancelColor(Color.BLACK)
                    .setSubmitColor(Color.BLACK)
                    .setSubCalSize(24)
                    .setContentTextSize(18)
                    .isDialog(true) //是否对话框样式显示（显示在页面中间）
                    //.isCyclic(true) //是否循环滚动
                    .setType(new boolean[]{true, true, true, false, false, false}) //显示“年月日时分秒”的哪几项
                    .isCenterLabel(false) //是否只显示选中的label文字，false则每项item全部都带有 label
                    .setDividerColor(Color.WHITE)
                    .setItemsVisible(5)
                    .setLineSpacingMultiplier(2.2f)
                    .build();
            //设置显示的日期
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-20"));
            } catch (ParseException e) {
                //e.printStackTrace();
            }
            //这里需要注意的是月份是从0开始的，要显示10月份这里的参数应该是9
            //calendar.set(1997,9,10);
            pvTime.setDate(calendar);
            pvTime.show();
        } else if (v.getId() == R.id.lin_to_pay) {
            if (TextUtils.isEmpty(tv_name.getText().toString())) {
                ToastUtils.showShort("请输入姓名");
            } else if (TextUtils.isEmpty(sex)) {
                ToastUtils.showShort("请选择性别");
            } else if (TextUtils.isEmpty(birthday)) {
                ToastUtils.showShort("请选择生辰");
            } else if (TextUtils.isEmpty(ed_content.getText().toString())) {
                ToastUtils.showShort("请输入许愿内容");
            } else if (StringUtils.isEmpty(godPrice) && TextUtils.isEmpty(et_money.getText().toString())) {
                ToastUtils.showShort("请输入供奉金额");
            } else if (!StringUtils.isEmpty(godPrice) && StringUtils.isEmpty(selectGodPrice)) {
                ToastUtils.showShort("请选择供奉金额");
            } else if (TextUtils.isEmpty(mPayType)) {
                ToastUtils.showShort("请选择支付方式");
            } else {
                onPay();
            }
        }
    }


    protected void onPay() {
        showWaitDialog("", false);
        RxHttp.postJson(Comments.KANGNING_ORDER)
                .add("name", tv_name.getText().toString())
                .add("sex", sex)
                .add("enshrineType", enshrineType)
                .add("healthyType", healthyType)
                .add("birthday", birthday)
                .add("content", ed_content.getText().toString())
                .add("money", StringUtils.isEmpty(godPrice) ? et_money.getText().toString() : selectGodPrice)
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
                PayTask alipay = new PayTask(GongfengActivity.this);
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
                        EventBusUtils.post(Comments.PAY_SUCCESS);
                        ToastUtils.showShort("支付成功");
                        finish();
                    } else {
                        ToastUtils.showShort("您取消了订单");
                    }
                    break;
                }
            }
        }

        ;
    };


}