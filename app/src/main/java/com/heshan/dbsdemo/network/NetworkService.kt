package com.heshan.dbsdemo.network

import com.heshan.dbsdemo.network.model.Article
import com.heshan.dbsdemo.network.model.ArticleDetail
import com.heshan.dbsdemo.network.model.ArticleItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//THIS API DOES NOT WORK SINCE IT HAS EXCEEDED 50 REQUESTS PER DAY
//const val BASE_URL = "https://task.free.beeceptor.com/"
const val BASE_URL = "http://10.0.2.2:64604/"


interface NetworkService {
    //@GET("article")
    @GET("api/visitormanagement/article")
    fun getAllArticles(): Call<Article>

    @GET("api/visitormanagement/articleid/{id}")
    fun getArticleDetails(@Path("id") articleId: Int): Call<ArticleDetail>
}