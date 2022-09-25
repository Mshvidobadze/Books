package com.example.books.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Pdf(
    @SerializedName("Chapter 2")
    val chapter2: String,
    @SerializedName("Chapter 5")
    val chapter5: String
)