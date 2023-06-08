package com.metalist.bookreader.utils

import android.view.View

fun View.updateVisibility(visible: Boolean?) {
    visible?.let { isVisible -> visibility = if (isVisible) View.VISIBLE else View.GONE }
}