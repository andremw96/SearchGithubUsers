package com.andreamw96.andreamettawijaya.feature.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andreamw96.andreamettawijaya.R
import com.andreamw96.andreamettawijaya.datasource.network.user.GithubUserResponse
import com.bumptech.glide.Glide

class MainAdapter(private val ctx: Context) : RecyclerView.Adapter<MainAdapter.ListUserViewHolder>() {

    var listUser = mutableListOf<GithubUserResponse>()

    fun bindData(users: List<GithubUserResponse>) {
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        return ListUserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        val user = listUser[position]

        holder.apply {
            Glide.with(holder.itemView)
                .load(user.avatarUrl)
                .placeholder(R.drawable.ic_person)
                .into(imgPhoto)

            txtName.text = user.login
        }
    }

    class ListUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto = itemView.findViewById<ImageView>(R.id.img_profile)
        val txtName = itemView.findViewById<TextView>(R.id.txt_name)
    }
}