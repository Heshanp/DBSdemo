package com.heshan.dbsdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.heshan.dbsdemo.R
import com.heshan.dbsdemo.network.model.Article
import com.heshan.dbsdemo.network.model.ArticleItem
import com.heshan.dbsdemo.viewModel.ArticleDetailsActivityViewModel
import com.heshan.dbsdemo.viewModel.ArticlesActivityViewModel
import kotlinx.android.synthetic.main.activity_article_details.*

class ArticleDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: ArticleDetailsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra("Article")) {
            val selectedArticleItem: ArticleItem = intent.getParcelableExtra("Article")
            title = selectedArticleItem.title
            viewModel = ViewModelProvider(this).get(ArticleDetailsActivityViewModel::class.java)
            viewModel.getArticleDetails(selectedArticleItem.id)
            Glide.with(this).load(selectedArticleItem.avatar).into(article_detail_avatar)
            viewModel.articleDetail.observe(this, Observer {
                article_full_desc.text = it.text
            })
            //Toast.makeText(application,"Selected ${selectedArticleItem.id}", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.article_detail_menu, menu)
        return true
    }
}