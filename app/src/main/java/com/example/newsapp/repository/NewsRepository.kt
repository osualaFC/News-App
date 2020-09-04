package com.example.newsapp.repository

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase

class NewsRepository(val db :ArticleDatabase) {

    suspend fun getBreakingNews(countryCode:String, pageNumber:Int) = RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(querySearch: String, pageNumber: Int) = RetrofitInstance.api.searchNews(querySearch, pageNumber)
}

/**
 * Repository takes in  the db as parameter
 * it contains funcs that calls the api
 * viewModel implements the funcs
 * **/