package com.example.temple.activity.agent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.KeyboardUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.bean.address.JsonBean;
import com.example.temple.bean.address.NewAddressBean;
import com.example.temple.utils.BaseUtils;
import com.example.temple.utils.DataCacheUtil;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 代理商
 */
public class AgentActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.tv_click)
    TextView tv_click;


    private Thread thread;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private NewAddressBean.ContentBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String data = DataCacheUtil.getInstance(mContext).getmSharedPreferences().getString("cityData2", "");
        if (TextUtils.isEmpty(data)) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    initJsonData(null);
                }
            });
            thread.start();
        } else {
            initJsonData(data);
        }

        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoaded) {
                    KeyboardUtils.hideSoftInput(AgentActivity.this);
                    showPickerView();
                }
            }
        });

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_agent;
    }

    @Override
    protected void initView() {
        baseTitleGone();


    }

    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private String province, city, county;
    OptionsPickerView pvOptions;

    private void showPickerView() {

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                province = opt1tx;
                city = opt2tx;
                county = opt3tx;
                String tx = province +"-"+ city+"-"+ county;
                tvAddress.setText(tx);
            }
        })
                .setTitleText("所在地")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .isDialog(true) //是否对话框样式显示（显示在页面中间）
                .isCenterLabel(false) //是否只显示选中的label文字，false则每项item全部都带有 label
                .setDividerColor(Color.WHITE)
                .setItemVisibleCount(3)
                .setLineSpacingMultiplier(2.5f)
                .setLayoutRes(R.layout.address_pop, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                pvOptions.returnData();
                                pvOptions.dismiss();
                            }
                        });
                    }
                })
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);
        pvOptions.show();
    }


    private void initJsonData(String JsonData) {

        if (JsonData == null) {
            JsonData = new BaseUtils().getJson(this, "province.json");
            DataCacheUtil.getInstance(mContext).getmSharedPreferences().saveString("cityData2", JsonData);
        }
        ArrayList<JsonBean> jsonBean = parseData(JsonData);

        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {
            ArrayList<String> cityList = new ArrayList<>();
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);
                ArrayList<String> city_AreaList = new ArrayList<>();


                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);
            }


            options2Items.add(cityList);
            options3Items.add(province_AreaList);
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    boolean isLoaded;
    private static final int MSG_LOAD_FAILED = 0x0003;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;
            }
        }
    };

    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        tv_click.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        }else if (v.getId() == R.id.tv_click) {
            startActivity(new Intent(AgentActivity.this,AgentSuccessActivity.class));
        }
    }


}