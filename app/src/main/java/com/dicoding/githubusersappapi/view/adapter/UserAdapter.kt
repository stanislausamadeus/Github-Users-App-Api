package com.dicoding.githubusersappapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.githubusersappapi.data.response.ItemResponse
import com.dicoding.githubusersappapi.databinding.ItemUserBinding
import com.dicoding.githubusersappapi.helper.UsersDiffUtil

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private var list = ArrayList<ItemResponse>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(users: ArrayList<ItemResponse>) {
        val diffUtil = UsersDiffUtil(list, users)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        list.clear()
        list.addAll(users)
        list = users
        diffResult.dispatchUpdatesTo(this)
    }

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemResponse) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(item)
            }

            Glide.with(itemView)
                .load(item.avatarUrl)
                .circleCrop()
                .into(binding.imgItemPhoto)
            binding.tvItemUsername.text = item.login
            binding.tvItemWebsite.text = item.htmlUrl
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

    interface OnItemClickCallback{
        fun onItemClicked(data: ItemResponse)
    }
}