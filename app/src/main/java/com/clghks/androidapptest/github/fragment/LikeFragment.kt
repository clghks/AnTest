package com.clghks.androidapptest.github.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.clghks.androidapptest.R
import com.clghks.androidapptest.common.config.BusProvider
import com.clghks.androidapptest.common.network.model.Item
import com.clghks.androidapptest.common.view.recycler.RecyclerViewItemClickListener
import com.clghks.androidapptest.github.adapter.UserAdapter
import com.clghks.androidapptest.github.event.UpdateUserInfo
import com.clghks.androidapptest.github.manager.GithubLikeManager
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.fragment_like.*

class LikeFragment: Fragment(), RecyclerViewItemClickListener {
    private var adapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.bus.register(this@LikeFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        BusProvider.bus.unregister(this@LikeFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_like, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = context?.let { UserAdapter(it, this@LikeFragment) }
        adapter?.addAllItems(GithubLikeManager.getLikeUser())

        like_list.layoutManager = LinearLayoutManager(context)
        like_list.adapter = adapter
    }

    override fun onClickItemListener(view: View, position: Int) {
        var user = adapter?.getItem(position) ?: Item()
        GithubLikeManager.updateUserInfo(user)
    }

    @Subscribe
    fun updateUserInof(event: UpdateUserInfo) {
        adapter?.addAllItems(GithubLikeManager.getLikeUser())
    }
}