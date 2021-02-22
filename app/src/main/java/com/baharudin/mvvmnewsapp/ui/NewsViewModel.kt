package com.baharudin.mvvmnewsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baharudin.mvvmnewsapp.model.NewsResponse
import com.baharudin.mvvmnewsapp.repository.NewsRepository
import com.baharudin.mvvmnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
val newsRepository: NewsRepository
) : ViewModel(){
    val breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    val searchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNewsPage = 1

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode : String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun getSearchNews(searchQuery : String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.getSearchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }
    private fun handleBreakingNewsResponse(response : Response<NewsResponse>) : Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultRespnse ->
                return Resource.Success(resultRespnse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response : Response<NewsResponse>) : Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultRespnse ->
                return Resource.Success(resultRespnse)
            }
        }
        return Resource.Error(response.message())
    }
}