package com.sam43.githubusers.ui.utils

import com.sam43.githubusers.models.GithubUser

interface Communicator {
    fun startDetailFragmentWith(value: GithubUser)
}