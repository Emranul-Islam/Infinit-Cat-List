package com.emranul.mymvvm.di

import android.content.Context
import androidx.room.Room
import com.emranul.mymvvm.data.api.ApiServices
import com.emranul.mymvvm.data.room.CatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://api.thecatapi.com/v1/"
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetroInstance()= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiServices::class.java)

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context:Context) =
        Room.databaseBuilder(context,CatDatabase::class.java,"cat_database.db")
            .build()

    @Provides
    @Singleton
    fun provideCatDao(database: CatDatabase) = database.catDao()
}