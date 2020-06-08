package com.heshan.dbsdemo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.heshan.dbsdemo.network.model.Article
import com.heshan.dbsdemo.network.model.ArticleDetail
import com.heshan.dbsdemo.repository.ArticleDetailsActivityRepository
import com.heshan.dbsdemo.repository.ArticlesActivityRepository

class ArticleDetailsActivityViewModel : ViewModel(){

    private val repository  = ArticleDetailsActivityRepository()
    val showProgress : LiveData<Boolean>
    val articleDetail : LiveData<ArticleDetail>

    init {
        this.showProgress = repository.showProgress
        this.articleDetail = repository.articlesDetail
    }


    fun changeState(){
        repository.changeState()
    }

    fun getArticleDetails(articleId : Int){
        repository.getArticleDetails(articleId)
    }
}