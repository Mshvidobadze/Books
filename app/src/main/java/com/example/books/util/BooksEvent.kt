package com.example.books.util

import com.example.books.data.remote.responses.BookResponse

sealed class BooksEvent {
    data class Add(val book: BookResponse): BooksEvent()
    data class Delete(val book: BookResponse): BooksEvent()
}
