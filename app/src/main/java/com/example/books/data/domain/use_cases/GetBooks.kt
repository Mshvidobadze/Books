package com.example.books.data.domain.use_cases

import com.example.books.data.domain.models.SearchListItemModel
import com.example.books.data.domain.repository.LocalBookRepository
import kotlinx.coroutines.flow.Flow

class GetBooks(
    private val repository: LocalBookRepository
) {
    operator fun invoke(): Flow<List<SearchListItemModel>>{
        return repository.getBooks()
    }
}