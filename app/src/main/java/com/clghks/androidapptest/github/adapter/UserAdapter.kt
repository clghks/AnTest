package com.clghks.androidapptest.github.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clghks.androidapptest.R
import com.clghks.androidapptest.common.network.model.Item
import com.clghks.androidapptest.common.view.recycler.RecyclerViewItemClickListener
import com.clghks.androidapptest.github.viewholder.UserViewHolder

class UserAdapter(private val context: Context, private val clickListener: RecyclerViewItemClickListener) : RecyclerView.Adapter<UserViewHolder>() {
    private var items: MutableList<Item> = ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(context, item)
    }

    fun addItems(items: List<Item>?) {
        if (items != null) {
            this.items.addAll(items)
        }
        notifyDataSetChanged()
    }

    fun clearItem() {
        this.items = ArrayList()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Item {
        return this.items[position]
    }

    fun addAllItems(items: MutableList<Item>) {
        this.items = items
        notifyDataSetChanged()
    }
}