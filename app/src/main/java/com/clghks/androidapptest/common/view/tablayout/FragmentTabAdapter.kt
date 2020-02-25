package com.clghks.androidapptest.common.view.tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentTabAdapter(fragmentManager: FragmentManager, private var items: List<FragmentTab>) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Fragment {
        return items[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return items[position].title
    }

    fun setItems(items: List<FragmentTab>) {
        this.items = items
        notifyDataSetChanged()
    }
}