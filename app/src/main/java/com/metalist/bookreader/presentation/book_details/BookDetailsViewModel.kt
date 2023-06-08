package com.metalist.bookreader.presentation.book_details

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
class BookDetailsViewModel @Inject constructor(private val booksApi: BooksApi): ViewModel() {

    private val _bookResponse = MutableLiveData<BookResponse>()
    val bookResponse: LiveData<BookResponse> = _bookResponse

    fun fetchBookDetails(id: Int){
        try {
            viewModelScope.launch {
                _bookResponse.postValue(booksApi.fetchBookDetails(id).data)
            }
        } catch (e: Throwable){
            Timber.e(e)
        }
    }
}