package com.clghks.androidapptest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clghks.androidapptest.github.GithubSearchActivity
import com.clghks.androidapptest.ui.UITestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        github_search.setOnClickListener {
            startActivity(Intent(this@MainActivity, GithubSearchActivity::class.java))
        }

        ui_test.setOnClickListener {
            startActivity(Intent(this@MainActivity, UITestActivity::class.java))
        }
    }
}
