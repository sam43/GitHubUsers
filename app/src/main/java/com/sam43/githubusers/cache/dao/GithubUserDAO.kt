package com.sam43.githubusers.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sam43.githubusers.models.GithubUser

@Dao
interface GithubUserDAO {

    /*@Query("SELECT * FROM GithubUser")
    fun getAllUsers(): LiveData<List<GithubUser?>?>?*/
    // DAO doesn't work with MutableList

    @Query("SELECT * FROM GithubUser")
    fun getAllUsers(): List<GithubUser?>

    @Query("SELECT * FROM GithubUser LIMIT :limit OFFSET :offset")
    fun getPaginatedUserList(offset: Int, limit: Int): LiveData<MutableList<GithubUser?>?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserList(gitHubUsers: MutableList<GithubUser?>?)
}