package com.sam43.githubusers.repositories

import com.sam43.githubusers.models.GithubUser
import com.sam43.githubusers.services.BaseRepository
import com.sam43.githubusers.services.ServiceApi

open class UserRepo(private val api : ServiceApi): BaseRepository() {

    open var userListLiveData: MutableList<GithubUser?>? = mutableListOf()

    suspend fun getUsersListFromServer() : MutableList<GithubUser?>? {
        val response = safeApiCall(
            call = { api.getUserListAsync().await() },
            errorMessage = "Error Fetching users list"
        )
        userListLiveData = if (response !== null) response.toMutableList() else mutableListOf()
        return response?.toMutableList()
    }
}