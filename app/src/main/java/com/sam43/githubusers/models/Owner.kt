package com.sam43.githubusers.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//@Entity(tableName = "Owner")
data class Owner(
    //@PrimaryKey(autoGenerate = false)
    //@NotNull
    @SerializedName("id")
    //@ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("avatar_url")
    //@ColumnInfo(name = "avatar_url")
    var avatar_url: String? = null,
    @SerializedName("url")
    //@ColumnInfo(name = "url")
    var url: String? = null
): Serializable