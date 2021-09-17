package com.emranul.mymvvm.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.emranul.mymvvm.data.response.CatResponseItem

@Dao
interface CatDao {

    @Query("SELECT * FROM cat_table")
    fun getLocalCats():LiveData<List<CatResponseItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCat(catResponseItem: CatResponseItem):Long

    @Delete
    suspend fun deleteCat(catResponseItem: CatResponseItem)
}