package com.example.temple.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.adapter.ShangZhongJiNianListAdapter;
import com.lxj.xpopup.core.BottomPopupView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * 祭奠 纪念
 */
public class JiNianPopup extends BottomPopupView implements View.OnClickListener {
    TextView tv_cancel;
    RadioGroup group1;

    RecyclerView view_lise;
    private final String[] mTitles = {"敬酒", "敬茶", "上香", "献花", "贡品"};
    ShangZhongJiNianListAdapter shangZhongJiNianListAdapter;

    public JiNianPopup(@NotNull Context context) {
        super(context);

    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_fangke_jilu;
    }

    //
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate() {
        super.onCreate();
        tv_cancel = findViewById(R.id.tv_cancel);
        group1 = findViewById(R.id.group1);

        view_lise = findViewById(R.id.view_lise);
        view_lise.setLayoutManager(new GridLayoutManager(getContext(), 4));
        shangZhongJiNianListAdapter = new ShangZhongJiNianListAdapter(R.layout.item_jinian);
        view_lise.setAdapter(shangZhongJiNianListAdapter);

        tv_cancel.setOnClickListener(this);
        //假数据
        ArrayList<String> data = new ArrayList<String>();
        data.add("koko");
        data.add("ko1ko");
        data.add("ko2ko");
        data.add("koko");
        data.add("koko");
        data.add("ko1ko");
        data.add("ko2ko");
        data.add("koko");
        shangZhongJiNianListAdapter.setList(data);

        shangZhongJiNianListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {

//                startActivity(new Intent(CangjinStoreyActivity.this, ContentScripturesActivity.class));

            }
        });
        LinearLayout.LayoutParams params_rb = new LinearLayout.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        int margin = SizeUtils.dp2px(15);
        params_rb.setMargins(margin, margin, margin, margin);
        for (int i = 0; i < mTitles.length; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            //设置文字距离四周的距离
            radioButton.setButtonDrawable(null);
            radioButton.setBackground(null);
            radioButton.setPadding(15, 15, 15, 15);

            //设置文字
            radioButton.setText(mTitles[i]);
            radioButton.setTextSize(16);
            if (i == 0) {
                radioButton.setChecked(true);
            }
            radioButton.setId(i);
            radioButton.setTextColor(getResources().getColorStateList(R.drawable.radio_select_color));
            //设置radioButton的点击事件
            int finalI1 = i;
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    selectGodPrice = price.get(finalI1);
                }
            });
            //将radioButton添加到radioGroup中
            group1.addView(radioButton, params_rb);
        }

        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                int id = checkedId;
//                if (id >= 0 && id < mTitles.length) Toast.makeText(getContext(), "" + mTitles[id], Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_cancel) {
            dialog.dismiss();
        }
    }

    public interface onClickDone {
        void copy();
    }

}
