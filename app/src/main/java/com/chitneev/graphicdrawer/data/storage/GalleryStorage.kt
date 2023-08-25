package com.chitneev.graphicdrawer.data.storage

import android.graphics.Bitmap

interface GalleryStorage {
    fun saveBitmap(bitmap: Bitmap)
}