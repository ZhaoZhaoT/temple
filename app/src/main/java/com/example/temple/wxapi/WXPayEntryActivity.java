package com.example.temple.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.temple.activity.item.GongfengActivity;
import com.example.temple.activity.item.order.AdvanceOrderSubmissionActivity;
import com.example.temple.base.MyThirdDelegate;
import com.example.temple.network.Comments;
import com.example.temple.utils.AppManager;
import com.example.temple.utils.EventBusUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, MyThirdDelegate.WX_APP_ID);

        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {

                if (AppManager.getAppManager().currentActivity() instanceof GongfengActivity) {//康宁 供奉支付成功
                    GongfengActivity activity = (GongfengActivity) AppManager.getAppManager().currentActivity();
                    if (activity != null) {
                        EventBusUtils.post(Comments.PAY_SUCCESS);
                        activity.finish();
                    }
                } else if (AppManager.getAppManager().currentActivity() instanceof AdvanceOrderSubmissionActivity) {//预约老师支付成功
                    AdvanceOrderSubmissionActivity activity = (AdvanceOrderSubmissionActivity) AppManager.getAppManager().currentActivity();
                    if (activity != null) {
                        EventBusUtils.post(Comments.PAY_TEACHER_SUCCESS);
                        activity.nextSetp();
                    }
                }
                Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
            } else if (resp.errCode == -2) {
                EventBusUtils.post(Comments.ON_ORDER_CONFIRM);
                Toast.makeText(this, "您取消了订单", Toast.LENGTH_LONG).show();
            } else {
                EventBusUtils.post(Comments.ON_ORDER_CONFIRM);
                Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}
