package com.sam43.githubusers.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner(
    @SerializedName("avatar_url") val avatar_url: String
): Serializable