package com.example.books.di

import android.app.Application
import androidx.room.Room
import com.example.books.data.domain.repository.LocalBookRepository
import com.example.books.data.domain.use_cases.*
import com.example.books.data.local.data_source.BookDatabase
import com.example.books.data.local.repository.LocalBookRepositoryImpl
import com.example.books.data.remote.BooksApi
import com.example.books.data.remote.repository.BooksRepository
import com.example.books.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesBooksApi(): BooksApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(BooksApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBooksRepository(
        api: BooksApi
    ) = BooksRepository(api)


    @Singleton
    @Provides
    fun provideBookDatabase(app: Application): BookDatabase {
        return Room.databaseBuilder(
            app,
            BookDatabase::class.java,
            BookDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocalBookRepository(db: BookDatabase): LocalBookRepository {
        return LocalBookRepositoryImpl(db.booksDao)
    }

    @Singleton
    @Provides
    fun provideBookUseCases(repository: LocalBookRepository): BookUseCases {
        return BookUseCases(
            getBooks = GetBooks(repository),
            getBookById = GetBookById(repository),
            addBook = AddBook(repository),
            deleteBook = DeleteBook(repository)
        )
    }

}