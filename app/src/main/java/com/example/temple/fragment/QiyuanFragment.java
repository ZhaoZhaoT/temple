package com.example.temple.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.temple.R;
import com.example.temple.activity.item.GongfengActivity;
import com.example.temple.bean.KangNingDetailsBean;
import com.example.temple.datatype.DataMatching;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.BaseUtils;
import com.example.temple.utils.EventBusUtils;
import com.rxjava.rxlife.RxLife;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class QiyuanFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_gongfeng1)
    TextView tv_gongfeng1;
    @BindView(R.id.tv_gongfeng2)
    TextView tv_gongfeng2;
    @BindView(R.id.tv_toptext)
    TextView tv_toptext;

    @BindView(R.id.view)
    WebView webView;
    @BindView(R.id.view_two)
    WebView view_two;

    @BindView(R.id.tv_lamp)
    TextView tv_lamp;
    @BindView(R.id.tv_lamp2)
    TextView tv_lamp2;
    @BindView(R.id.tv_god)
    TextView tv_god;
    @BindView(R.id.tv_god2)
    TextView tv_god2;

    private int mType = 1;
    private String godPrice;

    public QiyuanFragment(int mType) {
        this.mType = mType;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        BaseUtils.initWebSetting(webView,Color.parseColor("#F0F0F0"));
        BaseUtils.initWebSetting(view_two,Color.parseColor("#F0F0F0"));

        getDetail(DataMatching.KangNingType.getKangNingDesc(String.valueOf(mType)));

    }

    public void getDetail(String type) {
        RxHttp.get(Comments.KANGNING_DETAIL)
                .add("type", type)
                .asResponse(KangNingDetailsBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        tv_toptext.setText(model.getData().getTitle());
                        webView.loadDataWithBaseURL(null, model.getData().getDescriptionOne(), "text/html", "utf-8", null);
//                        webView.loadData(model.getData().getDescriptionOne(),"text/html","utf-8");
                        view_two.loadDataWithBaseURL(null, model.getData().getDescriptionTwo(), "text/html", "utf-8", null);

                        tv_lamp.setText("已有" + model.getData().getLampCount() + "位善信供奉");
                        tv_lamp2.setText("供灯   " + model.getData().getLampCount());
                        tv_god.setText("已有" + model.getData().getGodCount() + "位善信供奉");
                        tv_god2.setText("供神   " + model.getData().getGodCount());
                        godPrice=model.getData().getGodPrice();

                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_qiyuan;
    }

    @Override
    protected void initListener() {
        tv_gongfeng1.setOnClickListener(this);
        tv_gongfeng2.setOnClickListener(this);
        EventBusUtils.register(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_gongfeng1) {
            startActivity(new Intent(getActivity(), GongfengActivity.class)
                    .putExtra("type", DataMatching.KangNingType.getKangNingDesc(String.valueOf(mType)))
                    .putExtra("enshrineType", "ZERO"));
        } else if (v.getId() == R.id.tv_gongfeng2) {
            startActivity(new Intent(getActivity(), GongfengActivity.class)
                    .putExtra("type", DataMatching.KangNingType.getKangNingDesc(String.valueOf(mType)))
                    .putExtra("enshrineType", "ONE")
                    .putExtra("godPrice",godPrice ));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(Object event) {
        if (event.equals(Comments.PAY_SUCCESS)) {
            getDetail(DataMatching.KangNingType.getKangNingDesc(String.valueOf(mType)));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }

}
