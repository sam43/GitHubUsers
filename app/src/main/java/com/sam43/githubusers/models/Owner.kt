package com.sam43.githubusers.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity
data class Owner(
    @PrimaryKey
    @NotNull
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") var avatar_url: String? = null,
    @SerializedName("url") var url: String? = null
): Serializable