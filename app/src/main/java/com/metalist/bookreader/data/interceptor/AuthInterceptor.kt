package com.metalist.bookreader.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        chain.request().newBuilder().apply {

        }
        return chain.proceed(request)
    }
}