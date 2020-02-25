package com.clghks.androidapptest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clghks.androidapptest.R
import com.clghks.androidapptest.ui.model.MeetingRoom
import com.clghks.androidapptest.ui.viewholder.MeetingRoomViewHolder

class MeetingRoomAdapter(private val context: Context) : RecyclerView.Adapter<MeetingRoomViewHolder>() {
    private var items: List<MeetingRoom> = java.util.ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingRoomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_meeting_room_name, parent, false)
        return MeetingRoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeetingRoomViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    fun addAllItems(items: List<MeetingRoom>?) {
        if (items != null) {
            this.items = items
        }
        notifyDataSetChanged()
    }
}