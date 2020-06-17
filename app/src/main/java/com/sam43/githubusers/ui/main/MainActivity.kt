package com.sam43.githubusers.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.sam43.githubusers.R
import com.sam43.githubusers.models.GithubUser
import com.sam43.githubusers.ui.main.details.UserDetailsFragment
import com.sam43.githubusers.ui.main.users.UserListFragment
import com.sam43.githubusers.ui.utils.Communicator
import java.io.Serializable

class MainActivity : AppCompatActivity(), Communicator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, UserListFragment.newInstance())
                    .commitNow()
        }
    }

    override fun startDetailFragmentWith(value: GithubUser) {
        val bundle = Bundle()
        bundle.putSerializable("user", value as Serializable)

        val transaction = supportFragmentManager.beginTransaction()
        val detailsFragment = UserDetailsFragment.newInstance()
        detailsFragment.arguments = bundle

        transaction.replace(R.id.container, detailsFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}
