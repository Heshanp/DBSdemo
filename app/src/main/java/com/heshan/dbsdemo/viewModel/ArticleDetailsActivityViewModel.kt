package com.heshan.dbsdemo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heshan.dbsdemo.network.model.Article
import com.heshan.dbsdemo.network.model.ArticleDetail
import com.heshan.dbsdemo.repository.ArticleDetailsActivityRepository
import com.heshan.dbsdemo.repository.ArticlesActivityRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleDetailsActivityViewModel @Inject constructor(private val repository : ArticleDetailsActivityRepository) : ViewModel(){

    val showProgress : LiveData<Boolean>
    val articleDetail : LiveData<ArticleDetail>
    val errorMessage : LiveData<String>

    init {
        this.showProgress = repository.showProgress
        this.articleDetail = repository.articlesDetail
        this.errorMessage = repository.errorMessage
    }


    fun changeState(){
        repository.changeState()
    }

    fun getArticleDetails(articleId : Int) = viewModelScope.launch{
        repository.getArticleDetails(articleId)
    }
}