package com.emranul.mymvvm.data.repositories

import com.emranul.mymvvm.data.api.ApiServices
import com.emranul.mymvvm.data.response.CatResponseItem
import com.emranul.mymvvm.data.room.CatDao
import javax.inject.Inject

class CatRepositories @Inject constructor(
    private val apiServices: ApiServices,
    private val catDao: CatDao
    ) {

    suspend fun getCatsFromCloud(limit:Int)=apiServices.getImages(limit)

    fun getLocalCats() = catDao.getLocalCats()

    suspend fun saveCat(catResponseItem: CatResponseItem) = catDao.saveCat(catResponseItem)

    suspend fun deleteCat(catResponseItem: CatResponseItem) = catDao.deleteCat(catResponseItem)

}