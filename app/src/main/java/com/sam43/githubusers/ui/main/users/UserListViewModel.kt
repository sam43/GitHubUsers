package com.sam43.githubusers.ui.main.users

import androidx.lifecycle.MutableLiveData
import com.sam43.githubusers.models.GithubUser
import com.sam43.githubusers.repositories.UserRepo
import com.sam43.githubusers.ui.utils.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

open class UserListViewModel(private val repo: UserRepo) : BaseViewModel() {

    open val usersLiveData = MutableLiveData<MutableList<GithubUser?>>()

    fun fetchUsers() {
        scope.launch {
            val users = repo.getUsersListFromServer()
            usersLiveData.postValue(users)
        }
    }

    open fun isUserListNull(): Boolean = usersLiveData.value != null

    fun cancelAllRequests() = coroutineContext.cancel()
}
