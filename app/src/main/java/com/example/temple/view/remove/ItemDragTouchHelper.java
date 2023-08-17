package com.example.temple.view.remove;

import androidx.recyclerview.widget.RecyclerView;

public interface ItemDragTouchHelper {
    /**
     * 交换
     *
     * @param source
     * @param target
     */
    void onItemMove(RecyclerView.ViewHolder source, RecyclerView.ViewHolder target);

    /**
     * 选中
     *
     * @param source
     */
    void onItemSelect(RecyclerView.ViewHolder source);

    /**
     * 状态清除
     *
     * @param source
     */
    void onItemClear(RecyclerView.ViewHolder source);
}

