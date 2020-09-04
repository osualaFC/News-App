package com.example.newsapp.api

import com.example.newsapp.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{

        private val retrofit by lazy{
            /**enables you to log responses of retrofit--check dependency**/
            val logging = HttpLoggingInterceptor()
            /**enables u to see the actual response**/
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            /**setUp client*/
            val client =OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            /**Retrofit builder*/
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                /**determine how the response should be interpreted--use Gson to convert json to kotlin object**/
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        /**get api instance--this will be use to make request calls**/
        val api by lazy{
            /**pass in ur api interface**/
            retrofit.create(NewsAPI::class.java)
        }
    }
}