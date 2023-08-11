package com.example.temple.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/*
* 单元格布局
* */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

        private int space;
        private int colum;
        public SpaceItemDecoration(int space, int colum) {
            this.space = space;
            this.colum=colum;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.left = space;
            outRect.bottom = space;
            //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
            if (parent.getChildLayoutPosition(view) %colum==0) {
                outRect.left = 0;
            }
        }

}
