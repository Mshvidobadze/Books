package com.example.books.presentation.main.book_details_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.data.domain.models.SearchListItemModel
import com.example.books.data.domain.use_cases.BookUseCases
import com.example.books.data.remote.responses.BookResponse
import com.example.books.data.remote.repository.BooksRepository
import com.example.books.util.BooksEvent
import com.example.books.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val repository: BooksRepository,
    private val booksUseCases: BookUseCases
) : ViewModel() {

    var isSaved = mutableStateOf(false)

    suspend fun getBookDetails(isbn13: String): Response<BookResponse> {
        return repository.getBook(isbn13)
    }

    fun getBookById(isbn13: String){
        viewModelScope.launch {
            booksUseCases.getBookById(isbn13)?.also { note ->
                isSaved.value = true
            }
        }
    }

    fun onEvent(event: BooksEvent){
        when(event) {
            is BooksEvent.Add -> {
                viewModelScope.launch {
                    val item = SearchListItemModel(
                        title = event.book.title,
                        imageUrl = event.book.image,
                        isbn13 = event.book.isbn13
                    )
                    booksUseCases.addBook(item)
                }
            }
            is BooksEvent.Delete -> {
                viewModelScope.launch {
                    val item = SearchListItemModel(
                        title = event.book.title,
                        imageUrl = event.book.image,
                        isbn13 = event.book.isbn13
                    )
                    booksUseCases.deleteBook(item)
                }
            }
        }
    }
}