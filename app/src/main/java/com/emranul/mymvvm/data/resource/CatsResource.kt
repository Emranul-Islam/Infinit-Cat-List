package com.emranul.mymvvm.data.resource

sealed class CatsResource<T>(
    val data:T? = null,
    val error:String?=null
){
    class Success<T>(data: T):CatsResource<T>(data)
    class Error<T>(error: String,data: T?=null):CatsResource<T>(data, error)
    class Loading<T>:CatsResource<T>()
}
