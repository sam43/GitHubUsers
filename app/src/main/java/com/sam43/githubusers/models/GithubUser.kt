package com.sam43.githubusers.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GithubUser(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("full_name") var full_name: String? = null,
    @SerializedName("html_url") var html_url: String? = null,
    @SerializedName("owner") var owner: Owner
): Serializable