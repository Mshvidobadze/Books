package com.example.books.data.domain.repository

import com.example.books.data.domain.models.SearchListItemModel
import kotlinx.coroutines.flow.Flow

interface LocalBookRepository {

    fun getBooks(): Flow<List<SearchListItemModel>>

    suspend fun getBookById(id: String): SearchListItemModel?

    suspend fun addBook(searchListItemModel: SearchListItemModel)

    suspend fun deleteBook(searchListItemModel: SearchListItemModel)

}