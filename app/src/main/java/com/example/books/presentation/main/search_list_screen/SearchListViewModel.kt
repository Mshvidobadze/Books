package com.example.books.presentation.main.search_list_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.data.domain.models.SearchListItemModel
import com.example.books.data.remote.repository.BooksRepository
import com.example.books.util.Constants.PAGE_SIZE
import com.example.books.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(
    private val repository: BooksRepository
) : ViewModel() {

    private var currPage = 1

    var searchList = mutableStateOf<List<SearchListItemModel>>(listOf())
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    var query: String = ""

    fun loadSearchResults(isSearch: Boolean){
        viewModelScope.launch {
            isLoading.value = true
            if(isSearch){
                currPage = 1
            }
            val result = repository.getSearchList(query, currPage.toString())
            when(result){
                is Response.Success -> {
                    endReached.value = currPage * PAGE_SIZE >= result.data!!.total.toInt()
                    val bookEntries = result.data.books.map {  book ->
                        val title = if(book.title.length > 10) book.title.substring(0, 10) + "..." else book.title
                        SearchListItemModel(title, book.image, book.isbn13)
                    }
                    error.value = ""
                    isLoading.value = false

                    if(isSearch){
                        searchList.value = bookEntries
                    }else{
                        if(currPage == 1){
                            searchList.value = bookEntries
                        }else{
                            searchList.value += bookEntries
                        }
                        currPage++

                    }

                }
            }
        }
    }

}