package com.example.temple.activity.user;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
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

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class AddAddressActivity extends BaseTitleActivity {


    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_address_detail)
    EditText etAddressDetail;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.ck_agree)
    CheckBox ckAgree;

    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private static boolean isLoaded = false;
    private String isDefault = "NO";
    private NewAddressBean.ContentBean bean;
    private String name, phone, detail_address;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initView() {
        grayTitle();

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

        bean = (NewAddressBean.ContentBean) getIntent().getSerializableExtra("data");
        if (bean != null) {
            etName.setText(bean.getName());
            etPhone.setText(bean.getPhone());
            if (bean.getProvince().contains("北京") || bean.getProvince().contains("天津") || bean.getProvince().contains("上海") || bean.getProvince().contains("重庆")) {
                tvAddress.setText(bean.getCity() + bean.getArea());
            } else {
                tvAddress.setText(bean.getProvince() + bean.getCity() + bean.getArea());
            }
            etAddressDetail.setText(bean.getDetail());
            province = bean.getProvince();
            city = bean.getCity();
            county = bean.getArea();
            ckAgree.setChecked(bean.getIsDefault().equals("YES")? true : false);
            baseTitle.setText("修改地址");
        }else{
            baseTitle.setText("新增地址");
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoaded) {
                    KeyboardUtils.hideSoftInput(AddAddressActivity.this);
                    showPickerView();
                }
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                phone = etPhone.getText().toString();
                detail_address = etAddressDetail.getText().toString();
                if (checkInput()) {
                    if (bean != null) {
                        updateAddress();
                    } else {
                        addAddress();
                    }
                }
            }
        });
        ckAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    isDefault = "YES";
                } else {
                    isDefault = "NO";
                }
            }
        });
    }

    public boolean checkInput() {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(province) || TextUtils.isEmpty(detail_address) || TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("请输入完整信息");
            return false;
        }
        return true;
    }


    public void addAddress() {
        RxHttp.postJson(Comments.ADD_ADDRESS)
                .add("name", name)
                .add("phone", phone)
                .add("province", province)
                .add("city", city)
                .add("area", county)
                .add("detail", detail_address)
                .add("isDefault", isDefault)
                .asResponse(String.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 1000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }


    public void updateAddress() {
        RxHttp.postJson(Comments.EDIT_ADDRESS)
                .add("id",bean.getId())
                .add("name", name)
                .add("phone", phone)
                .add("province", province)
                .add("city", city)
                .add("area", county)
                .add("detail", detail_address)
                .add("isDefault", isDefault)
                .asResponse(String.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 2000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 1000) {
            ToastUtils.showShort("新增成功");
        } else if (reqcode == 2000) {
            ToastUtils.showShort("修改成功");
        }else if (reqcode == 3000) {
            ToastUtils.showShort("删除成功");
        }
        setResult(RESULT_OK);
        finish();
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

    private String province, city, county;

    private void showPickerView() {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
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

                String tx = opt1tx + opt2tx + opt3tx;
                tvAddress.setText(tx);
                province = opt1tx;
                city = opt2tx;
                county = opt3tx;
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .build();


        pvOptions.setPicker(options1Items, options2Items, options3Items);
        pvOptions.show();
    }

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
}