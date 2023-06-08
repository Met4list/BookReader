package com.metalist.bookreader.data.repositories

import com.metalist.bookreader.data.response.base_response.BaseResponse
import com.metalist.bookreader.data.response.base_response.BookResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface BooksApi {

    @GET("Mangas")
    suspend fun fetchBooks(): BaseResponse<List<BookResponse>>

    @GET("Mangas/{id}")
    suspend fun fetchBookDetails(@Path("id") id: Int): BaseResponse<BookResponse>

    class Base @Inject constructor(private val retrofit: Retrofit) : BooksApi {
        private val api = retrofit.create(BooksApi::class.java)

        override suspend fun fetchBooks(): BaseResponse<List<BookResponse>> = api.fetchBooks()

        override suspend fun fetchBookDetails(id: Int): BaseResponse<BookResponse> {
            return api.fetchBookDetails(id)
        }

    }
}