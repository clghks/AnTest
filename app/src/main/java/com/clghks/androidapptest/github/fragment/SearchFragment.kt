package com.clghks.androidapptest.github.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clghks.androidapptest.R
import com.clghks.androidapptest.common.config.BusProvider
import com.clghks.androidapptest.common.network.NetworkManager
import com.clghks.androidapptest.common.network.model.Item
import com.clghks.androidapptest.common.view.recycler.RecyclerViewItemClickListener
import com.clghks.androidapptest.github.adapter.UserAdapter
import com.clghks.androidapptest.github.event.UpdateUserInfo
import com.clghks.androidapptest.github.manager.GithubLikeManager
import com.squareup.otto.Subscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment: Fragment(), RecyclerViewItemClickListener {
    private var searchKeyword = ""
    private var page = 1
    private var isNextPage = true
    private var isLoading = false
    private var adapter:UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.bus.register(this@SearchFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        BusProvider.bus.unregister(this@SearchFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_button.setOnClickListener {
            initData()

            var keyword = search_keyword.text.toString()
            if (!keyword.isNullOrEmpty()) {
                getGithubUserList(keyword)
            }
        }

        delete_button.setOnClickListener {
            search_keyword.setText("")
            initData()
        }

        adapter = context?.let { UserAdapter(it, this@SearchFragment) }
        user_list.layoutManager = LinearLayoutManager(context)
        user_list.adapter = adapter
        user_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                var lastVisibleItem = (user_list.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                var itemCount = adapter?.itemCount ?: 0

                if (lastVisibleItem == (itemCount -1) && isNextPage && !isLoading) {
                    getGithubUserList(searchKeyword, ++page)
                }
            }
        })
    }

    private fun initData() {
        searchKeyword = ""
        page = 1
        adapter?.clearItem()
        isNextPage = false
    }

    private fun getGithubUserList(keyword: String, page: Int = 1) {
        isLoading = true
        searchKeyword = keyword
        NetworkManager.create().getUsers(keyword, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (!response.items.isNullOrEmpty()) {
                        adapter?.addItems(response.items)
                    }
                    isNextPage = response.totalCount ?: 0 > (page * 30)
                    isLoading = false
                },
                {
                    it.printStackTrace()
                    showAlert(getString(R.string.error))
                    isLoading = false
                }
            )
    }

    private fun showAlert(message: String) {
        activity?.let {
            AlertDialog.Builder(it)
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok)) { _, _ ->  }
                .show()
        }
    }

    override fun onClickItemListener(view: View, position: Int) {
        var user = adapter?.getItem(position) ?: Item()
        GithubLikeManager.updateUserInfo(user)
    }

    @Subscribe
    fun updateUserInof(event: UpdateUserInfo) {
        adapter?.notifyDataSetChanged()
    }
}