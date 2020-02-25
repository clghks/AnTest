package com.clghks.androidapptest.common.config

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class AppApplication: Application() {
    private lateinit var appPreference: SharedPreferences

    fun getAppPreference(): SharedPreferences {
        return appPreference
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        appPreference = getSharedPreferences("app_pref_global", Context.MODE_PRIVATE)
    }

    companion object {
        lateinit var instance: AppApplication
            private set
    }
}