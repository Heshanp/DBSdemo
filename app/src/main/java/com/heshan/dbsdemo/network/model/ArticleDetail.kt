package com.heshan.dbsdemo.network.model

import com.google.gson.annotations.SerializedName

data class ArticleDetail (

    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String
)