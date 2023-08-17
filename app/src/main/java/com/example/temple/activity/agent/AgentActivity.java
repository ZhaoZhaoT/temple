package com.example.temple.activity.agent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.bean.DaiLiShangBean;
import com.example.temple.bean.UserBean;
import com.example.temple.bean.address.JsonBean;
import com.example.temple.bean.address.NewAddressBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.BaseUtils;
import com.example.temple.utils.DataCacheUtil;
import com.google.gson.Gson;
import com.rxjava.rxlife.RxLife;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import rxhttp.wrapper.param.RxHttp;

/**
 * 代理商
 */
public class AgentActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.iv_select)
    ImageView iv_select;
    @BindView(R.id.tv_click)
    TextView tv_click;


    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_phone)
    TextView tv_phone;

    @BindView(R.id.regiest_code)
    EditText regiest_code;

    @BindView(R.id.tv_send_sms)
    TextView tvSendSms;
    @BindView(R.id.lin_code)
    LinearLayout lin_code;

    private Thread thread;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private NewAddressBean.ContentBean bean;

    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private String province, city, county;
    OptionsPickerView pvOptions;
    String address;


    @Override
    protected void initView() {
        baseTitleGone();


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
        getAgentDetail();
        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoaded) {
                    KeyboardUtils.hideSoftInput(AgentActivity.this);
                    showPickerView();
                }
            }
        });

        tvSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showShort("请输入手机号");
                } else {
                    getSms(phone);
                }
            }
        });


        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                canNext();

            }
        });


        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                canNext();

            }
        });

        regiest_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                canNext();

            }
        });

    }

    public void getAgentDetail() {
        showWaitDialog("", false);
        RxHttp.get(Comments.AGENT_DETAIL)
                .asResponse(DaiLiShangBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 2000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }


    public void submitAgent() {
        showWaitDialog("", false);
        RxHttp.postJson(Comments.ADD_AGENT)
                .add("address", address)
                .add("code", regiest_code.getText().toString())
                .add("name", et_name.getText().toString())
                .add("phone", etPhone.getText().toString())
                .asResponse(UserBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 3000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 3000);
                });
    }

    public void getSms(String phone) {
        showWaitDialog("", false);
        RxHttp.postJson(Comments.SMS_CODE)
                .add("phone", phone)
                .asResponse(UserBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 1000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 1000) {
            ToastUtils.showShort("验证码发送成功");
            daoJs();
        } else if (reqcode == 2000) {
            DaiLiShangBean bean = (DaiLiShangBean) re_data;
            if (bean.getStatus().equals("OK")) {
                tv_click.setVisibility(View.VISIBLE);
                et_name.setVisibility(View.VISIBLE);
                tv_name.setVisibility(View.GONE);
                etPhone.setVisibility(View.VISIBLE);
                tv_phone.setVisibility(View.GONE);
                lin_code.setVisibility(View.VISIBLE);
                tvAddress.setEnabled(true);
                iv_select.setVisibility(View.VISIBLE);
            } else {
                tv_click.setVisibility(View.GONE);
                et_name.setVisibility(View.GONE);
                tv_name.setVisibility(View.VISIBLE);
                tv_name.setText(bean.getName());

                tv_phone.setText(bean.getPhone());
                etPhone.setVisibility(View.GONE);
                tv_phone.setVisibility(View.VISIBLE);
                lin_code.setVisibility(View.GONE);
                iv_select.setVisibility(View.GONE);
                tvAddress.setText(bean.getAddress());
                tvAddress.setEnabled(false);

            }
        }
        if (reqcode == 3000) {
            startActivity(new Intent(AgentActivity.this, AgentSuccessActivity.class));
        }
    }


    boolean flag = false;

    private void daoJs() {
        //intervalRange四个参数分别为：从0开始、到60结束、延时0开始，单位时间（NANOSECONDS,MICROSECONDS,MILLISECONDS,SECONDS,MINUTES,HOURS,DAYS）。
        Disposable countdownDisposable = Flowable.intervalRange(0, 60, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        tvSendSms.setText("" + (60 - aLong));
                        flag = true;
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        tvSendSms.setText("重新获取");
                        flag = false;
                        //倒计时完毕事件处理
                        finish();
                    }
                })
                .subscribe();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_agent;
    }


    private void canNext() {
        if (!TextUtils.isEmpty(et_name.getText().toString()) &&
                !TextUtils.isEmpty(address) &&
                !TextUtils.isEmpty(etPhone.getText().toString()) &&
                !TextUtils.isEmpty(regiest_code.getText().toString())) {
            tv_click.setBackgroundResource(R.mipmap.submit_bt);
        } else {
            tv_click.setBackgroundResource(R.mipmap.submit_bt_unselect);
        }
    }


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
                address = province + "-" + city + "-" + county;
                tvAddress.setText(address);
                canNext();
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
        } else if (v.getId() == R.id.tv_click) {
            if (TextUtils.isEmpty(et_name.getText().toString())) {
                ToastUtils.showShort("请输入真实姓名");
            } else if (TextUtils.isEmpty(address)) {
                ToastUtils.showShort("请选择所在地");
            } else if (TextUtils.isEmpty(etPhone.getText().toString())) {
                ToastUtils.showShort("请输入手机号");
            } else if (TextUtils.isEmpty(regiest_code.getText().toString())) {
                ToastUtils.showShort("请输入验证码");
            } else {
                submitAgent();
            }

        }
    }


}