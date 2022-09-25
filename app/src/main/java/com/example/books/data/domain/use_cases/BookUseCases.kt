package com.example.books.data.domain.use_cases

data class BookUseCases (
    val getBooks: GetBooks,
    val getBookById: GetBookById,
    val addBook: AddBook,
    val deleteBook: DeleteBook
)