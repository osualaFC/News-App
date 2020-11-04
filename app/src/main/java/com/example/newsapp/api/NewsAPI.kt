package com.example.newsapp.api

import com.example.newsapp.models.NewsResponse
import com.example.newsapp.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "ng",

        @Query("page")
        pageNumber: Int = 1,

        @Query("apiKey")
        apiKey:String =API_KEY
    ): Response<NewsResponse>


    @GET("everything")
    suspend fun searchNews(
        @Query("q")
        querySearch: String,

        @Query("page")
        pageNumber: Int = 1,

        @Query("apiKey")
        apiKey:String =API_KEY
    ): Response<NewsResponse>
}

