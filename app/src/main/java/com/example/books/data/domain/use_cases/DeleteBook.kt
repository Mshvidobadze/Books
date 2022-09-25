package com.example.books.data.domain.use_cases

import com.example.books.data.domain.models.SearchListItemModel
import com.example.books.data.domain.repository.LocalBookRepository

class DeleteBook(
    private val repository: LocalBookRepository
) {
    suspend operator fun invoke(searchListItemModel: SearchListItemModel){
        return repository.deleteBook(searchListItemModel)
    }
}