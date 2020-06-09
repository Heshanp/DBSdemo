package com.heshan.dbsdemo.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.heshan.dbsdemo.network.NetworkService
import com.heshan.dbsdemo.network.RetrofitInstance
import com.heshan.dbsdemo.network.model.ArticleDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import javax.inject.Inject

class ArticleDetailsActivityRepository @Inject constructor() {
    val showProgress = MutableLiveData<Boolean>()
    val articlesDetail = MutableLiveData<ArticleDetail>()
    val errorMessage = MutableLiveData<String>()

    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun getArticleDetails(articleId : Int) {
        showProgress.value = true
        val service = RetrofitInstance.getInstance().create(NetworkService::class.java)
        service.getArticleDetails(articleId).enqueue(object : Callback<ArticleDetail> {
            override fun onFailure(call: Call<ArticleDetail>, t: Throwable) {
                showProgress.value = false
                errorMessage.value = t.message
            }

            override fun onResponse(
                call: Call<ArticleDetail>,
                response: Response<ArticleDetail>
            ) {
                Log.d("MyTag", "Response : ${Gson().toJson(response.body())}")
                showProgress.value = false
                try {
                    articlesDetail.value = response.body()
                }catch (e: Exception){
                    errorMessage.value = e.message
                }
            }
        })
    }
}