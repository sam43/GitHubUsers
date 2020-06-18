package com.sam43.githubusers.ui.main.details

import androidx.lifecycle.MutableLiveData
import com.sam43.githubusers.models.GithubUser
import com.sam43.githubusers.ui.factory.BaseViewModel

class UserDetailsViewModel : BaseViewModel() {

    val userDetailLiveData = MutableLiveData<GithubUser?>()

    fun setUserDetail(user: GithubUser) {
        userDetailLiveData.postValue(user)
    }

}
