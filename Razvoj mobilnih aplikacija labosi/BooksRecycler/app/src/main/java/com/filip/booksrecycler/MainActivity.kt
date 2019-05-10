package com.filip.booksrecycler

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUi()
    }

    private fun setUpUi() {
        bookDisplay.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        bookDisplay.itemAnimator = DefaultItemAnimator()
        bookDisplay.addItemDecoration(DividerItemDecoration(this,RecyclerView.VERTICAL))
        displayData()
    }
    private fun displayData() {
        val detailsIntent: Intent = Intent(this, DetailsActivity::class.java)
        val bookListener = object: BookInteractionListener {
            override fun onShowDetails(id: Int) {
                when(id){
                    1-> startActivity(detailsIntent)
                }
                val book = BookRepository.get(id)
                Toast.makeText(applicationContext, book.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        bookDisplay.adapter = BookAdapter(BookRepository.books, bookListener)
    }

}

