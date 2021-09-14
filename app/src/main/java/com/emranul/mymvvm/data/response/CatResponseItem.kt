package com.emranul.mymvvm.data.response


import com.google.gson.annotations.SerializedName

data class CatResponseItem(

    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)