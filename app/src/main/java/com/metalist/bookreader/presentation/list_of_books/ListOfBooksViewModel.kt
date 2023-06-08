package com.metalist.bookreader.presentation.list_of_books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metalist.bookreader.data.repositories.BooksApi
import com.metalist.bookreader.data.response.base_response.BookResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListOfBooksViewModel @Inject constructor(
    private val booksApi: BooksApi
) : ViewModel() {

    private val _listOfBooks = MutableLiveData<List<BookResponse>>()
    val listOfBooks: LiveData<List<BookResponse>> = _listOfBooks

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        try {
            viewModelScope.launch {
                _listOfBooks.postValue(booksApi.fetchBooks().data)
            }
        } catch (e: Throwable) {
            Timber.e(e)
        }
    }
}