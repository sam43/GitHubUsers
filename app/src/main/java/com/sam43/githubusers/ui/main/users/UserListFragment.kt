package com.sam43.githubusers.ui.main.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sam43.githubusers.R
import com.sam43.githubusers.models.GithubUser
import com.sam43.githubusers.repositories.UserRepo
import com.sam43.githubusers.services.ApiFactory
import com.sam43.githubusers.ui.main.adapters.UserAdapter
import com.sam43.githubusers.ui.utils.*
import kotlinx.android.synthetic.main.user_list_fragment.*


class UserListFragment : Fragment() {

    private lateinit var communicator: Communicator

    companion object {
        fun newInstance() = UserListFragment()
    }

    private val repository: UserRepo = UserRepo(ApiFactory.service)

    private val viewModel: UserListViewModel by lazy {
        getViewModel<UserListViewModel> // without using (UserListViewModel) will get compile time error
        {
            UserListViewModel(repository)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.user_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchUsers()
        communicator = context as Communicator
    }

    private fun setupLayoutManager() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_github_users.layoutManager = layoutManager
        rv_github_users.setHasFixedSize(true)
    }

    override fun onStart() {
        super.onStart()
        viewModel.usersLiveData.observe(viewLifecycleOwner, Observer {
            try {
                Log.d("GithubUsers", "data: ${it[0]}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            populateWithData(it)
        })
    }

    private fun populateWithData(userList: MutableList<GithubUser?>) {
        rv_github_users.adapter = UserAdapter(requireContext(), userList) { userItem : GithubUser -> userItemClicked(userItem) }
        //rv_github_users.adapter = UserAdapterNormal(requireContext(), userList, this)
        setupLayoutManager()
    }

    private fun userItemClicked(item: GithubUser) {
        requireContext().pop("Clicked: ${item.name}")
        communicator.startDetailFragmentWith(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelAllRequests()
    }

    /*override fun onRecyclerViewItemClick(view: View, user: GithubUser?) {
        user?.let { userItemClicked(it) }
    }*/

}
