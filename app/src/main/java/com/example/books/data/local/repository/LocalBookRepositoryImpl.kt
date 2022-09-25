package com.example.books.data.local.repository

import com.example.books.data.domain.models.SearchListItemModel
import com.example.books.data.domain.repository.LocalBookRepository
import com.example.books.data.local.data_source.BooksDao
import kotlinx.coroutines.flow.Flow

class LocalBookRepositoryImpl(
    private val dao: BooksDao
): LocalBookRepository {

    override fun getBooks(): Flow<List<SearchListItemModel>> {
        return dao.getBooks()
    }

    override suspend fun getBookById(id: String): SearchListItemModel? {
        return dao.getBookById(id)
    }

    override suspend fun addBook(searchListItemModel: SearchListItemModel) {
        dao.addBook(searchListItemModel)
    }

    override suspend fun deleteBook(searchListItemModel: SearchListItemModel) {
        dao.deleteBook(searchListItemModel)
    }

}