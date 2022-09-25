package com.example.books.data.remote.repository

import com.example.books.data.remote.responses.BookResponse
import com.example.books.data.remote.responses.SearchListResponse
import com.example.books.data.remote.BooksApi
import com.example.books.util.Response
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class BooksRepository @Inject constructor(
    private val api: BooksApi
) {

    suspend fun getSearchList(query: String, page: String): Response<SearchListResponse> {
        val response = try {
            api.getSearchList(query, page)
        } catch (e: Exception) {
            return Response.Error("Error occured")
        }
        return Response.Success(response)
    }

    suspend fun getBook(isbn13: String): Response<BookResponse> {
        val response = try {
            api.getBook(isbn13)
        } catch (e: Exception) {
            return Response.Error("Error occured")
        }
        return Response.Success(response)
    }

}