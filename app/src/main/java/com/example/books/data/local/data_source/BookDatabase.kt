package com.example.books.data.local.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.books.data.domain.models.SearchListItemModel

@Database(
    entities = [SearchListItemModel::class],
    version = 1
)
abstract class BookDatabase: RoomDatabase() {

    abstract val booksDao: BooksDao

    companion object{
        const val DATABASE_NAME = "books_db"
    }
}