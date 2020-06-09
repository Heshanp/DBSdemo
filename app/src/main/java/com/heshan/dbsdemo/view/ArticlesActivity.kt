package com.heshan.dbsdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.heshan.dbsdemo.R
import com.heshan.dbsdemo.adapter.ArticlesAdapter
import com.heshan.dbsdemo.di.AppComponent
import com.heshan.dbsdemo.di.DaggerAppComponent
import com.heshan.dbsdemo.viewModel.ArticlesActivityViewModel
import kotlinx.android.synthetic.main.activity_articles.*
import javax.inject.Inject

class ArticlesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ArticlesActivityViewModel
    private lateinit var adapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)
        DaggerAppComponent.create().inject(this)

        viewModel.showProgress.observe(this, Observer {
            if (it)
                article_progress.visibility = VISIBLE
            else
                article_progress.visibility = GONE
        })

        viewModel.articlesList.observe(this, Observer {
            adapter.setArticles(it)
        })
        viewModel.errorMessage.observe(this, Observer {
            showErrorAlert(it)
        })

        adapter = ArticlesAdapter(this)
        articles_recyclerView.adapter = adapter

        viewModel.getAllArticles()
    }

    private fun showErrorAlert(message: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.retry)) { dialog, id ->
                viewModel.getAllArticles()
            }
            .setNegativeButton(getString(R.string.menu_cancel)) { dialog, id ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.setTitle(getString(R.string.Oops))
        alert.show()
    }
}