package com.heshan.dbsdemo.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.heshan.dbsdemo.network.model.Article
import com.heshan.dbsdemo.repository.ArticleDetailsActivityRepository
import com.heshan.dbsdemo.repository.ArticlesActivityRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticlesActivityViewModel @Inject constructor(private val repository : ArticlesActivityRepository) : ViewModel(){
    val showProgress : LiveData<Boolean>
    val articlesList : LiveData<Article>
    val errorMessage : LiveData<String>

    init {
        this.showProgress = repository.showProgress
        this.articlesList = repository.articlesList
        this.errorMessage = repository.errorMessage
    }


    fun changeState(){
        repository.changeState()
    }

    fun getAllArticles() = viewModelScope.launch{
        repository.getAllArticles()
    }
}
