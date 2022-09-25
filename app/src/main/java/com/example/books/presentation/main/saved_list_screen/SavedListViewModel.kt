package com.example.books.presentation.main.saved_list_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.data.domain.use_cases.BookUseCases
import com.example.books.util.BooksState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SavedListViewModel @Inject constructor(
    private val booksUseCases: BookUseCases
) : ViewModel() {

    private val _state = mutableStateOf(BooksState())
    val state: State<BooksState> = _state

    var error = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        getBooks()
    }

    private fun getBooks() {
        booksUseCases.getBooks()
            .onEach { books ->
                _state.value = state.value.copy(
                    books = books,
                )
            }
            .launchIn(viewModelScope)
    }
}