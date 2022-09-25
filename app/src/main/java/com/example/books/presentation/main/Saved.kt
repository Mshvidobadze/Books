package com.example.books.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.books.presentation.main.book_details_screen.BookDetailsScreen
import com.example.books.presentation.main.saved_list_screen.SavedListScreen

@Composable
fun SavedScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "saved_list_screen"
    ){
        composable(
            "saved_list_screen"
        ){
            SavedListScreen(navController = navController)
        }
        composable(
            "book_details_screen/{bookId}",
            arguments = listOf(
                navArgument("bookId"){
                    type = NavType.StringType
                }
            )
        ){
            val bookId = remember {
                it.arguments?.getString("bookId")
            }
            if (bookId != null) {
                BookDetailsScreen(bookId = bookId, navController = navController)
            }
        }
    }
}

@Composable
@Preview
fun SavedScreenPreview() {
    SavedScreen()
}