package com.heshan.dbsdemo.di
import com.heshan.dbsdemo.view.ArticleDetailsActivity
import com.heshan.dbsdemo.view.ArticlesActivity
import dagger.Component



@Component
interface AppComponent {
    fun inject(activity: ArticlesActivity)
    fun inject(activity: ArticleDetailsActivity)
}