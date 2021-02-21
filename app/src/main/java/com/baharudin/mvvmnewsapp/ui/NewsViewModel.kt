package com.baharudin.mvvmnewsapp.ui

import androidx.lifecycle.ViewModel
import com.baharudin.mvvmnewsapp.repository.NewsRepository

class NewsViewModel(
val newsRepository: NewsRepository
) : ViewModel(){
}