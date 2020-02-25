package com.clghks.androidapptest.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clghks.androidapptest.R
import com.clghks.androidapptest.common.view.tablayout.FragmentTab
import com.clghks.androidapptest.common.view.tablayout.FragmentTabAdapter
import com.clghks.androidapptest.github.fragment.LikeFragment
import com.clghks.androidapptest.github.fragment.SearchFragment
import kotlinx.android.synthetic.main.activity_github_search.*

class GithubSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_search)

        val fragmentTabAdapter = FragmentTabAdapter(supportFragmentManager,
            arrayListOf(
                FragmentTab(SearchFragment(), getString(R.string.search)),
                FragmentTab(LikeFragment(), getString(R.string.like))
            )
        )
        view_pager.adapter = fragmentTabAdapter
        tab_layout.setupWithViewPager(view_pager)
    }
}
