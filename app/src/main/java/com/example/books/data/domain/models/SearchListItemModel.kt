package com.example.books.data.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchListItemModel(
    val title: String,
    val imageUrl: String,
    @PrimaryKey val isbn13: String
)
