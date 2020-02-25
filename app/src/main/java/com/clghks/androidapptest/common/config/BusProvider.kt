package com.clghks.androidapptest.common.config

import com.squareup.otto.Bus

object BusProvider {
    val bus by lazy {
        Bus()
    }
}