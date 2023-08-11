package com.example.temple.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.example.temple.R;
import com.example.temple.wheelview.WheelView;
import com.example.temple.wheelview.listener.OnItemSelectedListener;
import com.lxj.xpopup.core.CenterPopupView;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * 是否弹窗
 */
public class WhetherNotPopup extends CenterPopupView implements View.OnClickListener {

    private TextView tv_affrim;
    private TextView tv_title;
    private WheelView options1;
    private OnItemSelectedListener wheelListener_option1;

    private Context context;

    private String surname = "";
    private onClickDone listener;


    public static final String[] sur_name = {"是", "否"};

    public WhetherNotPopup(@NonNull @NotNull Context context, WhetherNotPopup.onClickDone onClickDone) {
        super(context);
        this.context = context;
        this.listener = onClickDone;

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_all_surname;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        tv_affrim = findViewById(R.id.tv_affrim);
        tv_affrim.setOnClickListener(this);
        tv_title= findViewById(R.id.tv_title);
        tv_title.setText("是否健在");

        options1 = findViewById(R.id.options1);// 初始化时显示的数据
        // 选项1
        options1.setAdapter(new ArrayWheelAdapter(Arrays.asList(sur_name)));// 设置显示数据
        options1.setCurrentItem(0,5);// 初始化时显示的数据  可见数量
        options1.setIsOptions(true);
        options1.setDividerColor(Color.parseColor("#FFFFFF"));
        options1.setCyclic(false);//是否循环
        options1.setTextSize(24f);
//        options1.setItemsVisibleCount(3);
        options1.setLineSpacingMultiplier(2.0f);
        // 联动监听器
        wheelListener_option1 = new OnItemSelectedListener() {

            @Override
            public void onItemSelected(int index) {
                surname = Arrays.asList(sur_name).get(index);
            }
        };

        // 添加联动监听
        options1.setOnItemSelectedListener(wheelListener_option1);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_affrim) {
            if (listener != null) {
                listener.selectData(surname);
            }
            dialog.dismiss();
        }
    }

    public interface onClickDone {
        void selectData(String surname);
    }

}
