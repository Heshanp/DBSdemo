package com.heshan.dbsdemo.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.heshan.dbsdemo.network.BASE_URL
import com.heshan.dbsdemo.network.NetworkService
import com.heshan.dbsdemo.network.model.ArticleDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleDetailsActivityRepository {
    val showProgress = MutableLiveData<Boolean>()
    val articlesDetail = MutableLiveData<ArticleDetail>()

    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun getArticleDetails(articleId : Int) {
        showProgress.value = true

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NetworkService::class.java)

        //service.getAllArticles(searchString).enqueue(object  : Callback<List<Article>>{
        service.getArticleDetails(articleId).enqueue(object : Callback<ArticleDetail> {
            override fun onFailure(call: Call<ArticleDetail>, t: Throwable) {
                showProgress.value = false
                //Toast.makeText(application,"Error wile accessing the API",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ArticleDetail>,
                response: Response<ArticleDetail>
            ) {
                Log.d("MyTag", "Response : ${Gson().toJson(response.body())}")
                articlesDetail.value = response.body()
                showProgress.value = false
            }
        })
    }
}