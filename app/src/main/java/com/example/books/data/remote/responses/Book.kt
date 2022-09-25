package com.example.books.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("image")
    val image: String,
    @SerializedName("isbn13")
    val isbn13: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)