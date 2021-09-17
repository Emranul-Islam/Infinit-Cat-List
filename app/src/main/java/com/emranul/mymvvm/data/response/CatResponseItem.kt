package com.emranul.mymvvm.data.response


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cat_table")
data class CatResponseItem(

    @PrimaryKey(autoGenerate = true)
    val key:Int? =null,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)