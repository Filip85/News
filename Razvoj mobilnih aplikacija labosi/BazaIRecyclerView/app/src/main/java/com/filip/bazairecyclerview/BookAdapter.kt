package com.filip.bazairecyclerview

import android.support.v7.widget.DecorContentParent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.container.view.*

class BookAdapter(): RecyclerView.Adapter<BookHolder>() {

    private val books = mutableListOf<Book>()

    fun setData(newBooks: List<Book>){
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val bookView = LayoutInflater.from(parent.context).inflate(R.layout.container, parent, false)
        val bookHolder = BookHolder(bookView)
        return bookHolder
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val b = books[position]
        holder.bind(b)
    }

}

class BookHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(b: Book) {
        itemView.authorOfBook.text = b.author
        itemView.titleOfBook.text = b.title

    }

}
