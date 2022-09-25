package com.example.books.presentation.main.saved_list_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.books.presentation.common.BooksRow
import com.example.books.presentation.common.RetrySection

@Composable
fun SavedListScreen(
    navController: NavController
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
    ) {
        Column {
            BooksList(navController = navController)
        }
    }
}

@Composable
fun BooksList(
    navController: NavController,
    viewModel: SavedListViewModel = hiltViewModel()
){
    val booksList by remember { viewModel.state }
    val error by remember { viewModel.error }
    val isLoading by remember { viewModel.isLoading }

    LazyColumn(contentPadding = PaddingValues(15.dp)){
        if(booksList.books.isNotEmpty()){
            val itemCount = if(booksList.books.size % 2 == 0){
                booksList.books.size / 2
            } else{
                booksList.books.size / 2 + 1
            }

            items(itemCount){
                BooksRow(
                    rowIndex = it,
                    entries = booksList.books,
                    navController = navController
                )
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if(isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if(error.isNotEmpty()) {
            RetrySection(error = error) {

            }
        }
    }

}