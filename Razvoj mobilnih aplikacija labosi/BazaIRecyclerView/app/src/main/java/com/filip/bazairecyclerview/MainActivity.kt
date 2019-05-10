package com.filip.bazairecyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val bookAdapter by lazy { BookAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMe.setOnClickListener { addBook() }
        display()

        setUpUi()
    }

    private fun addBook() {
        val input = editText.text.toString()
        val book = Book("$input", "$input")
        BookDatabase.bookDao.insert(book)
        Log.d("dsds", book.toString())
        display()

    }

    private fun display() {
        bookAdapter.setData(BookDatabase.bookDao.getAll())
    }

    private fun setUpUi() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = bookAdapter
    }
}
