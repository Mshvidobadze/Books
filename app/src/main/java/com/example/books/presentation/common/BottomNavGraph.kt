package com.example.books.presentation.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.books.presentation.main.SavedScreen
import com.example.books.presentation.main.SearchScreen
import com.example.books.util.BottomBarScreen

@Composable
fun BottomNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = BottomBarScreen.Search.route
    ){
        composable(route = BottomBarScreen.Search.route){
            SearchScreen()
        }
        composable(route = BottomBarScreen.Saved.route){
            SavedScreen()
        }
    }
}