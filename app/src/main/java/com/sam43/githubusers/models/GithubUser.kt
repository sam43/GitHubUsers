package com.sam43.githubusers.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GithubUser(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val full_name: String,
    @SerializedName("owner") val owner: Owner
): Serializable