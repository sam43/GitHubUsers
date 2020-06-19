package com.sam43.githubusers.ui.main.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam43.githubusers.App
import com.sam43.githubusers.cache.database.CacheDatabase
import com.sam43.githubusers.models.GithubUser
import com.sam43.githubusers.repositories.UserRepo
import com.sam43.githubusers.ui.utils.isInternetConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class UserListViewModel(private val repo: UserRepo) : ViewModel() {

    open val usersLiveData = MutableLiveData<MutableList<GithubUser?>?>()

    private var appDB: CacheDatabase? = null

    init {
        appDB = CacheDatabase.getInstance(App.applicationContext())
    }

    fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            if (isInternetConnected()) {
                val users = repo.getUsersListFromServer()
                usersLiveData.postValue(users)
            } else {
                val users = repo.getGithubUserListOffline()
                usersLiveData.postValue(users)
            }
        }
    }
}
