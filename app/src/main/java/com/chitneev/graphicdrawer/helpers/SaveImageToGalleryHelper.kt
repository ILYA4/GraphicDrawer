package com.chitneev.graphicdrawer.helpers

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class SaveImageToGalleryHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    operator fun invoke(
        bitmap: Bitmap,
        fileName: String,
    ) : SaveGraphResult {
        val contentResolver: ContentResolver = context.contentResolver
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")

        val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)

        val uri = contentResolver.insert(collection, values)

        uri ?: return SaveGraphResult.Error("ContentResolver returned null")

        return try {
            val outputStream = contentResolver.openOutputStream(uri)

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream?.close()
            SaveGraphResult.Success(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
            SaveGraphResult.Error(e.message.toString())
        }
    }
}

sealed interface SaveGraphResult {
    class Success(val fileName: String) : SaveGraphResult
    class Error(val errorMessage: String) : SaveGraphResult
}
