package com.example.books.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.books.data.domain.models.SearchListItemModel

@Composable
fun BooksRow(
    rowIndex: Int,
    entries: List<SearchListItemModel>,
    navController: NavController
){
    Column {
        Row {
            BookEntry(
                entry = entries[rowIndex * 2],
                navController = navController,
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(
                modifier = Modifier
                    .width(15.dp)
            )
            if(entries.size >= rowIndex * 2 + 2){
                BookEntry(
                    entry = entries[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier
                        .weight(1f)
                )
            } else {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
    }
}