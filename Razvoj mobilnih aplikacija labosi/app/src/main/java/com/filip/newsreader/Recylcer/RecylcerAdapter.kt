package com.filip.newsreader.Recylcer

import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.filip.newsreader.DatabaseTable.Articles
import com.filip.newsreader.Cont.MyApplication

//import com.filip.newsreader.DatabaseTable.News
import com.filip.newsreader.R
import com.filip.newsreader.Ui.SingleActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.news_look.view.*


class RecylcerAdapter(): RecyclerView.Adapter<NewsHolder>(){

    val news: MutableList<Articles> = mutableListOf() //lista za prikazivanje određenog sadržaja iz klase Articles

    fun show(newsList: List<Articles>){ //metoda koja nam služi za prikazivanje određenog sadržaja na recyclerView-u
        news.clear()
        news.addAll(newsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {  //služi za stavranje ViewHolder odnosno u našem slučaju NewsHolder sa svime što sadrži layout news_look
        val newsView = LayoutInflater.from(parent.context).inflate(R.layout.news_look, parent, false)  //View objašnjen dolje u NewsHolder klasi
        val newsHolder = NewsHolder(newsView)  //objekt klase NewsHolder kojem se prosljeđuje paramentar newsView
        return newsHolder
    }

    override fun getItemCount(): Int {  //vraća broj elemenata news.size (toliko će "kućica"se prikazati na recyclerView-u)
        return news.size   //news je lista koju smo definirali iznad funkcije show

    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news1 = news[position]
        holder.bind(news1)
    }
}

class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {  //klasa koja u konsturktoru ima itemView tipa View (View je osnovan klasa pretstavlja UI kontrolu iz koje se izvode ostale kontrole kao što su TextView, EditText...
    fun bind(news1: Articles) {
        itemView.newsTitle.text = news1.title     //prikaz naslova
        itemView.description.text = news1.description  //prikaz opisa

        val pictureUrl = news1.urlToImage
        val singleNewsUrl = news1.url

        Picasso.get()
                .load(pictureUrl)         //Picasso nam služi za pristup slici preko url-a te za njeno prikazivanje
                .error(android.R.drawable.stat_notify_error)
                .into(itemView.newsPicture)      //prikaz slike na ImageView-u (newsPicture)

        itemView.setOnClickListener{       // listener za otvaranje single news-a u novom activity-u

            val navigate = Intent(MyApplication.ApplicationContext, SingleActivity::class.java)   //Intent za otvaranje novig activity-a
            navigate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            navigate.putExtra(SingleActivity.TITLE, news1.title)   //Pomoću putExtra šaljemo SingleAcitvity-u naslov, url slike te url
                    .putExtra(SingleActivity.PICTURE, pictureUrl)  //na kojem se nalazi single vijest. Trpamo ih u parametre companion object-a koji se nalazi u SingleActivity-u kako bi im tamo mogli i pristupiti
                    .putExtra(SingleActivity.TEXT, singleNewsUrl)

            MyApplication.ApplicationContext.startActivity(navigate)   //pokretanje novog activity-a
        }
    }
}
