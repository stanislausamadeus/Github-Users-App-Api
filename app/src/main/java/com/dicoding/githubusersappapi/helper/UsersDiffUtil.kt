package com.dicoding.githubusersappapi.helper

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.githubusersappapi.data.response.ItemResponse

class UsersDiffUtil(private val oldList: ArrayList<ItemResponse>, private val newList: ArrayList<ItemResponse>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}