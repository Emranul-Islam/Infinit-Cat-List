package com.emranul.infiniteCatsList.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emranul.infiniteCatsList.data.response.CatResponseItem

@Database(
    entities = [CatResponseItem::class],
    version = 2
)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao():CatDao
}