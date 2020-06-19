package com.sam43.githubusers.repositories

import android.util.Log
import com.sam43.githubusers.App.Companion.applicationContext
import com.sam43.githubusers.cache.database.CacheDatabase
import com.sam43.githubusers.models.GithubUser
import com.sam43.githubusers.services.BaseRepository
import com.sam43.githubusers.services.ServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class UserRepo(private val api : ServiceApi): BaseRepository() {

    private var appDB: CacheDatabase? = null

    init {
        appDB = CacheDatabase.getInstance(applicationContext())
    }


    suspend fun getUsersListFromServer() : MutableList<GithubUser?>? {
        val response = safeApiCall(
            call = { api.getUserListAsync().await() },
            errorMessage = "Error Fetching users list"
        )
        val userListLiveData: MutableList<GithubUser?>? =
            if (response !== null) response.toMutableList() else mutableListOf()
        insertUserListToCacheDB(response?.toMutableList())
        return userListLiveData
    }

    private suspend fun insertUserListToCacheDB(list: MutableList<GithubUser?>?) {
        list?.let { users ->
            withContext(Dispatchers.IO) {
                appDB?.githubUserDao?.insertUserList(users)
                Log.d("Local_cache", "data inserted")
            }
        }
    }

    fun getGithubUserListOffline(): MutableList<GithubUser?>? {
        return appDB?.githubUserDao?.getAllUsers()?.toMutableList() ?: mutableListOf()
    }
}