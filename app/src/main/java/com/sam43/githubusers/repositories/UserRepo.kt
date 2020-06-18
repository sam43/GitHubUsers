package com.sam43.githubusers.repositories

import androidx.lifecycle.LiveData
import com.sam43.githubusers.App.Companion.applicationContext
import com.sam43.githubusers.cache.CacheDatabase
import com.sam43.githubusers.models.GithubUser
import com.sam43.githubusers.services.BaseRepository
import com.sam43.githubusers.services.ServiceApi

open class UserRepo(private val api : ServiceApi): BaseRepository() {

    open var userListLiveData: MutableList<GithubUser?>? = mutableListOf()

    private var appDB: CacheDatabase? = null

    init {
        appDB = CacheDatabase.getInstance(applicationContext())
    }


    suspend fun getUsersListFromServer() : MutableList<GithubUser?>? {
        val response = safeApiCall(
            call = { api.getUserListAsync().await() },
            errorMessage = "Error Fetching users list"
        )
        userListLiveData = if (response !== null) response.toMutableList() else mutableListOf()
        return response?.toMutableList()
    }

    fun getGithubUserListOffline(): MutableList<GithubUser?>? {
        var liveData: LiveData<MutableList<GithubUser?>?>?
        appDB?.githubUserDao?.getAllUsers().let {
            liveData = it
        }
        return liveData?.value
    }

}