package com.wahidabd.mandirinews.data

import com.wahidabd.mandirinews.data.model.NewsResultResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


interface ApiService {

    @GET("top-headlines?country=us")
    suspend fun headlines(
        @Query("page") page: Int
    ): NewsResultResponse

    @GET("everything")
    suspend fun search(
        @Query("q") q: String,
        @Query("page") page: Int
    ): NewsResultResponse

}