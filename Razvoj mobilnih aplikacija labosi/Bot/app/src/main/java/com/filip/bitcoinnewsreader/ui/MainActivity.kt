package com.filip.bitcoinnewsreader.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.filip.bitcoinnewsreader.R
import com.filip.bitcoinnewsreader.RecyclerView.RecylcerAdapter
import com.filip.bitcoinnewsreader.model.Articles
import com.filip.bitcoinnewsreader.model.News
import com.filip.bitcoinnewsreader.model.Source
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.filip.bitcoinnewsreader.retrofit.Networking
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity(), Callback<News> {

    private val newsAdapter by lazy { RecylcerAdapter() }
    private val listOfSoureces = mutableListOf<String>()
    private val hlist = hashSetOf<String>()
    private val listOfNews = mutableListOf<Articles>()
    private var sourceFromSpinner: String = "-"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {
            get()
        }

        setUpUi()

        doAsync {
            refresh.setOnClickListener { get() }
        }
    }

    private fun setUpUi() {
        googleNewsView.layoutManager = LinearLayoutManager(this)
        googleNewsView.itemAnimator = DefaultItemAnimator()
        googleNewsView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        googleNewsView.adapter = newsAdapter

    }

    private fun get() {
        Networking.showBitcoinNews.showNews("bitcoin", "d98adcf17a8b43779fc054404455c353")
            .enqueue(this)

        listOfNews.clear()
        hlist.clear()
        listOfSoureces.clear()
    }

    override fun onFailure(call: Call<News>, t: Throwable) {
        Log.d("TAG", t.message)
    }

    override fun onResponse(call: Call<News>, response: Response<News>) {
        Log.d("TAG", "Fetch json")
        val results = response.body()

        val articlesRes: List<Articles> = results!!.articles

        newsAdapter.show(articlesRes)

        spinner.adapter = null

        setSpinner(articlesRes)

        showNews(articlesRes)
    }

    private fun setSpinner(sourcesOfBitcoinNews: List<Articles>) {
        listOfSoureces.add("-")

        for (item in sourcesOfBitcoinNews) {
            hlist.add(item.source.name)
        }

        listOfSoureces.addAll(hlist)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOfSoureces
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sourceFromSpinner = parent?.getItemAtPosition(position).toString()
            }
        }
    }

    private fun showNews(articlesRes: List<Articles>) {

        if (sourceFromSpinner == "-") {
            newsAdapter.show(articlesRes)
        } else {
            for (item in articlesRes) {
                if (sourceFromSpinner == item.source.name) {
                    var news = Articles(
                        Source(item.source.id, item.source.name),
                        item.author,
                        item.title,
                        item.description,
                        item.url,
                        item.urlToImage,
                        item.publishedAt,
                        item.content
                    )

                    listOfNews.add(news)
                }
            }
            newsAdapter.show(listOfNews)
        }
    }
}
