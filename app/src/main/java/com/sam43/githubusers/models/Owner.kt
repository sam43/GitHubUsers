package com.sam43.githubusers.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner(
    @SerializedName("avatar_url")
    var avatar_url: String? = null,
    @SerializedName("url")
    var url: String? = null
): Serializable