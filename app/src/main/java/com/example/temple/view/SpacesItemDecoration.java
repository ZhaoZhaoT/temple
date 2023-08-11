package com.example.temple.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int span;

    public SpacesItemDecoration(int space, int span) {
        this.space = space;
        this.span = span;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        if (parent.getChildLayoutPosition(view) % span == 0) {
            outRect.left = 0;
        } else {
            outRect.left = space;

        }
    }
}
