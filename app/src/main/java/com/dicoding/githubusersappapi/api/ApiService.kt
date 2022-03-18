package com.dicoding.githubusersappapi.api

import com.dicoding.githubusersappapi.data.SearchResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearch(
        @Query("q") query: String
    ): Call<SearchResponse>
}