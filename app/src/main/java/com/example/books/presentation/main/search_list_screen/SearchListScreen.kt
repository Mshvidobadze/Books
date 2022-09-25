package com.example.books.presentation.main.search_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.books.presentation.common.BooksRow
import com.example.books.presentation.common.RetrySection

@Composable
fun SearchListScreen(
    navController: NavController,
    viewModel: SearchListViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(5.dp))

            SearchBar(
                hint = "Search",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ){
                viewModel.query = it
                viewModel.loadSearchResults(true)
            }

            Spacer(modifier = Modifier.height(10.dp))
            
            BooksList(navController = navController)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused
                }
        )
        if(isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun BooksList(
    navController: NavController,
    viewModel: SearchListViewModel = hiltViewModel()
){
    val booksList by remember { viewModel.searchList }
    val endReached by remember { viewModel.endReached }
    val error by remember { viewModel.error }
    val isLoading by remember { viewModel.isLoading }

    LazyColumn(contentPadding = PaddingValues(15.dp)){
        if(booksList.isNotEmpty()){
            val itemCount = if(booksList.size % 2 == 0){
                booksList.size / 2
            } else{
                booksList.size / 2 + 1
            }

            items(itemCount){
                if(it >= itemCount - 1 && !endReached && !isLoading){
                    viewModel.loadSearchResults(false)
                }
                BooksRow(
                    rowIndex = it,
                    entries = booksList,
                    navController = navController
                )
            }
        }
    }

    Box(
        contentAlignment = Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if(isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if(error.isNotEmpty()) {
            RetrySection(error = error) {
                viewModel.loadSearchResults(false)
            }
        }
    }
}