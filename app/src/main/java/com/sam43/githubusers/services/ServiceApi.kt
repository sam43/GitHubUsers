package com.sam43.githubusers.services

import com.sam43.githubusers.models.GithubUser
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {
    @GET("repositories")
    fun getUserListAsync(): Deferred<Response<ArrayList<GithubUser>>>
}