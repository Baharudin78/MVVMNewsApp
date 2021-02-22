package com.baharudin.mvvmnewsapp.repository

import com.baharudin.mvvmnewsapp.api.RetrofitInstance
import com.baharudin.mvvmnewsapp.db.ArticleDatabase

class NewsRepository (
    val articleDb : ArticleDatabase)
{
    suspend fun getBreakingNews(countryCode : String, pageNumber : Int) =
            RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun getSearchNews(searchQuery : String, pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchQuery,pageNumber)
}