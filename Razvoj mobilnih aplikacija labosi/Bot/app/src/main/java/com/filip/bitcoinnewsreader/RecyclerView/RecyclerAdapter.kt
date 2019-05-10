package com.filip.bitcoinnewsreader.RecyclerView

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.filip.bitcoinnewsreader.R
import com.filip.bitcoinnewsreader.context.MyApplication
import com.filip.bitcoinnewsreader.model.Articles
import com.filip.bitcoinnewsreader.model.News
import com.filip.bitcoinnewsreader.webview.WebViewActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*


class RecylcerAdapter(): RecyclerView.Adapter<NewsHolder>(){

    val news: MutableList<Articles> = mutableListOf()

    fun show(newsList: List<Articles>){
        news.clear()
        news.addAll(newsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val newsView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val newsHolder = NewsHolder(newsView)
        return newsHolder
    }

    override fun getItemCount(): Int {
        return news.size

    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news1 = news[position]
        holder.bind(news1)
    }

}

class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(news1: Articles) {
        itemView.title.text = news1.title//articles.title
        itemView.newsSource.text = news1.source.name
        itemView.newsAuthor.text = news1.author
        //itemView.newsDescription.text = news1.description//articles.description

        val pictureUrl = news1.urlToImage
        val singleNewsUrl = news1.url

        Picasso.get()
            .load(pictureUrl)
            .error(android.R.drawable.stat_notify_error)
            .into(itemView.newsImage)

        itemView.setOnClickListener{

            val navigate = Intent(MyApplication.ApplicationContext, WebViewActivity::class.java)
            navigate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            navigate.putExtra(WebViewActivity.URL, news1.url)

            MyApplication.ApplicationContext.startActivity(navigate)
        }
    }
}