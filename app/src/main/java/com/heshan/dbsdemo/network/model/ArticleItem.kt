package com.heshan.dbsdemo.network.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleItem(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_update")
    val lastUpdate: Int,
    @SerializedName("short_description")
    val shortDescription: String,
    @SerializedName("title")
    val title: String
) : Parcelable