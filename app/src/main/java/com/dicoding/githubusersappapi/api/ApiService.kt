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
    @Headers("Authorization: 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")
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