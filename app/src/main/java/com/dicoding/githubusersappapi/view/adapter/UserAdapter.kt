package com.dicoding.githubusersappapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.githubusersappapi.data.SearchItemResponse
import com.dicoding.githubusersappapi.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private val list = ArrayList<SearchItemResponse>()

    fun setList(users: ArrayList<SearchItemResponse>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchItem: SearchItemResponse) {
            Glide.with(itemView)
                .load(searchItem.avatarUrl)
                .circleCrop()
                .into(binding.imgItemPhoto)
            binding.tvItemUsername.text = searchItem.login
            binding.tvItemWebsite.text = searchItem.htmlUrl
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}