package com.emranul.infiniteCatsList.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.emranul.infiniteCatsList.data.api.ApiServices
import com.emranul.infiniteCatsList.data.pagingSource.CatsPagingSource
import com.emranul.infiniteCatsList.data.response.CatResponseItem
import com.emranul.infiniteCatsList.data.room.CatDao
import javax.inject.Inject

class CatRepositories @Inject constructor(
    private val apiServices: ApiServices,
    private val catDao: CatDao
    ) {

    fun getCatsFromCloud() = Pager(
        PagingConfig(
            5,
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