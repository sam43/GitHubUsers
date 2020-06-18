package com.sam43.githubusers

import android.app.Application
import android.content.Context
import com.sam43.githubusers.cache.database.CacheDatabase

class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any

        // Use ApplicationContext.
        // example: SharedPreferences etc...
        val context: Context? = applicationContext()
        context?.let { CacheDatabase.getInstance(it) }
    }
}