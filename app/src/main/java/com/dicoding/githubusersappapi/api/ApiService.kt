package com.dicoding.githubusersappapi.api

import com.dicoding.githubusersappapi.data.*
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
    ): Call<ArrayList<ItemResponse>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<ItemResponse>>
}