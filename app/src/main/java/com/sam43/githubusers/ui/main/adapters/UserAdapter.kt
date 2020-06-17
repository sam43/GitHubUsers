package com.sam43.githubusers.ui.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sam43.githubusers.R
import com.sam43.githubusers.models.GithubUser
import com.sam43.githubusers.ui.utils.loadUserAvatar
import kotlinx.android.synthetic.main.item_github_user.view.*

class UserAdapter(private val userList: MutableList<GithubUser?>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_github_user,
            parent, false
        )
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = userList[position]
        context.loadUserAvatar(currentItem?.owner?.avatar_url, holder.userAvatar)
        holder.userID.text = currentItem?.id.toString()
        holder.userName.text = currentItem?.name
        holder.userFullName.text = currentItem?.full_name
    }

    override fun getItemCount() = userList.size
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userAvatar: ImageView = itemView.user_avatar
        val userID: TextView = itemView.user_id
        val userName: TextView = itemView.user_name
        val userFullName: TextView = itemView.user_full_name
    }
}