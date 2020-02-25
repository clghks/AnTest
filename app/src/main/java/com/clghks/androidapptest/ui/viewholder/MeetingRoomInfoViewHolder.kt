package com.clghks.androidapptest.ui.viewholder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clghks.androidapptest.R
import com.clghks.androidapptest.ui.model.MeetingRoom
import com.clghks.androidapptest.ui.view.meetingRoomInfoUpdate

class MeetingRoomInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val roomName = itemView.findViewById(R.id.room_name) as TextView
    private val roomLocation = itemView.findViewById(R.id.room_location) as TextView
    private val timeView = itemView.findViewById<View>(R.id.layout_time) as View

    fun setItem(context: Context, item: MeetingRoom) {
        roomName.text = item.name
        roomLocation.text = item.location

        meetingRoomInfoUpdate(context, timeView, item.reservations ?: ArrayList())
    }
}