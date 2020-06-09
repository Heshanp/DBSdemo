package com.heshan.dbsdemo.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.heshan.dbsdemo.network.NetworkService
import com.heshan.dbsdemo.network.RetrofitInstance
import com.heshan.dbsdemo.network.model.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ArticlesActivityRepository @Inject constructor() {
    val showProgress = MutableLiveData<Boolean>()
    val articlesList = MutableLiveData<Article>()
    val errorMessage = MutableLiveData<String>()

    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun getAllArticles() {
        showProgress.value = true
        val service = RetrofitInstance.getInstance().create(NetworkService::class.java)
        service.getAllArticles().enqueue(object : Callback<Article> {
            override fun onFailure(call: Call<Article>, t: Throwable) {
                showProgress.value = false
                errorMessage.value = t.message
            }

            override fun onResponse(
                call: Call<Article>,
                response: Response<Article>
            ) {
                Log.d("MyTag", "Response : ${Gson().toJson(response.body())}")
                showProgress.value = false
                if(response.isSuccessful){
                    try {
                        articlesList.value = response.body()
                    }catch (e: Exception){
                        errorMessage.value = e.message
                    }
                }else{
                    errorMessage.value = response.message()
                }
            }

        })
    }
}