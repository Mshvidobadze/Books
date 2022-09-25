package com.example.books.util

import com.example.books.data.domain.models.SearchListItemModel

data class BooksState(
    val books: List<SearchListItemModel> = emptyList()
)
