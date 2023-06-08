package com.metalist.bookreader.data.response.base_response

import java.io.Serializable

class BaseResponse<T>(val data: T, val errorMessage: String, val statusCode: Int): Serializable