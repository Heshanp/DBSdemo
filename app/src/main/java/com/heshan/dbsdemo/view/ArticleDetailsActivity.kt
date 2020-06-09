package com.heshan.dbsdemo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.heshan.dbsdemo.R
import com.heshan.dbsdemo.di.DaggerAppComponent
import com.heshan.dbsdemo.network.model.ArticleItem
import com.heshan.dbsdemo.viewModel.ArticleDetailsActivityViewModel
import kotlinx.android.synthetic.main.activity_article_details.*
import kotlinx.android.synthetic.main.activity_articles.*
import javax.inject.Inject

class ArticleDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ArticleDetailsActivityViewModel
    private lateinit var  selectedArticleItem: ArticleItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        DaggerAppComponent.create().inject(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra("Article")) {
            selectedArticleItem = intent.getParcelableExtra("Article")
            title = selectedArticleItem.title
            viewModel.getArticleDetails(selectedArticleItem.id)
            Glide.with(this).load(selectedArticleItem.avatar).into(article_detail_avatar)

            viewModel.articleDetail.observe(this, Observer {
                if(it != null && !it.text.isNullOrEmpty()){
                    article_full_desc.text = it.text
                }else{
                    showAlert("Couldn't find article details for ${selectedArticleItem.title}!")
                }
            })
            viewModel.errorMessage.observe(this, Observer {
                showErrorAlert(it)
            })
            viewModel.showProgress.observe(this, Observer {
                if (it)
                    article_detail_progress.visibility = View.VISIBLE
                else
                    article_detail_progress.visibility = View.GONE
            })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.article_detail_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.ae_menu -> {
                val intent = Intent(this, EditArticleDetailsActivity::class.java)
                intent.putExtra("Title", selectedArticleItem.title)
                intent.putExtra("Text", viewModel.articleDetail.value?.text)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun showAlert(message: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.Ok)) { dialog, id ->
                finish()
            }

        val alert = dialogBuilder.create()
        alert.setTitle(getString(R.string.Oops))
        alert.show()
    }
    private fun showErrorAlert(message: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.retry)) { dialog, id ->
                viewModel.getArticleDetails(selectedArticleItem.id)
            }
            .setNegativeButton(getString(R.string.menu_cancel)) { dialog, id ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.setTitle(getString(R.string.Oops))
        alert.show()
    }
}