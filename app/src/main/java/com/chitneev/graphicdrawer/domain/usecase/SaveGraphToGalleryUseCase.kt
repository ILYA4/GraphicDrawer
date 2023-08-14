package com.chitneev.graphicdrawer.domain.usecase

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

class SaveGraphToGalleryUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {

    operator fun invoke(bitmap: Bitmap) {
        val contentResolver: ContentResolver = context.contentResolver
        val values = ContentValues()
        val localDateTime = LocalDateTime.now()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, "graph$localDateTime.png")
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")

        val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)

        val item = contentResolver.insert(collection, values)

        try {
            val outputStream = contentResolver.openOutputStream(item!!)

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}