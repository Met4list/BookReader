package com.metalist.bookreader.data.response.base_response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
): Serializable