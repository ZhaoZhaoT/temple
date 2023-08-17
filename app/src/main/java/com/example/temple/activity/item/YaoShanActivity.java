package com.example.temple.activity.item;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.bean.changshou.AnswerBean;
import com.example.temple.bean.changshou.ChangShouListBean;
import com.example.temple.dialog.HintSelectPopup;
import com.example.temple.dialog.SuccessViewHintPopup;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class YaoShanActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout mIvLeft;
    @BindView(R.id.scroll_view)
    NestedScrollView mScrollView;
    @BindView(R.id.tjq)
    TextView tjq;

    @BindView(R.id.layout_sex)
    LinearLayout mLayoutSex;
    @BindView(R.id.radio_sex)
    RadioGroup mRadioSex;

    @BindView(R.id.layout_age)
    LinearLayout mLayoutAge;
    @BindView(R.id.radio_age)
    RadioGroup mRadioAge;

    //第一组
    @BindView(R.id.lin_one)
    LinearLayout mLinOne;
    @BindView(R.id.radio_one_set)
    RadioGroup mRadioOneSet;
    @BindView(R.id.radio_one_set_two)
    RadioGroup mRadioOneSetTwo;
    @BindView(R.id.radio_one_set_three)
    RadioGroup mRadioOneSetThree;
    @BindView(R.id.radio_one_set_four)
    RadioGroup mRadioOneSetFour;
    @BindView(R.id.radio_one_set_five)
    RadioGroup mRadioOneSetFive;
    @BindView(R.id.radio_one_set_six)
    RadioGroup mRadioOneSetSix;
    @BindView(R.id.radio_one_set_seven)
    RadioGroup mRadioOneSetSeven;

    //第二组
    @BindView(R.id.lin_two)
    LinearLayout lin_two;
    @BindView(R.id.radio_two_set)
    RadioGroup radio_two_set;
    @BindView(R.id.radio_two_set_one)
    RadioGroup radio_two_set_one;
    @BindView(R.id.radio_two_set_two)
    RadioGroup radio_two_set_two;
    @BindView(R.id.radio_two_set_three)
    RadioGroup radio_two_set_three;
    @BindView(R.id.radio_two_set_four)
    RadioGroup mRadioTwoSetFour;
    @BindView(R.id.radio_two_set_five)
    RadioGroup mRadioTwoSetFive;
    @BindView(R.id.radio_two_set_six)
    RadioGroup mRadioTwoSetSix;
    @BindView(R.id.radio_two_set_seven)
    RadioGroup mRadioTwoSetSeven;

    //第三组
    @BindView(R.id.lin_three)
    LinearLayout lin_three;
    @BindView(R.id.radio_three_set)
    RadioGroup radio_three_set;
    @BindView(R.id.radio_three_set_one)
    RadioGroup radio_three_set_one;
    @BindView(R.id.radio_three_set_two)
    RadioGroup radio_three_set_two;
    @BindView(R.id.radio_three_set_three)
    RadioGroup radio_three_set_three;
    @BindView(R.id.radio_three_set_four)
    RadioGroup radio_three_set_four;
    @BindView(R.id.radio_three_set_five)
    RadioGroup radio_three_set_five;
    @BindView(R.id.radio_three_set_six)
    RadioGroup radio_three_set_six;
    @BindView(R.id.radio_three_set_seven)
    RadioGroup radio_three_set_seven;

    //第四组
    @BindView(R.id.lin_four)
    LinearLayout lin_four;
    @BindView(R.id.radio_four_set)
    RadioGroup radio_four_set;
    @BindView(R.id.radio_four_set_one)
    RadioGroup radio_four_set_one;
    @BindView(R.id.radio_four_set_two)
    RadioGroup radio_four_set_two;
    @BindView(R.id.radio_four_set_three)
    RadioGroup radio_four_set_three;
    @BindView(R.id.radio_four_set_four)
    RadioGroup radio_four_set_four;
    @BindView(R.id.radio_four_set_five)
    RadioGroup radio_four_set_five;
    @BindView(R.id.radio_four_set_six)
    RadioGroup radio_four_set_six;
    @BindView(R.id.radio_four_set_seven)
    RadioGroup radio_four_set_seven;

    //第五组
    @BindView(R.id.lin_five)
    LinearLayout lin_five;
    @BindView(R.id.radio_five_set)
    RadioGroup radio_five_set;
    @BindView(R.id.radio_five_set_one)
    RadioGroup radio_five_set_one;
    @BindView(R.id.radio_five_set_two)
    RadioGroup radio_five_set_two;
    @BindView(R.id.radio_five_set_three)
    RadioGroup radio_five_set_three;
    @BindView(R.id.radio_five_set_four)
    RadioGroup radio_five_set_four;
    @BindView(R.id.radio_five_set_five)
    RadioGroup radio_five_set_five;
    @BindView(R.id.radio_five_set_six)
    RadioGroup radio_five_set_six;

    //第六组
    @BindView(R.id.lin_six)
    LinearLayout lin_six;
    @BindView(R.id.radio_six_set)
    RadioGroup radio_six_set;
    @BindView(R.id.radio_six_set_one)
    RadioGroup radio_six_set_one;
    @BindView(R.id.radio_six_set_two)
    RadioGroup radio_six_set_two;
    @BindView(R.id.radio_six_set_three)
    RadioGroup radio_six_set_three;
    @BindView(R.id.radio_six_set_four)
    RadioGroup radio_six_set_four;
    @BindView(R.id.radio_six_set_five)
    RadioGroup radio_six_set_five;
    @BindView(R.id.radio_six_set_six)
    RadioGroup radio_six_set_six;

    //第七组
    @BindView(R.id.lin_seven)
    LinearLayout lin_seven;
    @BindView(R.id.radio_seven_set)
    RadioGroup radio_seven_set;
    @BindView(R.id.radio_seven_set_one)
    RadioGroup radio_seven_set_one;
    @BindView(R.id.radio_seven_set_two)
    RadioGroup radio_seven_set_two;
    @BindView(R.id.radio_seven_set_three)
    RadioGroup radio_seven_set_three;
    @BindView(R.id.radio_seven_set_four)
    RadioGroup radio_seven_set_four;
    @BindView(R.id.radio_seven_set_five)
    RadioGroup radio_seven_set_five;
    @BindView(R.id.radio_seven_set_six)
    RadioGroup radio_seven_set_six;

    //第八组
    @BindView(R.id.lin_eight)
    LinearLayout lin_eight;
    @BindView(R.id.radio_eight_set)
    RadioGroup radio_eight_set;
    @BindView(R.id.radio_eight_set_one)
    RadioGroup radio_eight_set_one;
    @BindView(R.id.radio_eight_set_two)
    RadioGroup radio_eight_set_two;
    @BindView(R.id.radio_eight_set_three)
    RadioGroup radio_eight_set_three;
    @BindView(R.id.radio_eight_set_four)
    RadioGroup radio_eight_set_four;
    @BindView(R.id.radio_eight_set_five)
    RadioGroup radio_eight_set_five;
    @BindView(R.id.radio_eight_set_six)
    RadioGroup radio_eight_set_six;

    //第九组
    @BindView(R.id.lin_nine)
    LinearLayout lin_nine;
    @BindView(R.id.radio_nine_set)
    RadioGroup radio_nine_set;
    @BindView(R.id.radio_nine_set_one)
    RadioGroup radio_nine_set_one;
    @BindView(R.id.radio_nine_set_two)
    RadioGroup radio_nine_set_two;
    @BindView(R.id.radio_nine_set_three)
    RadioGroup radio_nine_set_three;
    @BindView(R.id.radio_nine_set_four)
    RadioGroup radio_nine_set_four;
    @BindView(R.id.radio_nine_set_five)
    RadioGroup radio_nine_set_five;
    @BindView(R.id.radio_nine_set_six)
    RadioGroup radio_nine_set_six;
    @BindView(R.id.radio_nine_set_seven)
    RadioGroup radio_nine_set_seven;

    @BindView(R.id.tv_one)
    TextView tv_one;
    @BindView(R.id.tv_two)
    TextView tv_two;

    private String sex, avgPhase;
    private ArrayList<AnswerBean> list1 = new ArrayList();
    private ArrayList<AnswerBean> list2 = new ArrayList();
    private ArrayList<AnswerBean> list3 = new ArrayList();
    private ArrayList<AnswerBean> list4 = new ArrayList();
    private ArrayList<AnswerBean> list5 = new ArrayList();
    private ArrayList<AnswerBean> list6 = new ArrayList();
    private ArrayList<AnswerBean> list7 = new ArrayList();
    private ArrayList<AnswerBean> list8 = new ArrayList();
    private ArrayList<AnswerBean> list9 = new ArrayList();

    private HintSelectPopup hintSelectPopup;
    private SuccessViewHintPopup successHintPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yaoshan;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        mRadioSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radio_one:
                        //点击左面的处理
                        sex = "男";
                        break;
                    case R.id.radio_two:
                        //点击右面的处理
                        sex = "nv";
                        break;
                }
            }
        });
        mRadioAge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                avgPhase = radioButton.getText().toString();

            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();
        mIvLeft.setOnClickListener(this);
        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            onBackPressed();

        } else if (v.getId() == R.id.tv_one) {
            if (mLayoutSex.getVisibility() == View.VISIBLE &&
                    mLayoutAge.getVisibility() == View.VISIBLE) {
                if (TextUtils.isEmpty(sex)) {
                    ToastUtils.showShort("第1题未完成，请完成所有题目，以免影响最终的评分");
                } else if (TextUtils.isEmpty(avgPhase)) {
                    ToastUtils.showShort("第2题未完成，请完成所有题目，以免影响最终的评分");
                } else {
                    //开始测试
                    tv_one.setText("上一组测试");
                    oneView();
                }


            } else if (mLinOne.getVisibility() == View.VISIBLE) {
                //回到上一个开始测试引导题
                tv_one.setText("开始测试");
                tjq.setText("通过体质检测可以了解自身的身体状况，旨在为体质辨识及与中医体质相关疾病的防治、养生保健、健康管理提供依据，使体质分类科学化、规范化。体质测试为我们日常辨别养生常识的真伪提供了强有力的证据。可以帮助大家维护健康，早发现，早调理，早预防。");
                mLayoutSex.setVisibility(View.VISIBLE);
                mLayoutAge.setVisibility(View.VISIBLE);

                tv_two.setVisibility(View.GONE);

                mLinOne.setVisibility(View.GONE);
                lin_two.setVisibility(View.GONE);
                lin_three.setVisibility(View.GONE);
                lin_four.setVisibility(View.GONE);
                lin_five.setVisibility(View.GONE);
                lin_six.setVisibility(View.GONE);
                lin_seven.setVisibility(View.GONE);
                lin_eight.setVisibility(View.GONE);
                lin_nine.setVisibility(View.GONE);
            } else if (lin_two.getVisibility() == View.VISIBLE) {
                //当前第二题  回到第一题
                tv_one.setText("上一组测试");
                tv_two.setText("下一组测试");
                oneView();

            } else if (lin_three.getVisibility() == View.VISIBLE) {
                //当前第三题  回到第二题
                tv_one.setText("上一组测试");
                tv_two.setText("下一组测试");
                twoView();
            } else if (lin_four.getVisibility() == View.VISIBLE) {
                //当前第四题  回到第三题
                tv_one.setText("上一组测试");
                tv_two.setText("下一组测试");
                threeView();
            } else if (lin_five.getVisibility() == View.VISIBLE) {
                //当前第五题  回到第四题
                tv_one.setText("上一组测试");
                tv_two.setText("下一组测试");
                fourView();
            } else if (lin_six.getVisibility() == View.VISIBLE) {
                //当前第六题  回到第五题
                tv_one.setText("上一组测试");
                tv_two.setText("下一组测试");
                fiveView();
            } else if (lin_seven.getVisibility() == View.VISIBLE) {
                //当前第七题  回到第六题
                tv_one.setText("上一组测试");
                tv_two.setText("下一组测试");
                sixView();
            } else if (lin_eight.getVisibility() == View.VISIBLE) {
                //当前第八题  回到第七题
                tv_one.setText("上一组测试");
                tv_two.setText("下一组测试");
                sevenView();
            } else if (lin_nine.getVisibility() == View.VISIBLE) {
                //当前第九题  回到第八题
                tv_one.setText("上一组测试");
                tv_two.setText("下一组测试");
                eightView();
            }

        } else if (v.getId() == R.id.tv_two) {
            if (mLinOne.getVisibility() == View.VISIBLE) {
                list1.clear();
                if (mRadioOneSet.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第1题未完成，请完成所有题目，以免影响最终的评分");
                } else if (mRadioOneSetTwo.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第2题未完成，请完成所有题目，以免影响最终的评分");
                } else if (mRadioOneSetThree.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第3题未完成，请完成所有题目，以免影响最终的评分");
                } else if (mRadioOneSetFour.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第4题未完成，请完成所有题目，以免影响最终的评分");
                } else if (mRadioOneSetFive.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第5题未完成，请完成所有题目，以免影响最终的评分");
                } else if (mRadioOneSetSix.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第6题未完成，请完成所有题目，以免影响最终的评分");
                } else if (mRadioOneSetSeven.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第7题未完成，请完成所有题目，以免影响最终的评分");
                } else {
                    //第一题
                    AnswerBean answerBean = new AnswerBean();
                    switch (mRadioOneSet.getCheckedRadioButtonId()) {

                        case R.id.one_set_one:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(1);
                            break;
                        case R.id.one_set_two:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(2);
                            break;
                        case R.id.one_set_three:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(3);
                            break;
                        case R.id.one_set_four:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(4);
                            break;
                        case R.id.one_set_five:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(5);
                            break;
                    }
                    list1.add(answerBean);

                    //第二题
                    AnswerBean answerBean2 = new AnswerBean();
                    switch (mRadioOneSetTwo.getCheckedRadioButtonId()) {

                        case R.id.one_set_two_one:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(1);
                            break;
                        case R.id.one_set_two_two:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(2);
                            break;
                        case R.id.one_set_two_three:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(3);
                            break;
                        case R.id.one_set_two_four:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(4);
                            break;
                        case R.id.one_set_two_five:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(5);
                            break;
                    }
                    list1.add(answerBean2);

                    //第三题
                    AnswerBean answerBean3 = new AnswerBean();
                    switch (mRadioOneSetThree.getCheckedRadioButtonId()) {

                        case R.id.one_set_three_one:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(1);
                            break;
                        case R.id.one_set_three_two:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(2);
                            break;
                        case R.id.one_set_three_three:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(3);
                            break;
                        case R.id.one_set_three_four:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(4);
                            break;
                        case R.id.one_set_three_five:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(5);
                            break;
                    }
                    list1.add(answerBean3);

                    //第四题
                    AnswerBean answerBean4 = new AnswerBean();
                    switch (mRadioOneSetFour.getCheckedRadioButtonId()) {

                        case R.id.one_set_four_one:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(1);
                            break;
                        case R.id.one_set_four_two:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(2);
                            break;
                        case R.id.one_set_four_three:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(3);
                            break;
                        case R.id.one_set_four_four:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(4);
                            break;
                        case R.id.one_set_four_five:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(5);
                            break;
                    }
                    list1.add(answerBean4);
                    //第五题
                    AnswerBean answerBean5 = new AnswerBean();
                    switch (mRadioOneSetFive.getCheckedRadioButtonId()) {

                        case R.id.one_set_five_one:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(1);
                            break;
                        case R.id.one_set_five_two:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(2);
                            break;
                        case R.id.one_set_five_three:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(3);
                            break;
                        case R.id.one_set_five_four:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(4);
                            break;
                        case R.id.one_set_five_five:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(5);
                            break;
                    }
                    list1.add(answerBean5);
                    //第六题
                    AnswerBean answerBean6 = new AnswerBean();
                    switch (mRadioOneSetSix.getCheckedRadioButtonId()) {

                        case R.id.one_set_six_one:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(1);
                            break;
                        case R.id.one_set_six_two:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(2);
                            break;
                        case R.id.one_set_six_three:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(3);
                            break;
                        case R.id.one_set_six_four:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(4);
                            break;
                        case R.id.one_set_six_five:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(5);
                            break;
                    }
                    list1.add(answerBean6);
                    //第七题
                    AnswerBean answerBean7 = new AnswerBean();
                    switch (mRadioOneSetSeven.getCheckedRadioButtonId()) {

                        case R.id.one_set_seven_one:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(1);
                            break;
                        case R.id.one_set_seven_two:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(2);
                            break;
                        case R.id.one_set_seven_three:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(3);
                            break;
                        case R.id.one_set_seven_four:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(4);
                            break;
                        case R.id.one_set_seven_five:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(5);
                            break;
                    }
                    list1.add(answerBean7);

                    Log.e("sssss", new Gson().toJson(list1));
                    //当前是第一题 去第二题
                    twoView();
                    tv_two.setText("下一组测试");
                }
            } else if (lin_two.getVisibility() == View.VISIBLE) {
                list2.clear();
                if (radio_two_set.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第1题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_two_set_one.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第2题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_two_set_two.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第3题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_two_set_three.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第4题未完成，请完成所有题目，以免影响最终的评分");
                } else if (mRadioTwoSetFour.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第5题未完成，请完成所有题目，以免影响最终的评分");
                } else if (mRadioTwoSetFive.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第6题未完成，请完成所有题目，以免影响最终的评分");
                } else if (mRadioTwoSetSix.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第7题未完成，请完成所有题目，以免影响最终的评分");
                } else if (mRadioTwoSetSeven.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第8题未完成，请完成所有题目，以免影响最终的评分");
                } else {
                    //第一题
                    AnswerBean answerBean = new AnswerBean();
                    switch (radio_two_set.getCheckedRadioButtonId()) {

                        case R.id.two_set_one:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(1);
                            break;
                        case R.id.two_set_two:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(2);
                            break;
                        case R.id.two_set_three:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(3);
                            break;
                        case R.id.two_set_four:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(4);
                            break;
                        case R.id.two_set_five:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(5);
                            break;

                    }
                    list2.add(answerBean);

                    //第二题
                    AnswerBean answerBean2 = new AnswerBean();
                    switch (radio_two_set_one.getCheckedRadioButtonId()) {

                        case R.id.two_set_one_one:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(1);
                            break;
                        case R.id.two_set_one_two:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(2);
                            break;
                        case R.id.two_set_one_three:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(3);
                            break;
                        case R.id.two_set_one_four:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(4);
                            break;
                        case R.id.two_set_one_five:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(5);
                            break;
                    }
                    list2.add(answerBean2);
                    //第三题
                    AnswerBean answerBean3 = new AnswerBean();
                    switch (radio_two_set_two.getCheckedRadioButtonId()) {

                        case R.id.two_set_two_one:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(1);
                            break;
                        case R.id.two_set_two_two:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(2);
                            break;
                        case R.id.two_set_two_three:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(3);
                            break;
                        case R.id.two_set_two_four:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(4);
                            break;
                        case R.id.two_set_two_five:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(5);
                            break;
                    }
                    list2.add(answerBean3);
                    //第四题
                    AnswerBean answerBean4 = new AnswerBean();
                    switch (radio_two_set_three.getCheckedRadioButtonId()) {

                        case R.id.two_set_three_one:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(1);
                            break;
                        case R.id.two_set_three_two:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(2);
                            break;
                        case R.id.two_set_three_three:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(3);
                            break;
                        case R.id.two_set_three_four:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(4);
                            break;
                        case R.id.two_set_three_five:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(5);
                            break;
                    }
                    list2.add(answerBean4);
                    //第五题
                    AnswerBean answerBean5 = new AnswerBean();
                    switch (mRadioTwoSetFour.getCheckedRadioButtonId()) {

                        case R.id.two_set_four_one:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(1);
                            break;
                        case R.id.two_set_four_two:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(2);
                            break;
                        case R.id.two_set_four_three:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(3);
                            break;
                        case R.id.two_set_four_four:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(4);
                            break;
                        case R.id.two_set_four_five:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(5);
                            break;
                    }
                    list2.add(answerBean5);
                    //第六题
                    AnswerBean answerBean6 = new AnswerBean();
                    switch (mRadioTwoSetFive.getCheckedRadioButtonId()) {

                        case R.id.two_set_five_one:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(1);
                            break;
                        case R.id.two_set_five_two:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(2);
                            break;
                        case R.id.two_set_five_three:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(3);
                            break;
                        case R.id.two_set_five_four:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(4);
                            break;
                        case R.id.two_set_five_five:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(5);
                            break;
                    }
                    list2.add(answerBean6);
                    //第七题
                    AnswerBean answerBean7 = new AnswerBean();
                    switch (mRadioTwoSetSix.getCheckedRadioButtonId()) {

                        case R.id.two_set_six_one:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(1);
                            break;
                        case R.id.two_set_six_two:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(2);
                            break;
                        case R.id.two_set_six_three:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(3);
                            break;
                        case R.id.two_set_six_four:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(4);
                            break;
                        case R.id.two_set_six_five:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(5);
                            break;
                    }
                    list2.add(answerBean7);
                    //第八题
                    AnswerBean answerBean8 = new AnswerBean();
                    switch (mRadioTwoSetSeven.getCheckedRadioButtonId()) {

                        case R.id.two_set_seven_one:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(1);
                            break;
                        case R.id.two_set_seven_two:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(2);
                            break;
                        case R.id.two_set_seven_three:
                            answerBean8.setTopicNo(87);
                            answerBean8.setTopicResult(3);
                            break;
                        case R.id.two_set_seven_four:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(4);
                            break;
                        case R.id.two_set_seven_five:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(5);
                            break;
                    }
                    list2.add(answerBean8);

                    //当前是第二组 去第三组
                    Log.e("sssss2", new Gson().toJson(list2));
                    threeView();
                    tv_two.setText("下一组测试");
                }

            } else if (lin_three.getVisibility() == View.VISIBLE) {
                list3.clear();
                if (radio_three_set.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第1题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_three_set_one.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第2题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_three_set_two.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第3题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_three_set_three.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第4题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_three_set_four.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第5题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_three_set_five.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第6题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_three_set_six.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第7题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_three_set_seven.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第8题未完成，请完成所有题目，以免影响最终的评分");
                } else {
                    //第一题
                    AnswerBean answerBean = new AnswerBean();
                    switch (radio_three_set.getCheckedRadioButtonId()) {

                        case R.id.three_set_one:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(1);
                            break;
                        case R.id.three_set_two:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(2);
                            break;
                        case R.id.three_set_three:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(3);
                            break;
                        case R.id.three_set_four:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(4);
                            break;
                        case R.id.three_set_five:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(5);
                            break;
                    }
                    list3.add(answerBean);

                    //第二题
                    AnswerBean answerBean2 = new AnswerBean();
                    switch (radio_three_set_one.getCheckedRadioButtonId()) {

                        case R.id.three_set_one_one:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(1);
                            break;
                        case R.id.three_set_one_two:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(2);
                            break;
                        case R.id.three_set_one_three:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(3);
                            break;
                        case R.id.three_set_one_four:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(4);
                            break;
                        case R.id.three_set_one_five:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(5);
                            break;
                    }
                    list3.add(answerBean2);

                    //第三题
                    AnswerBean answerBean3 = new AnswerBean();
                    switch (radio_three_set_two.getCheckedRadioButtonId()) {

                        case R.id.three_set_two_one:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(1);
                            break;
                        case R.id.three_set_two_two:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(2);
                            break;
                        case R.id.three_set_two_three:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(3);
                            break;
                        case R.id.three_set_two_four:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(4);
                            break;
                        case R.id.three_set_two_five:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(5);
                            break;
                    }
                    list3.add(answerBean3);

                    //第四题
                    AnswerBean answerBean4 = new AnswerBean();
                    switch (radio_three_set_three.getCheckedRadioButtonId()) {

                        case R.id.three_set_three_one:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(1);
                            break;
                        case R.id.three_set_three_two:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(2);
                            break;
                        case R.id.three_set_three_three:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(3);
                            break;
                        case R.id.three_set_three_four:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(4);
                            break;
                        case R.id.three_set_three_five:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(5);
                            break;
                    }
                    list3.add(answerBean4);

                    //第五题
                    AnswerBean answerBean5 = new AnswerBean();
                    switch (radio_three_set_four.getCheckedRadioButtonId()) {

                        case R.id.three_set_four_one:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(1);
                            break;
                        case R.id.three_set_four_two:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(2);
                            break;
                        case R.id.three_set_four_three:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(3);
                            break;
                        case R.id.three_set_four_four:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(4);
                            break;
                        case R.id.three_set_four_five:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(5);
                            break;
                    }
                    list3.add(answerBean5);
                    //第六题
                    AnswerBean answerBean6 = new AnswerBean();
                    switch (radio_three_set_five.getCheckedRadioButtonId()) {

                        case R.id.three_set_five_one:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(1);
                            break;
                        case R.id.three_set_five_two:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(2);
                            break;
                        case R.id.three_set_five_three:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(3);
                            break;
                        case R.id.three_set_five_four:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(4);
                            break;
                        case R.id.three_set_five_five:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(5);
                            break;
                    }
                    list3.add(answerBean6);
                    //第七题
                    AnswerBean answerBean7 = new AnswerBean();
                    switch (radio_three_set_six.getCheckedRadioButtonId()) {

                        case R.id.three_set_six_one:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(1);
                            break;
                        case R.id.three_set_six_two:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(2);
                            break;
                        case R.id.three_set_six_three:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(3);
                            break;
                        case R.id.three_set_six_four:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(4);
                            break;
                        case R.id.three_set_six_five:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(5);
                            break;
                    }
                    list3.add(answerBean7);
                    //第八题
                    AnswerBean answerBean8 = new AnswerBean();
                    switch (radio_three_set_seven.getCheckedRadioButtonId()) {

                        case R.id.three_set_seven_one:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(1);
                            break;
                        case R.id.three_set_seven_two:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(2);
                            break;
                        case R.id.three_set_seven_three:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(3);
                            break;
                        case R.id.three_set_seven_four:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(4);
                            break;
                        case R.id.three_set_seven_five:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(5);
                            break;
                    }
                    list3.add(answerBean8);


                    //当前是第三组 去第四组
                    Log.e("sssss3", new Gson().toJson(list3));
                    fourView();
                    tv_two.setText("下一组测试");
                }

            } else if (lin_four.getVisibility() == View.VISIBLE) {
                list4.clear();
                if (radio_four_set.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第1题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_four_set_one.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第2题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_four_set_two.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第3题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_four_set_three.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第4题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_four_set_four.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第5题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_four_set_five.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第6题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_four_set_six.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第7题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_four_set_seven.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第8题未完成，请完成所有题目，以免影响最终的评分");
                } else {
                    //第一题
                    AnswerBean answerBean = new AnswerBean();
                    switch (radio_four_set.getCheckedRadioButtonId()) {

                        case R.id.four_set_one:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(1);
                            break;
                        case R.id.four_set_two:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(2);
                            break;
                        case R.id.four_set_three:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(3);
                            break;
                        case R.id.four_set_four:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(4);
                            break;
                        case R.id.four_set_five:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(5);
                            break;
                    }
                    list4.add(answerBean);
                    //第二题
                    AnswerBean answerBean2 = new AnswerBean();
                    switch (radio_four_set_one.getCheckedRadioButtonId()) {

                        case R.id.four_set_one_one:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(1);
                            break;
                        case R.id.four_set_one_two:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(2);
                            break;
                        case R.id.four_set_one_three:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(3);
                            break;
                        case R.id.four_set_one_four:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(4);
                            break;
                        case R.id.four_set_one_five:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(5);
                            break;
                    }
                    list4.add(answerBean2);
                    //第三题
                    AnswerBean answerBean3 = new AnswerBean();
                    switch (radio_four_set_two.getCheckedRadioButtonId()) {

                        case R.id.four_set_two_one:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(1);
                            break;
                        case R.id.four_set_two_two:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(2);
                            break;
                        case R.id.four_set_two_three:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(3);
                            break;
                        case R.id.four_set_two_four:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(4);
                            break;
                        case R.id.four_set_two_five:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(5);
                            break;
                    }
                    list4.add(answerBean3);
                    //第四题
                    AnswerBean answerBean4 = new AnswerBean();
                    switch (radio_four_set_three.getCheckedRadioButtonId()) {
                        case R.id.four_set_three_one:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(1);
                            break;
                        case R.id.four_set_three_two:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(2);
                            break;
                        case R.id.four_set_three_three:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(3);
                            break;
                        case R.id.four_set_three_four:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(4);
                            break;
                        case R.id.four_set_three_five:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(5);
                            break;
                    }
                    list4.add(answerBean4);
                    //第五题
                    AnswerBean answerBean5 = new AnswerBean();
                    switch (radio_four_set_four.getCheckedRadioButtonId()) {

                        case R.id.four_set_four_one:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(1);
                            break;
                        case R.id.four_set_four_two:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(2);
                            break;
                        case R.id.four_set_four_three:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(3);
                            break;
                        case R.id.four_set_four_four:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(4);
                            break;
                        case R.id.four_set_four_five:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(5);
                            break;
                    }
                    list4.add(answerBean5);
                    //第六题
                    AnswerBean answerBean6 = new AnswerBean();
                    switch (radio_four_set_five.getCheckedRadioButtonId()) {

                        case R.id.four_set_five_one:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(1);
                            break;
                        case R.id.four_set_five_two:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(2);
                            break;
                        case R.id.four_set_five_three:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(3);
                            break;
                        case R.id.four_set_five_four:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(4);
                            break;
                        case R.id.four_set_five_five:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(5);
                            break;
                    }
                    list4.add(answerBean6);
                    //第七题
                    AnswerBean answerBean7 = new AnswerBean();
                    switch (radio_four_set_six.getCheckedRadioButtonId()) {

                        case R.id.four_set_six_one:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(1);
                            break;
                        case R.id.four_set_six_two:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(2);
                            break;
                        case R.id.four_set_six_three:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(3);
                            break;
                        case R.id.four_set_six_four:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(4);
                            break;
                        case R.id.four_set_six_five:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(5);
                            break;
                    }
                    list4.add(answerBean7);
                    //第八题
                    AnswerBean answerBean8 = new AnswerBean();
                    switch (radio_four_set_seven.getCheckedRadioButtonId()) {

                        case R.id.four_set_seven_one:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(1);
                            break;
                        case R.id.four_set_seven_two:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(2);
                            break;
                        case R.id.four_set_seven_three:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(3);
                            break;
                        case R.id.four_set_seven_four:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(4);
                            break;
                        case R.id.four_set_seven_five:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(5);
                            break;
                    }
                    list4.add(answerBean8);
                    //当前是第四组 去第五组
                    Log.e("sssss4", new Gson().toJson(list4));
                    fiveView();
                    tv_two.setText("下一组测试");
                }
            } else if (lin_five.getVisibility() == View.VISIBLE) {
                list5.clear();
                if (radio_five_set.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第1题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_five_set_one.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第2题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_five_set_two.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第3题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_five_set_three.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第4题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_five_set_four.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第5题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_five_set_five.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第6题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_five_set_six.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第7题未完成，请完成所有题目，以免影响最终的评分");
                } else {
                    //第一题
                    AnswerBean answerBean = new AnswerBean();
                    switch (radio_five_set.getCheckedRadioButtonId()) {

                        case R.id.five_set_one:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(1);
                            break;
                        case R.id.five_set_two:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(2);
                            break;
                        case R.id.five_set_three:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(3);
                            break;
                        case R.id.five_set_four:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(4);
                            break;
                        case R.id.five_set_five:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(5);
                            break;
                    }
                    list5.add(answerBean);

                    //第二题
                    AnswerBean answerBean2 = new AnswerBean();
                    switch (radio_five_set_one.getCheckedRadioButtonId()) {

                        case R.id.five_set_one_one:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(1);
                            break;
                        case R.id.five_set_one_two:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(2);
                            break;
                        case R.id.five_set_one_three:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(3);
                            break;
                        case R.id.five_set_one_four:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(4);
                            break;
                        case R.id.five_set_one_five:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(5);
                            break;
                    }
                    list5.add(answerBean2);

                    //第三题
                    AnswerBean answerBean3 = new AnswerBean();
                    switch (radio_five_set_two.getCheckedRadioButtonId()) {

                        case R.id.five_set_two_one:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(1);
                            break;
                        case R.id.five_set_two_two:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(2);
                            break;
                        case R.id.five_set_two_three:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(3);
                            break;
                        case R.id.five_set_two_four:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(4);
                            break;
                        case R.id.five_set_two_five:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(5);
                            break;
                    }
                    list5.add(answerBean3);

                    //第四题
                    AnswerBean answerBean4 = new AnswerBean();
                    switch (radio_five_set_three.getCheckedRadioButtonId()) {

                        case R.id.five_set_three_one:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(1);
                            break;
                        case R.id.five_set_three_two:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(2);
                            break;
                        case R.id.five_set_three_three:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(3);
                            break;
                        case R.id.five_set_three_four:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(4);
                            break;
                        case R.id.five_set_three_five:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(5);
                            break;
                    }
                    list5.add(answerBean4);

                    //第五题
                    AnswerBean answerBean5 = new AnswerBean();
                    switch (radio_five_set_four.getCheckedRadioButtonId()) {

                        case R.id.five_set_four_one:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(1);
                            break;
                        case R.id.five_set_four_two:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(2);
                            break;
                        case R.id.five_set_four_three:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(3);
                            break;
                        case R.id.five_set_four_four:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(4);
                            break;
                        case R.id.five_set_four_five:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(5);
                            break;
                    }
                    list5.add(answerBean5);
                    //第六题
                    AnswerBean answerBean6 = new AnswerBean();
                    switch (radio_five_set_five.getCheckedRadioButtonId()) {

                        case R.id.five_set_five_one:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(1);
                            break;
                        case R.id.five_set_five_two:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(2);
                            break;
                        case R.id.five_set_five_three:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(3);
                            break;
                        case R.id.five_set_five_four:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(4);
                            break;
                        case R.id.five_set_five_five:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(5);
                            break;
                    }
                    list5.add(answerBean6);
                    //第七题
                    AnswerBean answerBean7 = new AnswerBean();
                    switch (radio_five_set_six.getCheckedRadioButtonId()) {

                        case R.id.five_set_six_one:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(1);
                            break;
                        case R.id.five_set_six_two:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(2);
                            break;
                        case R.id.five_set_six_three:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(3);
                            break;
                        case R.id.five_set_six_four:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(4);
                            break;
                        case R.id.five_set_six_five:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(5);
                            break;
                    }
                    list5.add(answerBean7);

                    Log.e("sssss5", new Gson().toJson(list5));

                    //当前是第五组 去第六组
                    sixView();
                    tv_two.setText("下一组测试");
                }

            } else if (lin_six.getVisibility() == View.VISIBLE) {
                list6.clear();
                if (radio_six_set.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第1题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_six_set_one.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第2题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_six_set_two.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第3题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_six_set_three.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第4题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_six_set_four.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第5题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_six_set_five.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第6题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_six_set_six.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第7题未完成，请完成所有题目，以免影响最终的评分");
                } else {
                    //第一题
                    AnswerBean answerBean = new AnswerBean();
                    switch (radio_six_set.getCheckedRadioButtonId()) {

                        case R.id.six_set_one:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(1);
                            break;
                        case R.id.six_set_two:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(2);
                            break;
                        case R.id.six_set_three:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(3);
                            break;
                        case R.id.six_set_four:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(4);
                            break;
                        case R.id.six_set_five:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(5);
                            break;

                    }
                    list6.add(answerBean);

                    //第二题
                    AnswerBean answerBean2 = new AnswerBean();
                    switch (radio_six_set_one.getCheckedRadioButtonId()) {

                        case R.id.six_set_one_one:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(1);
                            break;
                        case R.id.six_set_one_two:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(2);
                            break;
                        case R.id.six_set_one_three:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(3);
                            break;
                        case R.id.six_set_one_four:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(4);
                            break;
                        case R.id.six_set_one_five:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(5);
                            break;
                    }
                    list6.add(answerBean2);

                    //第三题
                    AnswerBean answerBean3 = new AnswerBean();
                    switch (radio_six_set_two.getCheckedRadioButtonId()) {

                        case R.id.six_set_two_one:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(1);
                            break;
                        case R.id.six_set_two_two:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(2);
                            break;
                        case R.id.six_set_two_three:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(3);
                            break;
                        case R.id.six_set_two_four:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(4);
                            break;
                        case R.id.six_set_two_five:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(5);
                            break;
                    }
                    list6.add(answerBean3);

                    //第四题
                    AnswerBean answerBean4 = new AnswerBean();
                    switch (radio_six_set_three.getCheckedRadioButtonId()) {

                        case R.id.six_set_three_one:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(1);
                            break;
                        case R.id.six_set_three_two:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(2);
                            break;
                        case R.id.six_set_three_three:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(3);
                            break;
                        case R.id.six_set_three_four:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(4);
                            break;
                        case R.id.six_set_three_five:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(5);
                            break;
                    }
                    list6.add(answerBean4);

                    //第五题
                    AnswerBean answerBean5 = new AnswerBean();
                    switch (radio_six_set_four.getCheckedRadioButtonId()) {

                        case R.id.six_set_four_one:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(1);
                            break;
                        case R.id.six_set_four_two:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(2);
                            break;
                        case R.id.six_set_four_three:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(3);
                            break;
                        case R.id.six_set_four_four:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(4);
                            break;
                        case R.id.six_set_four_five:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(5);
                            break;
                    }
                    list6.add(answerBean5);
                    //第六题
                    AnswerBean answerBean6 = new AnswerBean();
                    switch (radio_six_set_five.getCheckedRadioButtonId()) {

                        case R.id.six_set_five_one:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(1);
                            break;
                        case R.id.six_set_five_two:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(2);
                            break;
                        case R.id.six_set_five_three:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(3);
                            break;
                        case R.id.six_set_five_four:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(4);
                            break;
                        case R.id.six_set_five_five:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(5);
                            break;
                    }
                    list6.add(answerBean6);
                    //第七题
                    AnswerBean answerBean7 = new AnswerBean();
                    switch (radio_six_set_six.getCheckedRadioButtonId()) {

                        case R.id.six_set_six_one:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(1);
                            break;
                        case R.id.six_set_six_two:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(2);
                            break;
                        case R.id.six_set_six_three:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(3);
                            break;
                        case R.id.six_set_six_four:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(4);
                            break;
                        case R.id.six_set_six_five:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(5);
                            break;
                    }
                    list6.add(answerBean7);
                    Log.e("sssss6", new Gson().toJson(list6));
                    //当前是第六组 去第七组
                    sevenView();
                    tv_two.setText("下一组测试");
                }

            } else if (lin_seven.getVisibility() == View.VISIBLE) {
                list7.clear();
                if (radio_seven_set.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第1题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_seven_set_one.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第2题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_seven_set_two.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第3题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_seven_set_three.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第4题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_seven_set_four.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第5题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_seven_set_five.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第6题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_seven_set_six.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第7题未完成，请完成所有题目，以免影响最终的评分");
                } else {
                    //第一题
                    AnswerBean answerBean = new AnswerBean();
                    switch (radio_seven_set.getCheckedRadioButtonId()) {

                        case R.id.seven_set_one:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(1);
                            break;
                        case R.id.seven_set_two:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(2);
                            break;
                        case R.id.seven_set_three:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(3);
                            break;
                        case R.id.seven_set_four:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(4);
                            break;
                        case R.id.seven_set_five:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(5);
                            break;
                    }
                    list7.add(answerBean);
                    //第二题
                    AnswerBean answerBean2 = new AnswerBean();
                    switch (radio_seven_set_one.getCheckedRadioButtonId()) {

                        case R.id.seven_set_one_one:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(1);
                            break;
                        case R.id.seven_set_one_two:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(2);
                            break;
                        case R.id.seven_set_one_three:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(3);
                            break;
                        case R.id.seven_set_one_four:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(4);
                            break;
                        case R.id.seven_set_one_five:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(5);
                            break;
                    }
                    list7.add(answerBean2);
                    //第三题
                    AnswerBean answerBean3 = new AnswerBean();
                    switch (radio_seven_set_two.getCheckedRadioButtonId()) {

                        case R.id.seven_set_two_one:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(1);
                            break;
                        case R.id.seven_set_two_two:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(2);
                            break;
                        case R.id.seven_set_two_three:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(3);
                            break;
                        case R.id.seven_set_two_four:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(4);
                            break;
                        case R.id.seven_set_two_five:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(5);
                            break;
                    }
                    list7.add(answerBean3);

                    //第四题
                    AnswerBean answerBean4 = new AnswerBean();
                    switch (radio_seven_set_three.getCheckedRadioButtonId()) {

                        case R.id.seven_set_three_one:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(1);
                            break;
                        case R.id.seven_set_three_two:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(2);
                            break;
                        case R.id.seven_set_three_three:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(3);
                            break;
                        case R.id.seven_set_three_four:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(4);
                            break;
                        case R.id.seven_set_three_five:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(5);
                            break;
                    }
                    list7.add(answerBean4);

                    //第五题
                    AnswerBean answerBean5 = new AnswerBean();
                    switch (radio_seven_set_four.getCheckedRadioButtonId()) {

                        case R.id.seven_set_four_one:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(1);
                            break;
                        case R.id.seven_set_four_two:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(2);
                            break;
                        case R.id.seven_set_four_three:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(3);
                            break;
                        case R.id.seven_set_four_four:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(4);
                            break;
                        case R.id.seven_set_four_five:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(5);
                            break;
                    }
                    list7.add(answerBean5);
                    //第六题
                    AnswerBean answerBean6 = new AnswerBean();
                    switch (radio_seven_set_five.getCheckedRadioButtonId()) {

                        case R.id.seven_set_five_one:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(1);
                            break;
                        case R.id.seven_set_five_two:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(2);
                            break;
                        case R.id.seven_set_five_three:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(3);
                            break;
                        case R.id.seven_set_five_four:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(4);
                            break;
                        case R.id.seven_set_five_five:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(5);
                            break;
                    }
                    list7.add(answerBean6);
                    //第七题
                    AnswerBean answerBean7 = new AnswerBean();
                    switch (radio_seven_set_six.getCheckedRadioButtonId()) {

                        case R.id.seven_set_six_one:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(1);
                            break;
                        case R.id.seven_set_six_two:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(2);
                            break;
                        case R.id.seven_set_six_three:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(3);
                            break;
                        case R.id.seven_set_six_four:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(4);
                            break;
                        case R.id.seven_set_six_five:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(5);
                            break;
                    }
                    list7.add(answerBean7);

                    Log.e("sssss7", new Gson().toJson(list7));
                    //当前是第七组 去第八组
                    eightView();
                    tv_two.setText("下一组测试");
                }
            } else if (lin_eight.getVisibility() == View.VISIBLE) {
                list8.clear();
                if (radio_eight_set.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第1题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_eight_set_one.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第2题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_eight_set_two.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第3题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_eight_set_three.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第4题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_eight_set_four.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第5题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_eight_set_five.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第6题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_eight_set_six.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第7题未完成，请完成所有题目，以免影响最终的评分");
                } else {
                    //第一题
                    AnswerBean answerBean = new AnswerBean();
                    switch (radio_eight_set.getCheckedRadioButtonId()) {

                        case R.id.eight_set_one:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(1);
                            break;
                        case R.id.eight_set_two:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(2);
                            break;
                        case R.id.eight_set_three:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(3);
                            break;
                        case R.id.eight_set_four:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(4);
                            break;
                        case R.id.eight_set_five:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(5);
                            break;
                    }
                    list8.add(answerBean);
                    //第二题
                    AnswerBean answerBean2 = new AnswerBean();
                    switch (radio_eight_set_one.getCheckedRadioButtonId()) {

                        case R.id.eight_set_one_one:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(1);
                            break;
                        case R.id.eight_set_one_two:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(2);
                            break;
                        case R.id.eight_set_one_three:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(3);
                            break;
                        case R.id.eight_set_one_four:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(4);
                            break;
                        case R.id.eight_set_one_five:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(5);
                            break;
                    }
                    list8.add(answerBean2);

                    //第三题
                    AnswerBean answerBean3 = new AnswerBean();
                    switch (radio_eight_set_two.getCheckedRadioButtonId()) {

                        case R.id.eight_set_two_one:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(1);
                            break;
                        case R.id.eight_set_two_two:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(2);
                            break;
                        case R.id.eight_set_two_three:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(3);
                            break;
                        case R.id.eight_set_two_four:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(4);
                            break;
                        case R.id.eight_set_two_five:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(5);
                            break;
                    }
                    list8.add(answerBean3);

                    //第四题
                    AnswerBean answerBean4 = new AnswerBean();
                    switch (radio_eight_set_three.getCheckedRadioButtonId()) {

                        case R.id.eight_set_three_one:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(1);
                            break;
                        case R.id.eight_set_three_two:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(2);
                            break;
                        case R.id.eight_set_three_three:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(3);
                            break;
                        case R.id.eight_set_three_four:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(4);
                            break;
                        case R.id.eight_set_three_five:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(5);
                            break;
                    }
                    list8.add(answerBean4);

                    //第五题
                    AnswerBean answerBean5 = new AnswerBean();
                    switch (radio_eight_set_four.getCheckedRadioButtonId()) {

                        case R.id.eight_set_four_one:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(1);
                            break;
                        case R.id.eight_set_four_two:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(2);
                            break;
                        case R.id.eight_set_four_three:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(3);
                            break;
                        case R.id.eight_set_four_four:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(4);
                            break;
                        case R.id.eight_set_four_five:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(5);
                            break;
                    }
                    list8.add(answerBean5);
                    //第六题
                    AnswerBean answerBean6 = new AnswerBean();
                    switch (radio_eight_set_five.getCheckedRadioButtonId()) {

                        case R.id.eight_set_five_one:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(1);
                            break;
                        case R.id.eight_set_five_two:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(2);
                            break;
                        case R.id.eight_set_five_three:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(3);
                            break;
                        case R.id.eight_set_five_four:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(4);
                            break;
                        case R.id.eight_set_five_five:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(5);
                            break;
                    }
                    list8.add(answerBean6);
                    //第七题
                    AnswerBean answerBean7 = new AnswerBean();
                    switch (radio_eight_set_six.getCheckedRadioButtonId()) {

                        case R.id.eight_set_six_one:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(1);
                            break;
                        case R.id.eight_set_six_two:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(2);
                            break;
                        case R.id.eight_set_six_three:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(3);
                            break;
                        case R.id.eight_set_six_four:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(4);
                            break;
                        case R.id.eight_set_six_five:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(5);
                            break;
                    }
                    list8.add(answerBean7);

                    Log.e("sssss8", new Gson().toJson(list8));
                    //当前是第八组 去第九组
                    nineView();
                    tv_two.setText("查看结果");
                }
            } else if (lin_nine.getVisibility() == View.VISIBLE) {
                //去查看结果
                list9.clear();
                if (radio_nine_set.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第1题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_nine_set_one.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第2题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_nine_set_two.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第3题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_nine_set_three.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第4题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_nine_set_four.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第5题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_nine_set_five.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第6题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_nine_set_six.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第7题未完成，请完成所有题目，以免影响最终的评分");
                } else if (radio_nine_set_seven.getCheckedRadioButtonId() == -1) {
                    ToastUtils.showShort("第8题未完成，请完成所有题目，以免影响最终的评分");
                } else {
                    //第一题
                    AnswerBean answerBean = new AnswerBean();
                    switch (radio_nine_set.getCheckedRadioButtonId()) {

                        case R.id.nine_set_one:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(1);
                            break;
                        case R.id.nine_set_two:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(2);
                            break;
                        case R.id.nine_set_three:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(3);
                            break;
                        case R.id.nine_set_four:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(4);
                            break;
                        case R.id.nine_set_five:
                            answerBean.setTopicNo(1);
                            answerBean.setTopicResult(5);
                            break;
                    }
                    list9.add(answerBean);

                    //第二题
                    AnswerBean answerBean2 = new AnswerBean();
                    switch (radio_nine_set_one.getCheckedRadioButtonId()) {

                        case R.id.nine_set_seven_one:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(1);
                            break;
                        case R.id.nine_set_seven_two:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(2);
                            break;
                        case R.id.nine_set_seven_three:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(3);
                            break;
                        case R.id.nine_set_seven_four:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(4);
                            break;
                        case R.id.nine_set_seven_five:
                            answerBean2.setTopicNo(2);
                            answerBean2.setTopicResult(5);
                            break;
                    }
                    list9.add(answerBean2);
                    //第三题
                    AnswerBean answerBean3 = new AnswerBean();
                    switch (radio_nine_set_two.getCheckedRadioButtonId()) {

                        case R.id.nine_set_two_one:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(1);
                            break;
                        case R.id.nine_set_two_two:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(2);
                            break;
                        case R.id.nine_set_two_three:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(3);
                            break;
                        case R.id.nine_set_two_four:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(4);
                            break;
                        case R.id.nine_set_two_five:
                            answerBean3.setTopicNo(3);
                            answerBean3.setTopicResult(5);
                            break;
                    }
                    list9.add(answerBean3);
                    //第四题
                    AnswerBean answerBean4 = new AnswerBean();
                    switch (radio_nine_set_three.getCheckedRadioButtonId()) {

                        case R.id.nine_set_three_one:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(1);
                            break;
                        case R.id.nine_set_three_two:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(2);
                            break;
                        case R.id.nine_set_three_three:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(3);
                            break;
                        case R.id.nine_set_three_four:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(4);
                            break;
                        case R.id.nine_set_three_five:
                            answerBean4.setTopicNo(4);
                            answerBean4.setTopicResult(5);
                            break;
                    }
                    list9.add(answerBean4);

                    //第五题
                    AnswerBean answerBean5 = new AnswerBean();
                    switch (radio_nine_set_four.getCheckedRadioButtonId()) {

                        case R.id.nine_set_four_one:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(1);
                            break;
                        case R.id.nine_set_four_two:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(2);
                            break;
                        case R.id.nine_set_four_three:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(3);
                            break;
                        case R.id.nine_set_four_four:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(4);
                            break;
                        case R.id.nine_set_four_five:
                            answerBean5.setTopicNo(5);
                            answerBean5.setTopicResult(5);
                            break;
                    }
                    list9.add(answerBean5);
                    //第六题
                    AnswerBean answerBean6 = new AnswerBean();
                    switch (radio_nine_set_five.getCheckedRadioButtonId()) {

                        case R.id.nine_set_five_one:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(1);
                            break;
                        case R.id.nine_set_five_two:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(2);
                            break;
                        case R.id.nine_set_five_three:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(3);
                            break;
                        case R.id.nine_set_five_four:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(4);
                            break;
                        case R.id.nine_set_five_five:
                            answerBean6.setTopicNo(6);
                            answerBean6.setTopicResult(5);
                            break;
                    }
                    list9.add(answerBean6);
                    //第七题
                    AnswerBean answerBean7 = new AnswerBean();
                    switch (radio_nine_set_six.getCheckedRadioButtonId()) {

                        case R.id.nine_set_six_one:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(1);
                            break;
                        case R.id.nine_set_six_two:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(2);
                            break;
                        case R.id.nine_set_six_three:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(3);
                            break;
                        case R.id.nine_set_six_four:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(4);
                            break;
                        case R.id.nine_set_six_five:
                            answerBean7.setTopicNo(7);
                            answerBean7.setTopicResult(5);
                            break;
                    }
                    list9.add(answerBean7);
                    //第八题
                    AnswerBean answerBean8 = new AnswerBean();
                    switch (radio_nine_set_seven.getCheckedRadioButtonId()) {

                        case R.id.nine_set_seven_one:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(1);
                            break;
                        case R.id.nine_set_seven_two:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(2);
                            break;
                        case R.id.nine_set_seven_three:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(3);
                            break;
                        case R.id.nine_set_seven_four:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(4);
                            break;
                        case R.id.nine_set_seven_five:
                            answerBean8.setTopicNo(8);
                            answerBean8.setTopicResult(5);
                            break;
                    }
                    list9.add(answerBean8);
                    //当前是第九组 去查看结果
                    Log.e("sssss9", new Gson().toJson(list9) + sex + avgPhase);
                    toSave();
                }
            }
        }
    }


    private void toSave() {
        showWaitDialog("", false);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                hideWaitDialog();
        getSave();
//            }
//        }, 30000);//30秒后执行 检测接口

    }

    public void getSave() {
        RxHttp.postJson(Comments.TIZHI_INFO)
                .add("avgPhase", avgPhase)
                .add("sex", sex)
                .add("list1", list1)
                .add("list2", list2)
                .add("list3", list3)
                .add("list4", list4)
                .add("list5", list5)
                .add("list6", list6)
                .add("list7", list7)
                .add("list8", list8)
                .add("list9", list9)
                .asResponse(ChangShouListBean.class)

                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    hideWaitDialog();
                    if (model.getData() != null) {
                        if (successHintPopup == null) {
                            successHintPopup = new SuccessViewHintPopup(YaoShanActivity.this,
                                    model.getData().getTitle() + "\n" + model.getData().getContent(),
                                    "我知道了", new SuccessViewHintPopup.onClickDone() {
                                @Override
                                public void selectAffrim() {
                                    successHintPopup.dismiss();
                                    finish();
                                }
                            });
                        }
                        new XPopup.Builder(YaoShanActivity.this)
                                .dismissOnBackPressed(false)
                                .dismissOnTouchOutside(false)
                                .asCustom(successHintPopup)
                                .show();
                    }
                }, (OnError) error -> {
                    hideWaitDialog();
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }

    private void oneView() {
        tjq.setText("【阳虚体质】体质测试" + "\n" +
                "注：【阳虚体质】性格多沉静、内向，表现为平素畏冷，手足不温，喜热饮食，精神不振，舌淡胖嫩，脉沉迟。");
        mScrollView.scrollTo(0, 0);
        mLayoutSex.setVisibility(View.GONE);
        mLayoutAge.setVisibility(View.GONE);

        tv_two.setVisibility(View.VISIBLE);
        mLinOne.setVisibility(View.VISIBLE);
        lin_two.setVisibility(View.GONE);
        lin_three.setVisibility(View.GONE);
        lin_four.setVisibility(View.GONE);
        lin_five.setVisibility(View.GONE);
        lin_six.setVisibility(View.GONE);
        lin_seven.setVisibility(View.GONE);
        lin_eight.setVisibility(View.GONE);
        lin_nine.setVisibility(View.GONE);
    }

    private void twoView() {
        tjq.setText("【阴虚体质】体质测试" + "\n" +
                "注：【阴虚体质】性情急躁，外向好动，活泼，表现为手足心热，口燥咽干，鼻微干，喜冷饮，大便干燥，舌红少津，脉细数。");
        mScrollView.scrollTo(0, 0);
        mLayoutSex.setVisibility(View.GONE);
        mLayoutAge.setVisibility(View.GONE);
        tv_two.setVisibility(View.VISIBLE);
        mLinOne.setVisibility(View.GONE);
        lin_two.setVisibility(View.VISIBLE);
        lin_three.setVisibility(View.GONE);
        lin_four.setVisibility(View.GONE);
        lin_five.setVisibility(View.GONE);
        lin_six.setVisibility(View.GONE);
        lin_seven.setVisibility(View.GONE);
        lin_eight.setVisibility(View.GONE);
        lin_nine.setVisibility(View.GONE);
    }

    private void threeView() {
        tjq.setText("【气虚体质】体质测试" + "\n" +
                "注：【气虚体质】性格内向，不喜冒险，表现为平素语音低弱，气短懒言，容易疲乏，精神不振，易出汗，舌淡红，舌边有齿痕，脉弱。");
        mScrollView.scrollTo(0, 0);
        mLayoutSex.setVisibility(View.GONE);
        mLayoutAge.setVisibility(View.GONE);
        tv_two.setVisibility(View.VISIBLE);
        mLinOne.setVisibility(View.GONE);
        lin_two.setVisibility(View.GONE);
        lin_three.setVisibility(View.VISIBLE);
        lin_four.setVisibility(View.GONE);
        lin_five.setVisibility(View.GONE);
        lin_six.setVisibility(View.GONE);
        lin_seven.setVisibility(View.GONE);
        lin_eight.setVisibility(View.GONE);
        lin_nine.setVisibility(View.GONE);
    }

    private void fourView() {
        tjq.setText("【痰湿体质】体质测试" + "\n" +
                "注：【痰湿体质】性格偏温和、稳重，多善于忍耐，表现为面部皮肤油脂较多，多汗且黏，胸闷，痰多，口黏腻或甜，喜食肥甘甜黏，苔腻，脉滑。");
        mScrollView.scrollTo(0, 0);
        mLayoutSex.setVisibility(View.GONE);
        mLayoutAge.setVisibility(View.GONE);
        tv_two.setVisibility(View.VISIBLE);
        mLinOne.setVisibility(View.GONE);
        lin_two.setVisibility(View.GONE);
        lin_three.setVisibility(View.GONE);
        lin_four.setVisibility(View.VISIBLE);
        lin_five.setVisibility(View.GONE);
        lin_six.setVisibility(View.GONE);
        lin_seven.setVisibility(View.GONE);
        lin_eight.setVisibility(View.GONE);
        lin_nine.setVisibility(View.GONE);
    }

    private void fiveView() {
        tjq.setText("【湿热体质】体质测试" + "\n" +
                "注：【湿热体质】容易心烦急躁，表现为面垢油光，易生痤疮，口苦口干，身重困倦，大便黏滞不畅或燥结，小便短黄，男性易阴囊潮湿，女性易带下增多，舌质偏红，苔黄腻，脉滑数。");
        mScrollView.scrollTo(0, 0);
        mLayoutSex.setVisibility(View.GONE);
        mLayoutAge.setVisibility(View.GONE);
        tv_two.setVisibility(View.VISIBLE);
        mLinOne.setVisibility(View.GONE);
        lin_two.setVisibility(View.GONE);
        lin_three.setVisibility(View.GONE);
        lin_four.setVisibility(View.GONE);
        lin_five.setVisibility(View.VISIBLE);
        lin_six.setVisibility(View.GONE);
        lin_seven.setVisibility(View.GONE);
        lin_eight.setVisibility(View.GONE);
        lin_nine.setVisibility(View.GONE);
    }

    private void sixView() {
        tjq.setText("【血瘀体质】体质测试" + "\n" +
                "注：【血瘀体质】易烦，健忘，表现为肤色晦黯，色素沉着，容易出现瘀斑，口唇黯淡，舌黯或有瘀点，舌下络脉紫黯或增粗，脉涩。");
        mScrollView.scrollTo(0, 0);
        mLayoutSex.setVisibility(View.GONE);
        mLayoutAge.setVisibility(View.GONE);
        tv_two.setVisibility(View.VISIBLE);
        mLinOne.setVisibility(View.GONE);
        lin_two.setVisibility(View.GONE);
        lin_three.setVisibility(View.GONE);
        lin_four.setVisibility(View.GONE);
        lin_five.setVisibility(View.GONE);
        lin_six.setVisibility(View.VISIBLE);
        lin_seven.setVisibility(View.GONE);
        lin_eight.setVisibility(View.GONE);
        lin_nine.setVisibility(View.GONE);
    }

    private void sevenView() {
        tjq.setText("【特禀体质】体质测试" + "\n" +
                "注：【特禀体质】过敏体质者常见哮喘、风团、咽痒、鼻塞、喷嚏等；患遗传性疾病者有垂直遗传、先天性、家族性特征；患胎传性疾病者具有母体影响胎儿个体生长发育及相关疾病特征。");
        mScrollView.scrollTo(0, 0);
        mLayoutSex.setVisibility(View.GONE);
        mLayoutAge.setVisibility(View.GONE);
        tv_two.setVisibility(View.VISIBLE);
        mLinOne.setVisibility(View.GONE);
        lin_two.setVisibility(View.GONE);
        lin_three.setVisibility(View.GONE);
        lin_four.setVisibility(View.GONE);
        lin_five.setVisibility(View.GONE);
        lin_six.setVisibility(View.GONE);
        lin_seven.setVisibility(View.VISIBLE);
        lin_eight.setVisibility(View.GONE);
        lin_nine.setVisibility(View.GONE);
    }

    private void eightView() {
        tjq.setText("【气郁体质】体质测试" + "\n" +
                "注：【气郁体质】性格内向不稳定、敏感多虑，神情抑郁，情感脆弱，烦闷不乐，舌淡红，苔薄白，脉弦。");
        mScrollView.scrollTo(0, 0);
        mLayoutSex.setVisibility(View.GONE);
        mLayoutAge.setVisibility(View.GONE);
        tv_two.setVisibility(View.VISIBLE);
        mLinOne.setVisibility(View.GONE);
        lin_two.setVisibility(View.GONE);
        lin_three.setVisibility(View.GONE);
        lin_four.setVisibility(View.GONE);
        lin_five.setVisibility(View.GONE);
        lin_six.setVisibility(View.GONE);
        lin_seven.setVisibility(View.GONE);
        lin_eight.setVisibility(View.VISIBLE);
        lin_nine.setVisibility(View.GONE);
    }

    private void nineView() {
        tjq.setText("【平和体质】体质测试" + "\n" +
                "注：【平和体质】阴阳气血调和,体态适中、面色红润、精力充沛，肤色润泽，头发稠密有光泽,耐受寒热，睡眠良好。");
        mScrollView.scrollTo(0, 0);
        mLayoutSex.setVisibility(View.GONE);
        mLayoutAge.setVisibility(View.GONE);
        tv_two.setVisibility(View.VISIBLE);
        mLinOne.setVisibility(View.GONE);
        lin_two.setVisibility(View.GONE);
        lin_three.setVisibility(View.GONE);
        lin_four.setVisibility(View.GONE);
        lin_five.setVisibility(View.GONE);
        lin_six.setVisibility(View.GONE);
        lin_seven.setVisibility(View.GONE);
        lin_eight.setVisibility(View.GONE);
        lin_nine.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (hintSelectPopup == null) {
            hintSelectPopup = new HintSelectPopup(YaoShanActivity.this, "您确定要退出当前体质测试吗？",
                    "退出", "继续测试", new HintSelectPopup.onClickDone() {
                @Override
                public void selectAffrim() {
                    hintSelectPopup.dismiss();
                }

                @Override
                public void selectCancle() {
                    hintSelectPopup.dismiss();
                    finish();
                }

            });
        }
        new XPopup.Builder(YaoShanActivity.this)
                .dismissOnBackPressed(true)
                .dismissOnTouchOutside(true)
                .asCustom(hintSelectPopup)
                .show();
    }


}