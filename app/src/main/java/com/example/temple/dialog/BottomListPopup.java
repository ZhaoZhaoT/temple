package com.example.temple.dialog;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.adapter.SizeAdapter;
import com.example.temple.bean.ShoopDetailBean;
import com.example.temple.utils.GlideUtils;
import com.example.temple.utils.SpaceDecoration;
import com.example.temple.view.SpaceItemDecoration;
import com.lxj.xpopup.core.BottomPopupView;

import java.util.List;

public class BottomListPopup extends BottomPopupView implements View.OnClickListener {
    ImageView ivPicture;
    TextView tvProductTile;
    TextView tvNewPrice,tvOldPrice;
    TextView tvKucun,tvSaleCount;
    RecyclerView rView1;
    ImageView tvReduce;
    TextView tvChooseNumber,tvConfirm;
    ImageView tvAdd;
    private onClickDone listener;
    private int index=0;
    private ImageView close;

    private List<ShoopDetailBean.SpecVOBean> mList;
    private String proName;//商品名称
    private int buyCount = 1;
    private int minBuyCount = buyCount;
    private boolean isChange;
    private LinearLayout llAdd,llReduce;




  /*  public BottomListPopup(@NonNull @NotNull Context context) {
        super(context);
//        this.workDetailBean = workDetailBean;
    }

    public BottomListPopup(@NonNull Context context, List<ShoopDetailBean.SpecVOBean> mList) {
        super(context);
        this.mList = mList;
    }*/

    public void setChange(boolean change) {
        isChange = change;
        if(isChange){

        }
    }

    public BottomListPopup(@NonNull Context context, List<ShoopDetailBean.SpecVOBean> mList, String proName, onClickDone done) {
        super(context);
        this.mList = mList;
        this.proName = proName;
        this.listener=done;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_choose_guige;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ivPicture=findViewById(R.id.iv_picture);
        tvProductTile=findViewById(R.id.tv_product_tile);
        tvNewPrice=findViewById(R.id.tv_new_price);
        tvOldPrice=findViewById(R.id.tv_old_price);
        tvKucun=findViewById(R.id.tv_kucun);
        rView1=findViewById(R.id.rView);
//        tvReduce=findViewById(R.id.tv_reduce);
//        tvAdd=findViewById(R.id.tv_add);
        llAdd=findViewById(R.id.ll_add);
        llReduce=findViewById(R.id.ll_reduce);
        tvSaleCount=findViewById(R.id.tv_sale_count);
        tvChooseNumber=findViewById(R.id.tv_choose_number);
        tvConfirm=findViewById(R.id.tv_confirm);
        ClickUtils.expandClickArea(llAdd, SizeUtils.dp2px(10));
        ClickUtils.expandClickArea(llReduce, SizeUtils.dp2px(10));

        close=findViewById(R.id.iv_close);
        ClickUtils.expandClickArea(close, SizeUtils.dp2px(10));
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
//        tvReduce.setOnClickListener(this);
//        tvAdd.setOnClickListener(this);
        llAdd.setOnClickListener(this);
        llReduce.setOnClickListener(this);

        tvConfirm.setOnClickListener(this);
        tvProductTile.setText(proName);



        initGuiGe(mList.get(0));
        initRecycler();

    }

    private int release;//当前规格库存数量

    public void initGuiGe(ShoopDetailBean.SpecVOBean bean){
        release=bean.getStock();
        tvNewPrice.setText("¥"+bean.getPrice());
        tvOldPrice.setText("¥"+bean.getOrgPrice());
        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //中划线，会有锯齿
        tvOldPrice.getPaint().setAntiAlias(true);
        tvSaleCount.setText("销量"+bean.getSales());
        tvKucun.setText("库存"+bean.getStock());
        buyCount = bean.getBuyAmount();
        minBuyCount = buyCount;
        tvChooseNumber.setText("" +buyCount);
        GlideUtils.loadRoundCircleImage(getContext(), bean.getSpecCover(), ivPicture,5);

    }

    private SizeAdapter mAdapter1;
    public void initRecycler(){
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        rView1.setLayoutManager(manager);
        rView1.addItemDecoration(new SpaceItemDecoration(SizeUtils.dp2px(10), 3));

        mAdapter1 = new SizeAdapter(R.layout.item_text);
        rView1.addItemDecoration(new SpaceDecoration(0,0,0, SizeUtils.dp2px(10)));
        rView1.setAdapter(mAdapter1);
        mList.get(0).setCheck(true);
        mAdapter1.setList(mList);
        mAdapter1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                mAdapter1.getData().get(position).setCheck(true);
                for(int i=0;i<mList.size();i++){
                    if(i!=position){
                        mAdapter1.getData().get(i).setCheck(false);
                    }
                }
                mAdapter1.notifyDataSetChanged();
                buyCount =mList.get(position).getBuyAmount();
                minBuyCount = buyCount;
                tvChooseNumber.setText(buyCount + "");
                initGuiGe(mAdapter1.getData().get(position));
                index=position;

                if(listener!=null){
                    listener.choose(mAdapter1.getData().get(position).getSpecName());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ll_reduce) {
            buyCount = Integer.parseInt(tvChooseNumber.getText().toString());
            if (buyCount > minBuyCount) {
                buyCount--;
                tvChooseNumber.setText(buyCount + "");
            }
        } else if (view.getId() == R.id.ll_add) {
            buyCount = Integer.parseInt(tvChooseNumber.getText().toString());
            if (buyCount < release) {
                buyCount++;
            }else{
                ToastUtils.showLong("库存不足!");
            }
            tvChooseNumber.setText(buyCount + "");

        }else if(view.getId()==R.id.tv_confirm){
//            ToastUtils.showShort("添加购物车成功");
            if(listener!=null){
                listener.comfirm(buyCount,mList.get(index));
            }
            dismiss();
        }
    }

    public interface onClickDone {
        void comfirm(int buyAmount,ShoopDetailBean.SpecVOBean specVOBean);
        void choose(String name);
    }
}
