package com.sam43.githubusers.ui.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.sam43.githubusers.App
import com.sam43.githubusers.R
import com.sam43.githubusers.ui.factory.ViewModelFactory

inline fun <reified T : ViewModel> Fragment.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(
            this,
            ViewModelFactory(creator)
        ).get(T::class.java)
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(
            this,
            ViewModelFactory(creator)
        ).get(T::class.java)
}

fun Context.loadUserAvatar(url: String?, holder: ImageView) {
    if (url != null) {
        Glide.with(this)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(200))
            .apply(
                RequestOptions.placeholderOf(0)
                    .error(R.drawable.ic_android)
                    .dontAnimate().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .skipMemoryCache(true)
            )
            .into(holder)
    } else {
        holder.setImageResource(R.drawable.ic_android)
    }
}

fun Context.pop(msg: String) =
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

inline fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun isInternetConnected(): Boolean {
    val isInternetConnected: Boolean
    val connectivityManager = App.applicationContext()
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    isInternetConnected = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    } else {
        val nwInfo = connectivityManager.activeNetworkInfo ?: return false
        nwInfo.isConnected
    }
    return isInternetConnected
}