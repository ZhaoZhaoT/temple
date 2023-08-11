package com.example.temple.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.temple.R;
import com.example.temple.activity.message.MessageActivity;

import butterknife.BindView;

public class MessageFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.rl_xitong)
    RelativeLayout rl_xitong;
    @BindView(R.id.rl_richeng)
    RelativeLayout rl_richeng;
    @BindView(R.id.rl_shengri)
    RelativeLayout rl_shengri;
    @BindView(R.id.rl_jiri)
    RelativeLayout rl_jiri;


    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initListener() {
        rl_jiri.setOnClickListener(this);
        rl_richeng.setOnClickListener(this);
        rl_xitong.setOnClickListener(this);
        rl_shengri.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v.getId() == R.id.rl_jiri) {
            startActivity(new Intent(getActivity(), MessageActivity.class).putExtra("index",4));
        }
        else if(v.getId() == R.id.rl_richeng) {
            startActivity(new Intent(getActivity(), MessageActivity.class).putExtra("index",2));
        }
        else if(v.getId() == R.id.rl_xitong) {
            startActivity(new Intent(getActivity(), MessageActivity.class).putExtra("index",1));
        }
        else if(v.getId() == R.id.rl_shengri) {
            startActivity(new Intent(getActivity(), MessageActivity.class).putExtra("index",3));
        }
    }



}
