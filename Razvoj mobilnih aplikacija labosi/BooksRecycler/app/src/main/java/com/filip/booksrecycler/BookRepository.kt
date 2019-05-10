package com.filip.booksrecycler

object BookRepository {
    val pic1: Int  =0

    val books : MutableList<Book> = createBooks()

    private fun createBooks(): MutableList<Book> {
        return mutableListOf(
                Book(1, "Filip", "Lol"),
                Book(2, "Filip1", "Lol2"),
                Book(3, "Filip2", "Lol3")
        )
    }

    fun get(id: Int): Book? = books.find { book -> book.id == id }

}