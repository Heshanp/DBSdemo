package com.heshan.dbsdemo.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.heshan.dbsdemo.R
import com.heshan.dbsdemo.network.model.ArticleDetail
import com.heshan.dbsdemo.network.model.ArticleItem
import kotlinx.android.synthetic.main.activity_edit_article_details.*

class EditArticleDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_article_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra("Title")) {
            title = "Edit ${intent.getStringExtra("Title")}"
        }
        if (intent.hasExtra("Text")) {
            article_edit_text.setText(intent.getStringExtra("Text"))
        }else{
            Toast.makeText(application,"No text to edit!",Toast.LENGTH_SHORT).show()
        }

        save_button.setOnClickListener {
            Toast.makeText(application,"Changes saved.",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.article_edit_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.ae_menu -> {
                showConfirmation()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showConfirmation() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Are you sure you want to cancel editing?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                finish()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Cancel Editing?")
        alert.show()
    }
}