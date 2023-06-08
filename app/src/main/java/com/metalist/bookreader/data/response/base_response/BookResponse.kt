package com.metalist.bookreader.data.response.base_response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class BookResponse : Serializable {
    @SerializedName("ageRating")
    val ageRating: String = ""
    @SerializedName("author")
    val author: String = ""
    @SerializedName("description")
    val description: String = ""
    @SerializedName("genres")
    val genres: List<Genre> = listOf()
    @SerializedName("id")
    val id: Int = 0
    @SerializedName("name")
    val name: String = ""
    @SerializedName("numbetOfChapters")
    val numbetOfChapters: Int = 0
    @SerializedName("pathToFoldersWithGlava")
    val pathToFoldersWithGlava: List<PathToFoldersWithGlava> = listOf()
    @SerializedName("pathToTitlePicture")
    val pathToTitlePicture: String = ""
    @SerializedName("releaseYear")
    val releaseYear: Int = 0
}