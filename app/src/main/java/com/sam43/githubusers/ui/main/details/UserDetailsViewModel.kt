package com.sam43.githubusers.ui.main.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam43.githubusers.models.GithubUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailsViewModel : ViewModel() {

    val userDetailLiveData = MutableLiveData<GithubUser?>()

    fun setUserDetail(user: GithubUser) {
        viewModelScope.launch(Dispatchers.IO) {
            userDetailLiveData.postValue(user)
        }
    }

}
