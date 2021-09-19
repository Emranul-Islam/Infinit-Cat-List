package com.emranul.mymvvm.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.emranul.mymvvm.data.api.ApiServices
import com.emranul.mymvvm.data.pagingSource.CatsPagingSource
import com.emranul.mymvvm.data.response.CatResponseItem
import com.emranul.mymvvm.data.room.CatDao
import javax.inject.Inject

class CatRepositories @Inject constructor(
    private val apiServices: ApiServices,
    private val catDao: CatDao
    ) {

    fun getCatsFromCloud() = Pager(
        PagingConfig(
            25,
            enablePlaceholders = false
        )
    ){
        CatsPagingSource(apiServices)
    }.flow

    fun getLocalCats() = Pager(
        PagingConfig(30)
    ){
        catDao.getLocalCats()
    }.flow

    suspend fun saveCat(catResponseItem: CatResponseItem) = catDao.saveCat(catResponseItem)

    suspend fun deleteCat(catResponseItem: CatResponseItem) = catDao.deleteCat(catResponseItem)

}