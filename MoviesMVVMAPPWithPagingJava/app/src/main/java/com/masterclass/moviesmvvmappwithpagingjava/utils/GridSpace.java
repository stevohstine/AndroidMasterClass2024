package com.masterclass.moviesmvvmappwithpagingjava.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpace extends RecyclerView.ItemDecoration {
    private int spaceCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpace(int spaceCount, int spacing, boolean includeEdge) {
        this.spaceCount = spaceCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int column = position % spaceCount;

        if (includeEdge){
            outRect.left = spacing - column * spacing / spaceCount;
            outRect.right = (column + 1) * spacing / spaceCount;

            if (position < spaceCount){
                outRect.top = spacing;
            }
            outRect.bottom = spacing;

        }else {
            outRect.left = column * spacing / spaceCount;
            outRect.right = spacing - (column + 1) * spacing / spaceCount;

            if (position >= spaceCount){
                outRect.top = spacing;
            }
        }

    }
}
