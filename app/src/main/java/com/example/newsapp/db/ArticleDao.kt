package com.example.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.models.Article

@Dao
interface ArticleDao {

    /**if an article already exist in the db..then replace**/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    /**insert or update*/
    suspend fun upsert(article: Article): Long

    /**get all articles**/
    @Query("SELECT * FROM articles")
    /**i didnt use suspend fun becos suspend funs does not work with LiveData**/
    fun getAllArticles(): LiveData<List<Article>>

    /**delete article**/
    @Delete
    suspend fun deleteArticle(article: Article)

}