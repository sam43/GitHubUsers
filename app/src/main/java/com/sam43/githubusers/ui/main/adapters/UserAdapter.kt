package com.sam43.githubusers.ui.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sam43.githubusers.R
import com.sam43.githubusers.databinding.ItemGithubUserBinding
import com.sam43.githubusers.models.GithubUser


@Suppress("UNCHECKED_CAST")
class UserAdapter(val context: Context, private val userList: MutableList<GithubUser?>, private val clickListener: (GithubUser) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_github_user,
            parent,
            false
            )
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = userList[position]
        holder.itemGithubUserBinding.user = item
        holder.bind(context, item, clickListener as (GithubUser?) -> Unit)
    }

    override fun getItemCount() = userList.size

    inner class UserViewHolder(val itemGithubUserBinding: ItemGithubUserBinding) :
        RecyclerView.ViewHolder(itemGithubUserBinding.root)
    {
        fun bind(context: Context, item: GithubUser?, clickListener: (GithubUser?) -> Unit) {
            itemView.setOnClickListener { clickListener(item)}
        }

    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadUserAvatar(imageView: ImageView, url: String) {
            Glide.with(imageView).load(url).into(imageView) // "https://i.imgur.com/4i4EYJ7.jpg"
        }
    }
}