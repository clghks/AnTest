package com.clghks.androidapptest.ui.view

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.clghks.androidapptest.R
import com.clghks.androidapptest.ui.model.Reservation
import java.text.SimpleDateFormat
import java.util.*

val timeViewId = arrayOf(
    R.id.time_1,
    R.id.time_2,
    R.id.time_3,
    R.id.time_4,
    R.id.time_5,
    R.id.time_6,
    R.id.time_7,
    R.id.time_8,
    R.id.time_9,
    R.id.time_10,
    R.id.time_11,
    R.id.time_12,
    R.id.time_13,
    R.id.time_14,
    R.id.time_15,
    R.id.time_16,
    R.id.time_17,
    R.id.time_18,
    R.id.time_18
)
val simpleDateFormat = SimpleDateFormat("HHmm", Locale.KOREAN)

fun meetingRoomInfoUpdate(context: Context, view: View, reservationList: List<Reservation>) {
    val currentTime = getTimeIndex(simpleDateFormat.format(Date()))
    iniView(view)
    currentTimeUpdate(context, view, currentTime)

    reservationList.forEach {
        val startIndex = getTimeIndex(it.startTime ?: "")
        val endIndex = getTimeIndex(it.endTime ?: "") - 1
        if (startIndex != -1 && endIndex != -1) {
            for (i in startIndex .. endIndex) {
                if (currentTime <= i) {
                    view.findViewById<View>(timeViewId[i]).visibility = View.VISIBLE
                }
            }
        }
    }
}

fun currentTimeUpdate(context: Context, view: View, currentTime: Int) {
    var barView = view.findViewById<View>(R.id.current_time_bar)
    var textView = view.findViewById<View>(R.id.current_time_text)

    if (currentTime >= 0 && currentTime < timeViewId.size) {
        var barLayoutParams = ConstraintLayout.LayoutParams(context.resources.getDimensionPixelSize(R.dimen.current_time_width), context.resources.getDimensionPixelSize(R.dimen.current_time_height))
        var textLayoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        barLayoutParams.bottomToBottom = R.id.time_bar

        textLayoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        when (currentTime) {
            0 -> {
                textLayoutParams.startToStart = R.id.current_time_bar
                barLayoutParams.startToStart = timeViewId[currentTime]
            }
            timeViewId.size - 1 -> {
                textLayoutParams.endToEnd = R.id.current_time_bar
                barLayoutParams.endToEnd = timeViewId[currentTime]
            }
            else -> {
                textLayoutParams.endToEnd = R.id.current_time_bar
                textLayoutParams.startToStart = R.id.current_time_bar
                barLayoutParams.startToStart = timeViewId[currentTime]
            }
        }
        textView.layoutParams = textLayoutParams
        barView.layoutParams = barLayoutParams

        textView.visibility = View.VISIBLE
        barView.visibility = View.VISIBLE
    } else {
        textView.visibility = View.INVISIBLE
        barView.visibility = View.INVISIBLE
    }
}

fun iniView(view: View) {
    for (i in timeViewId) {
        view.findViewById<View>(i).visibility = View.INVISIBLE
    }
}

fun getTimeIndex(time: String): Int {
    if (time.length == 4) {
        var hour = (time.substring(0 .. 1).toInt() - 9) * 2
        if (30 > time.substring(2, 4).toInt()) {
            return hour
        }
        return hour + 1
    }
    return -1
}