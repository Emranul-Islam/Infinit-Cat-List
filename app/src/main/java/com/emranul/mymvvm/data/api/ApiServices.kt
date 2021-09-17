package com.emranul.mymvvm.data.api

import com.emranul.mymvvm.data.response.CatResponse
import com.emranul.mymvvm.data.response.CatResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiServices {
    companion object{
        const val apiKey = "886257b5-2b0f-49df-bd43-5a13d5741583"
       // apiName = "x-api-key"

    }

    @Headers("x-api-key: $apiKey")
    @GET("images/search")
    suspend fun getImages(
        @Query("limit")
        limit:Int
    ):Response<CatResponse>


}