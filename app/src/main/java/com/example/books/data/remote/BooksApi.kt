package com.example.books.data.remote

import com.example.books.data.remote.responses.BookResponse
import com.example.books.data.remote.responses.SearchListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApi {

    @GET("search/{query}/{page}")
    suspend fun getSearchList(
        @Path("query") query: String,
        @Path("page") page: String
    ): SearchListResponse

    @GET("books/{isbn13}")
    suspend fun getBook(
        @Path("isbn13") isbn13: String
    ): BookResponse

}