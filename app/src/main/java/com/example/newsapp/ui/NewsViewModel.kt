package com.example.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    /**liveData*/
    val breakingNews :MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    /**pageNumber**/
    var breakingNewsPage = 1

    /**func from repository**/
    fun getBreakingNews(countryCode: String) = viewModelScope.launch{
        breakingNews.postValue(Resource.Loading())
        /**get response**/
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)

        breakingNews.postValue(handleBreakingNewsResponse(response))
    }
    /**call the breaking news func**/
    init{
        /**add internet permission on ur Manifest for this to work**/
        getBreakingNews("ng")
    }

    /**this returns success response if the response is successful and vice versa**/
    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let{
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}