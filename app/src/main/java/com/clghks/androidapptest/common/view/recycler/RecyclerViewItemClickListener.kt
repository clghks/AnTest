package com.clghks.androidapptest.common.view.recycler

import android.view.View

interface RecyclerViewItemClickListener {
    fun onClickItemListener(view: View, position: Int)
}