package com.heshan.dbsdemo.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.heshan.dbsdemo.network.model.Article
import com.heshan.dbsdemo.repository.ArticleDetailsActivityRepository
import com.heshan.dbsdemo.repository.ArticlesActivityRepository
import kotlinx.coroutines.launch

class ArticlesActivityViewModel : ViewModel(){

    private val repository  = ArticlesActivityRepository()
    val showProgress : LiveData<Boolean>
    val articlesList : LiveData<Article>

    init {
        this.showProgress = repository.showProgress
        this.articlesList = repository.articlesList
    }


    fun changeState(){
        repository.changeState()
    }

    fun getAllArticles(){
        repository.getAllArticles()
    }
}