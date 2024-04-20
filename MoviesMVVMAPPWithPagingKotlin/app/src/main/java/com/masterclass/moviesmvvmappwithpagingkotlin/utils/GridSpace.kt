package com.masterclass.moviesmvvmappwithpagingkotlin.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpace(private val spaceCount: Int, private val spacing: Int, private val includeEdge: Boolean) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spaceCount

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spaceCount
            outRect.right = (column + 1) * spacing / spaceCount

            if (position < spaceCount) {
                outRect.top = spacing
            }
            outRect.bottom = spacing

        } else {
            outRect.left = column * spacing / spaceCount
            outRect.right = spacing - (column + 1) * spacing / spaceCount

            if (position >= spaceCount) {
                outRect.top = spacing
            }
        }
    }
}