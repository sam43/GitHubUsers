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
import com.sam43.githubusers.ui.utils.Communicator
import com.sam43.githubusers.ui.utils.getViewModel
import com.sam43.githubusers.ui.utils.pop
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
        callViewModelMethods()
    }

    private val observerPopulateList = Observer<MutableList<GithubUser?>> {
        Log.d("GithubUsers", "data: ${it[0]}")
        populateWithData(it)
    }

    private val observerCheckConnection = Observer<Boolean> { connected ->
        if (connected)
            requireContext().pop("Connected")
        else
            requireContext().pop("Waiting for internet connection")
    }

    private fun callViewModelMethods() {
        viewModel.isInternetConnectedLiveData.observe(viewLifecycleOwner, observerCheckConnection)
        viewModel.usersLiveData.observe(viewLifecycleOwner, observerPopulateList)
    }

    private fun populateWithData(userList: MutableList<GithubUser?>) {
        rv_github_users.adapter = UserAdapter(requireContext(), userList) { userItem : GithubUser -> userItemClicked(userItem) }
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
}
