package com.sam43.githubusers.ui.main.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder /*=
        UserViewHolder(
            DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_github_user,
            parent,
            false
            )
        )*/
    {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemGithubUserBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.item_github_user,
            parent, false)
        return UserViewHolder(binding)
    }

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
            //context.loadUserAvatar(currentItem?.owner?.avatar_url, holder.userAvatar)
/*            itemView.user_id.text = context.getString(R.string.user_id_placeholder).plus(item?.id.toString())
            itemView.user_name.text = item?.name
            itemView.user_full_name.text = item?.full_name*/
            itemView.setOnClickListener { clickListener(item)}
        }

    }

    /*@BindingAdapter("bind:imgUrl")
    fun setProfileAvatar(
        imageView: ImageView,
        imgUrl: String?
    ) =
        Glide.with(imageView.context).load(imgUrl).into(imageView)*/

    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun loadUserAvatar(imageView: ImageView, url: String) {
            Glide.with(imageView).load(url).into(imageView) // "https://i.imgur.com/4i4EYJ7.jpg"
        }
    }
}