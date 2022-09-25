package com.example.books.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Search: BottomBarScreen(
        "search",
        "Search",
        Icons.Default.Search
    )

    object Saved: BottomBarScreen(
        "saved",
        "Saved",
        Icons.Default.Save
    )
}
