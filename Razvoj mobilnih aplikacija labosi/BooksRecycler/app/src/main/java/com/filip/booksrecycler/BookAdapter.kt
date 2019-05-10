package com.filip.booksrecycler

import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_book.view.*

class BookAdapter(val books : MutableList<Book>, val bookListener: BookInteractionListener) : RecyclerView.Adapter<BookHolder>() {
    var image = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val bookView = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        val bookHolder = BookHolder(bookView)
        return bookHolder
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val book = books[position]
        image = position
        holder.bind(book, bookListener)
    }
}

class BookHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val pics = listOf(R.drawable.time, R.drawable.distance, R.drawable.weightpicture)
    fun bind(book: Book, bookListener: BookInteractionListener){
        itemView.textView2.text = book.author
        itemView.textView3.text = book.title
        val pos: String = (BookRepository.get(book.id)).toString()
        itemView.imageView.setImageResource(pics[pos.toInt()])
        itemView.setOnClickListener { bookListener.onShowDetails(book.id) }
    }
}

