package com.clghks.androidapptest.ui.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Reservation {
    @SerializedName("startTime")
    @Expose
    var startTime: String? = null
    @SerializedName("endTime")
    @Expose
    var endTime: String? = null
}
