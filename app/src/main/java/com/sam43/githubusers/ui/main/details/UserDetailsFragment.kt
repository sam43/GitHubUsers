package com.sam43.githubusers.ui.main.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sam43.githubusers.R
import com.sam43.githubusers.models.GithubUser
import com.sam43.githubusers.ui.utils.getViewModel
import com.sam43.githubusers.ui.utils.loadUserAvatar
import kotlinx.android.synthetic.main.user_details_fragment.*

class UserDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = UserDetailsFragment()
    }

    private val viewModel: UserDetailsViewModel by lazy {
        getViewModel<UserDetailsViewModel> // without using (UserListViewModel) will get compile time error
        {
            UserDetailsViewModel()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getUserDetails()
    }

    override fun onStart() {
        super.onStart()
        callViewModelVariables()
    }

    private fun callViewModelVariables() {
        viewModel.userDetailLiveData.observe(viewLifecycleOwner, Observer {
            updateViewsWith(it)
        })
    }

    private fun updateViewsWith(user: GithubUser?) {
        requireContext().loadUserAvatar(user?.owner?.avatar_url, ivUserAvatar)
        tvGithubID.text = requireContext().getString(R.string.githubId, user?.id.toString())
        tvGithubName.text = user?.name
        tvGithubFullName.text = user?.full_name
        tvProfileUrl.text = user?.html_url

        btnProfileLink.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(user?.html_url)
            startActivity(openURL)
        }
    }

    private fun getUserDetails() {
        val userDetail = arguments?.getSerializable("user") as GithubUser
        viewModel.setUserDetail(userDetail)
    }

}
