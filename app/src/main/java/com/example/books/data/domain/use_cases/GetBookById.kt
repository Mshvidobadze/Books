package com.example.books.data.domain.use_cases

import com.example.books.data.domain.models.SearchListItemModel
import com.example.books.data.domain.repository.LocalBookRepository

class GetBookById(
    private val repository: LocalBookRepository
) {
    suspend operator fun invoke(bookId: String): SearchListItemModel? {
        return repository.getBookById(bookId)
    }
}