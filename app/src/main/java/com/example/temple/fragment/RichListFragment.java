package com.example.temple.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.temple.R;
import com.example.temple.activity.item.DetailActivity;

import butterknife.BindView;

public class RichListFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.item1)
    RelativeLayout item1;


    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fugui;
    }

    @Override
    protected void initListener() {
        item1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v.getId() == R.id.item1) {
            startActivity(new Intent(getActivity(), DetailActivity.class));
        }
    }



}
