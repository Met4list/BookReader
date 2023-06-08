package com.metalist.bookreader.data.repositories

import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("Auth/sign-in")
    suspend fun signIn(@Body requestBody: RequestBody)

    @POST("Auth/sign-up")
    suspend fun signUp(@Body requestBody: RequestBody)

    @GET("Auth/update-refresh-token")
    suspend fun updateRefreshToken()

    @GET("Auth/verify-email")
    suspend fun verifyEmail()

    @POST("Auth/resend-verify-email-letter")
    suspend fun resendVerifyEmailLetter()

    @POST("Auth/send-reset-password-token")
    suspend fun sendResetPasswordToken()

    @POST("Auth/reset-password")
    suspend fun resetPassword()

    class Base(retrofit: Retrofit) : AuthApi{
        private val api = retrofit.create(AuthApi::class.java)

        override suspend fun signIn(requestBody: RequestBody) {
            return api.signIn(requestBody)
        }

        override suspend fun signUp(requestBody: RequestBody) {
            return api.signUp(requestBody)
        }

        override suspend fun updateRefreshToken() {
            return api.updateRefreshToken()
        }

        override suspend fun verifyEmail() {
            api.verifyEmail()
        }

        override suspend fun resendVerifyEmailLetter() {
            return api.resendVerifyEmailLetter()
        }

        override suspend fun sendResetPasswordToken() {
            return api.sendResetPasswordToken()
        }

        override suspend fun resetPassword() {
            return api.resetPassword()
        }
    }
}