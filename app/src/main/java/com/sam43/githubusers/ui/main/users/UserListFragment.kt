package com.sam43.githubusers.ui.main.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sam43.githubusers.R
import com.sam43.githubusers.models.GithubUser
import com.sam43.githubusers.repositories.UserRepo
import com.sam43.githubusers.services.ApiFactory
import com.sam43.githubusers.ui.main.adapters.UserAdapter
import com.sam43.githubusers.ui.utils.RecyclerAdapterUtil
import com.sam43.githubusers.ui.utils.getViewModel
import com.sam43.githubusers.ui.utils.loadUserAvatar
import kotlinx.android.synthetic.main.user_list_fragment.*
import java.io.Serializable


class UserListFragment : Fragment() {

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
            Log.d("GithubUsers", "data: $it")
            /*val userList = GsonBuilder().create()
                .fromJson(it.toString(), Array<GithubUser?>::class.java).toMutableList()*/
            /*val userList: MutableList<GithubUser?> =
                Gson().fromJson(it.toString(), Array<GithubUser?>::class.java).toMutableList()*/
            populateWithData(it)
           // updateList(it)
        })
    }

    private fun populateWithData(userList: MutableList<GithubUser?>) {
        rv_github_users.adapter = UserAdapter(userList)
        setupLayoutManager()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelAllRequests()
    }


    private fun updateList(users: MutableList<GithubUser?>?) {
        val viewList = listOf(
            R.id.user_avatar,
            R.id.user_id,
            R.id.user_name,
            R.id.user_full_name
        )

        users?.let {
            RecyclerAdapterUtil.Builder(requireContext(), it, R.layout.item_github_user)
                .viewsList(viewList)
                .bindView { _, item, _, innerViews ->
                    val tvUserID = innerViews[R.id.user_id] as TextView
                    val tvUserName = innerViews[R.id.user_name] as TextView
                    val tvUserFullName = innerViews[R.id.user_full_name] as TextView
                    val userImage = innerViews[R.id.user_avatar] as ImageView

                    tvUserID.text = item?.id.toString()
                    tvUserName.text = item?.name
                    tvUserFullName.text = item?.full_name
                    requireContext().loadUserAvatar(item?.owner?.avatar_url, userImage)
                }
                /*.addClickListener { item, _ ->
                    findNavController().navigate(R.id.action_userInfoFragment_to_detailFragment, bundleOf(
                        "USER_DATA" to item as Serializable
                    )
                    )
                }*/
                .into(rv_github_users)
        }
    }


}
