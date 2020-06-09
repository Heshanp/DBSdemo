package com.heshan.dbsdemo.network

import com.heshan.dbsdemo.network.model.Article
import com.heshan.dbsdemo.network.model.ArticleDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("article")
    fun getAllArticles(): Call<Article>

    @GET("articleid/{id}")
    fun getArticleDetails(@Path("id") articleId: Int): Call<ArticleDetail>
}