package com.heshan.dbsdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.heshan.dbsdemo.R
import com.heshan.dbsdemo.adapter.ArticlesAdapter
import com.heshan.dbsdemo.databinding.ActivityArticlesBinding
import com.heshan.dbsdemo.viewModel.ArticlesActivityViewModel
import kotlinx.android.synthetic.main.activity_articles.*

class ArticlesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityArticlesBinding
    private lateinit var viewModel: ArticlesActivityViewModel
    private lateinit var adapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArticlesBinding.inflate(layoutInflater)
        //articlesViewModel = ViewModelProvider(this,factory).get(ArticlesActivityViewModel::class.java)
        viewModel = ViewModelProvider(this).get(ArticlesActivityViewModel::class.java)
        setContentView(R.layout.activity_articles)

        viewModel.showProgress.observe(this, Observer {
            if (it)
                search_progress.visibility = VISIBLE
            else
                search_progress.visibility = GONE
        })

        viewModel.articlesList.observe(this, Observer {
            adapter.setArticles(it)
        })

        adapter = ArticlesAdapter(this)
        articles_recyclerView.adapter = adapter

        viewModel.getAllArticles()
    }



}