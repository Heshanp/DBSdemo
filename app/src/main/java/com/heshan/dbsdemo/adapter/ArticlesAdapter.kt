package com.heshan.dbsdemo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.heshan.dbsdemo.R
import com.heshan.dbsdemo.network.model.Article
import com.heshan.dbsdemo.view.ArticleDetailsActivity
import kotlinx.android.synthetic.main.article_rv_item.view.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import javax.inject.Inject

class ArticlesAdapter @Inject constructor (private val context: Context) :
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    private var list: Article = Article()

    fun setArticles(list: Article){
        this.list = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.title
        holder.date.text = getDateFromEpoch(item.lastUpdate)
        holder.shortDesc.text = item.shortDescription

        if(item.avatar.isNotEmpty()){
            Glide.with(context).load(item.avatar).into(holder.avatar)
        }
        holder.root.setOnClickListener {
            val intent = Intent(context, ArticleDetailsActivity::class.java)
            intent.putExtra("Article", item)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.article_rv_item,
                parent,
                false
            )
        )
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title = v.article_title!!
        val date = v.article_date!!
        val shortDesc = v.article_short_desc!!
        val avatar = v.article_avatar!!
        val root = v.ai_root!!
    }

    private fun getDateFromEpoch(epochTime : Int) : String{
        val date = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochTime.toLong() * 1000), ZoneId.systemDefault())
            .toLocalDate()
        return date.toString()
    }

}