package com.baharudin.mvvmnewsapp.util

sealed class Resource<T>(
    val data: T? = null,
    val messege : String? = null
){
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(data: T?,messege: String? = null) : Resource<T>(data,messege)
    class Loading<T> : Resource<T>()
}