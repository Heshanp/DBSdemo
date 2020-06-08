package com.heshan.dbsdemo.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.heshan.dbsdemo.network.BASE_URL
import com.heshan.dbsdemo.network.NetworkService
import com.heshan.dbsdemo.network.model.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticlesActivityRepository {
    val showProgress = MutableLiveData<Boolean>()
    val articlesList = MutableLiveData<Article>()

    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun getAllArticles() {
        showProgress.value = true

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NetworkService::class.java)

        //service.getAllArticles(searchString).enqueue(object  : Callback<List<Article>>{
        service.getAllArticles().enqueue(object : Callback<Article> {
            override fun onFailure(call: Call<Article>, t: Throwable) {
                showProgress.value = false
                //Toast.makeText(application,"Error wile accessing the API",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Article>,
                response: Response<Article>
            ) {
                Log.d("MyTag", "Response : ${Gson().toJson(response.body())}")
                articlesList.value = response.body()
                showProgress.value = false
            }

        })
    }
}