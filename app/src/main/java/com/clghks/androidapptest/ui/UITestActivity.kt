package com.clghks.androidapptest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.clghks.androidapptest.R
import com.clghks.androidapptest.common.view.recycler.HorizontalSpaceItemDecoration
import com.clghks.androidapptest.common.view.recycler.VerticalSpaceItemDecoration
import com.clghks.androidapptest.ui.adapter.MeetingRoomAdapter
import com.clghks.androidapptest.ui.adapter.MeetingRoomInfoAdapter
import com.clghks.androidapptest.ui.model.MeetingRoom
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_uitest.*
import kotlinx.android.synthetic.main.layout_action_bar.*
import java.text.SimpleDateFormat
import java.util.*


class UITestActivity : AppCompatActivity() {
    companion object {
        const val FILE_NAME = "MUSINSA.json"
    }

    private val simpleDateFormat = SimpleDateFormat("MM월 dd일 (E)", Locale.KOREAN)
    private var meetingRoomData:List<MeetingRoom>? = null
    private var meetingRoomAdapter = MeetingRoomAdapter(this@UITestActivity)
    private var meetingRoomInfoAdapter = MeetingRoomInfoAdapter(this@UITestActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uitest)

        initActionBar()
        initMeetingRoomList()
        initMeetingRoomInfoList()

        initData()
    }

    private fun initActionBar() {
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        supportActionBar?.setCustomView(R.layout.layout_action_bar)
        var toolBar = supportActionBar?.customView?.parent as Toolbar
        toolBar.setContentInsetsAbsolute(0, 0)

        today.text = simpleDateFormat.format(Date())
    }

    private fun initMeetingRoomList() {
        meeting_room_list.adapter = meetingRoomAdapter
        meeting_room_list.layoutManager = LinearLayoutManager(this@UITestActivity, LinearLayoutManager.HORIZONTAL, false)
        meeting_room_list.addItemDecoration(HorizontalSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.meeting_room_horizontal)))
    }

    private fun initMeetingRoomInfoList() {
        meeting_room_info_list.adapter = meetingRoomInfoAdapter
        meeting_room_info_list.layoutManager = LinearLayoutManager(this@UITestActivity)
        meeting_room_info_list.addItemDecoration(VerticalSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.meeting_room_vertical)))
    }

    private fun initData() {
        var inputStream = assets.open(FILE_NAME)

        val meetingRoomType = object : TypeToken<List<MeetingRoom>>() {}.type
        meetingRoomData = Gson().fromJson(inputStream.reader(), meetingRoomType) as List<MeetingRoom>

        meeting_room_count.text = "${meetingRoomData?.size ?: 0}"
        meetingRoomAdapter.addAllItems(meetingRoomData)
        meetingRoomInfoAdapter.addAllItems(meetingRoomData)
    }
}
