package com.clghks.androidapptest.ui.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MeetingRoom{
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("location")
    @Expose
    var location: String? = null
    @SerializedName("reservations")
    @Expose
    var reservations: List<Reservation>? = null
}
