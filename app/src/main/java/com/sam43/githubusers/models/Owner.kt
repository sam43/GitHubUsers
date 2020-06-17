package com.sam43.githubusers.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner(
    @SerializedName("avatar_url") var avatar_url: String? = null,
    /*@SerializedName("") val events_url: String,
    @SerializedName("") val followers_url: String,
    @SerializedName("") val following_url: String,
    @SerializedName("") val gists_url: String,
    @SerializedName("") val gravatar_id: String,
    @SerializedName("") val html_url: String,
    @SerializedName("") val id: Int,
    @SerializedName("") val login: String,
    @SerializedName("") val node_id: String,
    @SerializedName("") val organizations_url: String,
    @SerializedName("") val received_events_url: String,
    @SerializedName("") val repos_url: String,
    @SerializedName("") val starred_url: String,
    @SerializedName("") val subscriptions_url: String,
    @SerializedName("") val type: String,*/
    @SerializedName("url") var url: String? = null
): Serializable