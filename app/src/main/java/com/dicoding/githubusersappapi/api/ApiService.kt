package com.dicoding.githubusersappapi.api

import com.dicoding.githubusersappapi.BuildConfig
import com.dicoding.githubusersappapi.data.*
import retrofit2.Call
import retrofit2.http.*

const val githubToken = BuildConfig.GITHUB_TOKEN

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token $githubToken")
    fun getSearch(
        @Query("q") query: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: $githubToken")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token $githubToken")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<ItemResponse>>

    @GET("users/{username}/following")
    @Headers("Authorization: token $githubToken")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<ItemResponse>>
}