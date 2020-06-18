package com.sam43.githubusers.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "GithubUser")
data class GithubUser(
    @PrimaryKey(autoGenerate = false)
    @NotNull
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: String,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String? = null,
    @SerializedName("full_name")
    @ColumnInfo(name = "full_name")
    var full_name: String? = null,
    @SerializedName("url")
    @ColumnInfo(name = "url")
    var url: String? = null,
    @SerializedName("html_url")
    @ColumnInfo(name = "html_url")
    var html_url: String? = null
    /*@SerializedName("owner")
    var owner: Owner*/
): Serializable