package com.emranul.mymvvm.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Clint {

    companion object{

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(ApiServices.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiServices:ApiServices by lazy {
            retrofit.create(ApiServices::class.java)
        }

    }
}