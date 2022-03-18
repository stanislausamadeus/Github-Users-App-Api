package com.dicoding.githubusersappapi.api

import com.dicoding.githubusersappapi.data.DetailResponse
import com.dicoding.githubusersappapi.data.FollowersResponse
import com.dicoding.githubusersappapi.data.FollowingResponse
import com.dicoding.githubusersappapi.data.SearchResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearch(
        @Query("q") query: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<FollowersResponse>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<FollowingResponse>>
}