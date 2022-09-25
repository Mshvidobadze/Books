package com.example.books.data.remote.responses

import com.google.gson.annotations.SerializedName

data class SearchListResponse(
    @SerializedName("books")
    val books: List<Book>,
    @SerializedName("error")
    val error: String,
    @SerializedName("page")
    val page: String,
    @SerializedName("total")
    val total: String
)