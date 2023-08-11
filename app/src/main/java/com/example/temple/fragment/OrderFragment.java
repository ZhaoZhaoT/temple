package com.example.temple.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.BaseApplication;
import com.example.temple.R;
import com.example.temple.activity.order.LogisticsActivity;
import com.example.temple.activity.order.OrderDetailActivity;
import com.example.temple.activity.order.PayResultActivity;
import com.example.temple.adapter.OrderAdapter;
import com.example.temple.base.MyThirdDelegate;
import com.example.temple.base.PayResult;
import com.example.temple.bean.OrderBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.rxjava.rxlife.RxLife;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rxhttp.wrapper.param.RxHttp;

public class OrderFragment extends BaseListFragment {

    private OrderAdapter mAdapter;
    private int mType=-1;
//    private int page = 0, size = 10;

    public OrderFragment(int mType) {
        this.mType = mType;
    }

    public OrderFragment() { }
    private int index;

    private OrderBean.ContentBean mCurrent;

    @Override
    protected int getLayoutId() {
        return super.getLayoutId();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        rView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rView.setHasFixedSize(true);
        mAdapter = new OrderAdapter(R.layout.item_order);
        rView.setAdapter(mAdapter);
        mAdapter.setEmptyView(R.layout.empty_view);

//        mWxApi = WXAPIFactory.createWXAPI(getActivity(), Comments.WX_APP_ID, true);
//        mWxApi.registerApp(Comments.WX_APP_ID);

    }

    @Override
    protected void initListener() {
        super.initListener();
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                startActivity(new Intent(getContext(), OrderDetailActivity.class)
                        .putExtra("data",mAdapter.getData().get(position))
                        .putExtra("id",mAdapter.getData().get(position).getId()));
            }
        });
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                index=position;
                TextView textView= (TextView) view;
                if(view.getId()==R.id.tv_btn1){
                    if(textView.getText().toString().equals("取消订单")){
                        showClearDialog(position);
                    }else if(textView.getText().toString().contains("确认收货")){
                        confirmOrder(mAdapter.getData().get(position).getIdNo());
                    }
                }else if(view.getId()==R.id.tv_btn2){
                    if(textView.getText().toString().contains("支付")){
                        mCurrent=mAdapter.getData().get(position);
                        buyNow(mAdapter.getData().get(position));
                    }else if(textView.getText().toString().contains("查看物流")){
                        if(mAdapter.getData().get(position).getOrderExpressVO()!=null){
                            startActivity(new Intent(getActivity(),
                                    LogisticsActivity.class).putExtra("no",mAdapter.getData().get(position).getOrderExpressVO().getExpressNo()));
                        }
                    }else if(textView.getText().toString().contains("去支付")) {

                    }
                }
            }
        });
    }

    private void showClearDialog(int position) {
        new XPopup.Builder(getContext())
                .dismissOnBackPressed(true)
                .dismissOnTouchOutside(true)
                .asConfirm("提示", "你确定取消订单",
                        "取消", "确定",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                cancelOrder(mAdapter.getData().get(position).getIdNo());
                            }
                        }, null, false).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        getList();
    }

    @Override
    public void getList() {
        Map queryMap = new HashMap();
        queryMap.put("page", mPage);
        queryMap.put("size", 10);
        if(mType!=-1){
            queryMap.put("status",mType);
        }
        RxHttp.get(Comments.ORDER_LIST)
                .addAll(queryMap)
                .asResponse(OrderBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        mTotalPage = model.getData().getTotalPages();
                        onJsonDataGetSuccess(model.getData(), 1000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }


    public void cancelOrder(String idNo) {
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

    private String mPayType;
    protected void buyNow(OrderBean.ContentBean bean) {
        showWaitDialog("",false);
        BaseApplication.payMoney=bean.getOrderItemVO().getPrice();

        if(bean.getPayType().equals("WX")){
            mPayType="WX";
        }else if(bean.getPayType().equals("ZFB")){
            mPayType="ZFB";
        }else if(bean.getPayType().equals("WX_MINI")) {
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


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 1000) {
            OrderBean bean = (OrderBean) re_data;
            if (bean.getContent() != null && bean.getContent().size() > 0) {
                if (mPage == 0) {
                    mAdapter.setList(bean.getContent());
                } else {
                    mAdapter.addData(bean.getContent());
                }
            } else {
                if (mPage == 0) {
                    mAdapter.setList(null);
                }
            }
        }else if(reqcode==2000||reqcode==3000){
            refreshData();
        }else if(reqcode==4000){
            if(mPayType.equals("WX")){
                startWxPay((String) re_data);
            }else if(mPayType.equals("ZFB")){
                startAlipay((String) re_data);
            }else if(mPayType.equals("WX_MINI")) {
//                BaseUtils.startWXMini(getContext(),(String) re_data);
            }
        }else if(reqcode==5000){
            buyNow(mCurrent);
        }
    }

//    public void startWXMini(String json){
//        String arr[] = json.split("&");
//        String appId = "wx21a6c1fa22a0441c"; // 填移动应用(App)的 AppId，非小程序的 AppID
//        IWXAPI api = WXAPIFactory.createWXAPI(getContext(), appId);
//
//        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
//        req.userName = "gh_26856b40c4eb"; // 填小程序原始id
//        req.path = "pages/pays/pays?outTradeNo="+arr[0]+"&payMoney="+arr[1];                  ////拉起小程序页面的可带参路径，不填默认拉起小程序首页，对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"。
//        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_PREVIEW;// 可选打开 开发版，体验版和正式版
//        api.sendReq(req);
//    }

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


    public void startAlipay(String json){
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
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

                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        startActivity(new Intent(getActivity(), PayResultActivity.class));
                    } else {
                        refreshData();
                    }
                    break;
                }
            }
        };
    };

}
