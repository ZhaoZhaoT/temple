package com.example.temple.activity.order;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.LogisticsAdapter;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.rxjava.rxlife.RxLife;

import java.util.List;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class LogisticsActivity extends BaseTitleActivity {

    @BindView(R.id.rView)
    RecyclerView rView;
    private LogisticsAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_logistics;
    }

    @Override
    protected void initView() {
        baseTitle.setText("物流详情");
        rView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new LogisticsAdapter(R.layout.item_logistics);
        rView.setAdapter(mAdapter);
       /* List<LogisticsActivity.LogisticsBean> list=new ArrayList<>();
        list.add(new LogisticsBean());*/
        getList(getIntent().getStringExtra("no"));
    }


    public void getList(String no) {
        showWaitDialog("",true);
        RxHttp.get(Comments.GET_LOGISSTICS+no)
                .asResponseList(LogisticsBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if(model.getData()!=null){
                        onJsonDataGetSuccess(model.getData(), 1000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 1000) {
            List<LogisticsBean> list = (List<LogisticsBean>) re_data;
            if (list != null && list.size() > 0) {
                mAdapter.setList(list);
            } else {
                mAdapter.setEmptyView(R.layout.empty_view);
                mAdapter.setList(null);
            }
        }
    }

    public class LogisticsBean{
        private String status;
        private String time;

        public String getStatus() {
            return status;
        }

        public String getTime() {
            return time;
        }
    }
}