package com.emranul.mymvvm.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emranul.mymvvm.data.response.CatResponseItem

@Database(
    entities = [CatResponseItem::class],
    version = 2
)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao():CatDao
}