package com.sam43.githubusers.ui.main.users

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sam43.githubusers.App
import com.sam43.githubusers.cache.database.CacheDatabase
import com.sam43.githubusers.models.GithubUser
import com.sam43.githubusers.repositories.UserRepo
import com.sam43.githubusers.ui.factory.BaseViewModel
import kotlinx.coroutines.launch

open class UserListViewModel(private val repo: UserRepo) : BaseViewModel() {

    open val usersLiveData = MutableLiveData<MutableList<GithubUser?>?>()
    open val isInternetConnectedLiveData = MutableLiveData<Boolean>()

    private var appDB: CacheDatabase? = null

    init {
        appDB = CacheDatabase.getInstance(App.applicationContext())
    }

    fun fetchUsers() {
        viewModelScope.launch {
            if (isInternetConnected()) {
                val users = repo.getUsersListFromServer()
                usersLiveData.postValue(users)
            } else {
                val users = repo.getGithubUserListOffline()
                usersLiveData.postValue(users)
            }
        }
    }

    private fun isInternetConnected(): Boolean {
        val isInternetConnected: Boolean
        val connectivityManager = App.applicationContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        isInternetConnected = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            nwInfo.isConnected
        }

        isInternetConnectedLiveData.postValue(isInternetConnected)
        return isInternetConnected
    }
}
