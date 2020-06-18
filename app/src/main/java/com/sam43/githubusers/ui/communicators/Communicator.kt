package com.sam43.githubusers.ui.communicators

import com.sam43.githubusers.models.GithubUser

// This can also be done with VM but I like to do this way
interface Communicator {
    fun startDetailFragmentWith(value: GithubUser)
}