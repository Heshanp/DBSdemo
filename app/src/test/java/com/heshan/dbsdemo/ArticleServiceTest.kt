package com.heshan.dbsdemo

import com.heshan.dbsdemo.network.NetworkService
import com.heshan.dbsdemo.network.RetrofitInstance
import org.junit.Assert.*
import org.junit.Test

class ArticleServiceTest {
    private val service = RetrofitInstance.getInstance().create(NetworkService::class.java)!!

    @Test
    fun test_AllArticlesAPIResponse(){
        val response = service.getAllArticles().execute()
        assertEquals(200, response.code())
    }

    @Test
    fun test_getArticleFromId(){
        val response = service.getArticleDetails(1).execute()
        assertEquals(200, response.code())
    }
}