package com.clghks.androidapptest.ui.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clghks.androidapptest.R
import com.clghks.androidapptest.ui.model.MeetingRoom

class MeetingRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val roomName = itemView.findViewById<View>(R.id.room_name) as TextView

    fun setItem(item: MeetingRoom) {
        roomName.text = item.name
    }
}