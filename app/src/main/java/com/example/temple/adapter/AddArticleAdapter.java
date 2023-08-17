package com.example.temple.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;
import com.example.temple.view.remove.ItemDragTouchHelper;

import java.util.Collections;


public class AddArticleAdapter extends BaseQuickAdapter<String, BaseViewHolder> implements ItemDragTouchHelper {

    private onClickDone listener;

    public AddArticleAdapter(int layoutResId, AddArticleAdapter.onClickDone onClickDone) {
        super(layoutResId);
        this.listener = onClickDone;

    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView iv_close = helper.getView(R.id.iv_close);
        ImageView img_add = helper.getView(R.id.img_add);

        TextView tv_text = helper.getView(R.id.tv_text);
        tv_text.setText(item);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listener!=null){
                    listener.selectClose(getItemPosition(item));
                }
            }
        });

        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listener!=null){
                    listener.selectAdd(getItemPosition(item));
                }
            }
        });
    }


    public interface onClickDone {
        void selectClose(int position);

        void selectAdd(int position);

    }

    @Override
    public void onItemMove(RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        int fromPosition = source.getBindingAdapterPosition();
        int toPosition = target.getBindingAdapterPosition();
        if (fromPosition < getData().size() && toPosition < getData().size()) {
            Collections.swap(getData(), fromPosition, toPosition);
            notifyItemMoved(fromPosition, toPosition);
        }
        onItemClear(source);
    }

    @Override
    public void onItemSelect(RecyclerView.ViewHolder source) {
        source.itemView.setScaleX(1.2f);
        source.itemView.setScaleY(1.2f);
    }

    @Override
    public void onItemClear(RecyclerView.ViewHolder source) {
        source.itemView.setScaleX(1.0f);
        source.itemView.setScaleY(1.0f);
    }
}
