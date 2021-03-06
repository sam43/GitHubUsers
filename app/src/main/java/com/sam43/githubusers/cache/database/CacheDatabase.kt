package com.sam43.githubusers.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sam43.githubusers.cache.dao.GithubUserDAO
import com.sam43.githubusers.models.GithubUser

@Database(entities = [GithubUser::class], version = 1, exportSchema = false)
abstract class CacheDatabase : RoomDatabase() {

    abstract val githubUserDao: GithubUserDAO

    companion object {

        private const val DB_NAME = "CacheDB"
        private var instance: CacheDatabase? = null

        val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }

        @Synchronized
        fun getInstance(context: Context): CacheDatabase? = when (instance) {

            null -> {
                instance =
                    Room.databaseBuilder(
                        context,
                        CacheDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()

                instance
            }

            else -> instance

        }
    }
}