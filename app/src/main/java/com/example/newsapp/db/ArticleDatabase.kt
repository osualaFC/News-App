package com.example.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
/**check converter class**/
@TypeConverters(Converters::class)

abstract class ArticleDatabase : RoomDatabase(){

    abstract fun getArticleDao(): ArticleDao

    companion object{
        /**Volatile --other threads can see when a thread changes its instance*/
        @Volatile
        private var instance: ArticleDatabase? = null
        /**use to synchronise setting the instance--makes sure there is only a single instance of db at once*/
        private val LOCK = Any()

        /**called when ever an instance of the db is created**/
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{instance = it}
        }

        /**create db func*/
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}
/**SETTING UP ROOM
 * Create ur Entity
 * Create the Dao and its accompany funcs
 * Create  the database**/