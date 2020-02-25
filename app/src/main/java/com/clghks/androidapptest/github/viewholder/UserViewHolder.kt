package com.clghks.androidapptest.github.viewholder

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clghks.androidapptest.R
import com.clghks.androidapptest.common.network.model.Item
import com.clghks.androidapptest.common.view.recycler.RecyclerViewItemClickListener
import com.clghks.androidapptest.github.manager.GithubLikeManager

class UserViewHolder(itemView: View, private val clickListener: RecyclerViewItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val userImage = itemView.findViewById(R.id.user_image) as ImageView
    private val userName = itemView.findViewById(R.id.user_name) as TextView
    private val userScore = itemView.findViewById(R.id.user_score) as TextView
    private val likeButton = itemView.findViewById(R.id.like_button) as Button

    override fun onClick(v: View) {
        clickListener?.onClickItemListener(v, adapterPosition)
    }

    fun setItem(context: Context, item: Item) {
        Glide.with(context).load(item.avatarUrl).into(userImage)
        userName.text = item.login
        userScore.text = "${item.score}"

        likeButton.text = if (GithubLikeManager.isLikeUser(item.id ?: -1)) context.getString(R.string.cancel) else context.getString(R.string.like)
        likeButton.setOnClickListener(this)
    }
}