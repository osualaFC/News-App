package com.example.newsapp.repository


import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.models.Article

class NewsRepository(val db :ArticleDatabase) {

    suspend fun getBreakingNews(countryCode:String, pageNumber:Int) = RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun getSearchNews(querySearch: String, pageNumber: Int) = RetrofitInstance.api.searchNews(querySearch, pageNumber)


    suspend fun insert(article: Article) = db.getArticleDao().insert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}

/**
 * Repository takes in  the db as parameter
 * it contains funcs that calls the api
 * viewModel implements the funcs
 * **/