package com.muhammedguven.rickandmortyapp.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muhammedguven.rickandmortyapp.R

class GridItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spacing = view.context.resources.getDimensionPixelSize(R.dimen.item_character_spacing)
        val position = parent.getChildAdapterPosition(view)
        val spanIndex = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex

        val isAtRight = spanIndex == 1
        val isAtFirstLine = position < 2

        if (isAtRight) {
            outRect.left = spacing / 2
            outRect.right = spacing
        } else {
            outRect.left = spacing
            outRect.right = spacing / 2
        }
        outRect.bottom = spacing

        if (isAtFirstLine) {
            outRect.top = spacing
        }
    }
}