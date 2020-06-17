package com.sam43.githubusers.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sam43.githubusers.R
import com.sam43.githubusers.ui.main.users.UserListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, UserListFragment.newInstance())
                    .commitNow()
        }
    }
}
