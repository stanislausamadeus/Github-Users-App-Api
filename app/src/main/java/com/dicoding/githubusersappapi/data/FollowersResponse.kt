package com.dicoding.githubusersappapi.data

import com.google.gson.annotations.SerializedName

data class FollowersResponse(
    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("html_url")
    val htmlUrl: String
)
