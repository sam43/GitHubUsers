package com.sam43.githubusers.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity
data class GithubUser(
    @PrimaryKey
    @NotNull
    @SerializedName("id")
    val id: Int,
    @SerializedName("name") var name: String? = null,
    @SerializedName("full_name") var full_name: String? = null,
    @SerializedName("html_url") var html_url: String? = null,
    @SerializedName("owner") var owner: Owner
): Serializable