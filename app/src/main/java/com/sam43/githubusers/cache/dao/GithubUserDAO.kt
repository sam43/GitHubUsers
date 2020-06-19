package com.sam43.githubusers.cache.dao

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
    suspend fun getAllUsers(): List<GithubUser?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserList(gitHubUsers: MutableList<GithubUser?>?)
}