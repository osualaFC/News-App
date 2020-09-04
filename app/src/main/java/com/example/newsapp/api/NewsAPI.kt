package com.example.newsapp.api

import com.example.newsapp.NewsResponse
import com.example.newsapp.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
/**setUp to get headlines from api*/
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "ng",

        @Query("page")
        pageNumber: Int = 1,

        @Query("apiKey")
        apiKey:String =API_KEY
    ): Response<NewsResponse>

    /**setUp to get all news from api**/
    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        querySearch: String,

        @Query("page")
        pageNumber: Int = 1,

        @Query("apiKey")
        apiKey:String =API_KEY
    ): Response<NewsResponse>
}

/**
 * setUp ur json object
 * create the interface u will use to interact with the api
 * setUp retrofit Instance**/