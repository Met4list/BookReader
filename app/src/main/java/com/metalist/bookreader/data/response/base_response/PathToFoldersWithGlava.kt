package com.metalist.bookreader.data.response.base_response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

data class PathToFoldersWithGlava(
    @SerializedName("linkToFirstPicture")
    val linkToPictures: String,
    @SerializedName("numberOfGlava")
    val numberOfGlava: Int,
    @SerializedName("numberOfPictures")
    val numberOfPictures: Int
): Serializable