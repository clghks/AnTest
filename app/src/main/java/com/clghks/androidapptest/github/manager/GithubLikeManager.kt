package com.clghks.androidapptest.github.manager

import com.clghks.androidapptest.common.config.AppApplication
import com.clghks.androidapptest.common.config.BusProvider
import com.clghks.androidapptest.common.network.model.Item
import com.clghks.androidapptest.github.event.UpdateUserInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GithubLikeManager {
    private const val USER_INFO_LIST = "user_info_list"
    private var userList:MutableList<Item> = ArrayList()

    private fun addLikeUser(user: Item) {
        userList.add(user)
        save()
    }

    private fun removeLikeUser(user: Item) {
        userList.remove(user)
        save()
    }

    private fun load(): MutableList<Item> {
        var userInfo = AppApplication.instance.getAppPreference().getString(USER_INFO_LIST, "")
        if (!userInfo.isNullOrEmpty()) {
            val userListType = object : TypeToken<MutableList<Item>>() {}.type
            return Gson().fromJson(userInfo, userListType)
        }
        return ArrayList()
    }

    private fun save() {
        var preferenceEdit = AppApplication.instance.getAppPreference().edit()
        preferenceEdit.putString(USER_INFO_LIST, Gson().toJson(userList))
        preferenceEdit.commit()
    }

    fun getLikeUser(): MutableList<Item> {
        if (userList.isEmpty()) {
            userList = load()
        }
        return userList
    }

    fun isLikeUser(id: Long): Boolean {
        var user = userList.find { it.id == id }
        return user != null
    }

    fun updateUserInfo(user: Item) {
        if (isLikeUser(user.id ?: -1)) {
            removeLikeUser(user)
        } else {
            addLikeUser(user)
        }

        BusProvider.bus.post(UpdateUserInfo())
    }
}