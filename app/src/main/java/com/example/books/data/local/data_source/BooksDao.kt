package com.example.books.data.local.data_source

import androidx.room.*
import com.example.books.data.domain.models.SearchListItemModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    @Query("SELECT * FROM searchlistitemmodel")
    fun getBooks(): Flow<List<SearchListItemModel>>

    @Query("SELECT * FROM searchlistitemmodel WHERE isbn13 = :id")
    suspend fun getBookById(id: String): SearchListItemModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBook(searchListItemModel: SearchListItemModel)

    @Delete
    suspend fun deleteBook(searchListItemModel: SearchListItemModel)
}