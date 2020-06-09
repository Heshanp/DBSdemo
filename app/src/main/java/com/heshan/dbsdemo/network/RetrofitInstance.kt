package com.heshan.dbsdemo.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {
    companion object{
        //THIS API DOES NOT WORK SINCE IT HAS EXCEEDED 50 REQUESTS PER DAY
        //Also the 2nd API response JSON is not formatted correctly (when it worked 1 time).
        //I used my own local server to test.
        //const val BASE_URL = "http://10.0.2.2:64604/api/visitormanagement/"
        private const val BASE_URL = "https://task.free.beeceptor.com/"
        private val interceptor = HttpLoggingInterceptor().apply{
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        private val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                    .setLenient()//Added this to remedy JSON format issue but couldn't test due to the API is blocked.
                    .create()))
                .build()
        }
    }

}