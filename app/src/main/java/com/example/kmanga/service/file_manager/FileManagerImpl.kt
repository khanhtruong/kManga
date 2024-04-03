package com.example.kmanga.service.file_manager

import android.graphics.Bitmap
import android.util.Log
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import javax.inject.Inject

class FileManagerImpl @Inject constructor(
    activity: FragmentActivity
) : FileManager {
    private val activity: WeakReference<FragmentActivity> = WeakReference(activity)

    override fun getInternalStorageDir(): File? {
        return activity.get()?.filesDir
    }

    override fun getInternalImageFolder(): File? {
        val root = getInternalStorageDir() ?: return null
        val imageFolder = File(root, IMAGE_FOLDER_PATH)

        if (!imageFolder.exists()) {
            if (!imageFolder.mkdirs()) {
                return null
            }
        }
        return imageFolder
    }

    override fun createFile(baseFolder: File, format: String, extension: String): File {
        val name =
            SimpleDateFormat(format, Locale.US).format(System.currentTimeMillis()) + extension
        return File(baseFolder, name)
    }

    override fun createFileWithName(
        baseFolder: File,
        name: String,
        format: String,
        extension: String
    ): File {
        if (name.isEmpty()) {
            val newName =
                SimpleDateFormat(format, Locale.US).format(System.currentTimeMillis()) + extension
            return File(baseFolder, newName)
        }

        return File(baseFolder, name)
    }

    override fun createFolder(baseFolder: File, name: String): File {
        val folder = File(baseFolder, name)
        folder.mkdirs()
        return folder
    }

    override fun writeBitmapToFile(bitmap: Bitmap, quality: Int, des: File) {
        des.outputStream().use { fOut ->
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, fOut)
            fOut.flush()
            fOut.close()
        }
    }

    override fun prepareFilePart(
        partName: String,
        file: File
    ): MultipartBody.Part? {
        val context = activity.get()?.applicationContext ?: return null

        val provider = context.packageName + ".provider"
        val uri = FileProvider.getUriForFile(
            context,
            provider,
            file
        )

        context.contentResolver.getType(uri)?.toMediaTypeOrNull()?.let { type ->
            file.asRequestBody(type).let {
                return MultipartBody.Part.createFormData(partName, file.name, it)
            }
        }

        return null
    }

    override fun unzipFile(file: File, des: File) {
        unzip(file, des)
    }

    private fun unzip(file: File, des: File) {
        //create target location folder if not exist
        try {
            val fin = FileInputStream(file)
            val zin = ZipInputStream(fin)
            var ze: ZipEntry? = null
            while (zin.nextEntry.also { ze = it } != null) {

                //create dir if required while unzipping
                if (ze!!.isDirectory) {
                    // Ignore for now
                    Log.d("Debuggg: FileManagerImpl", "Unzip file contains folder, skip for now")
                } else {
                    val name = ze!!.name
                    val newName = name.substring(name.lastIndexOf('/') + 1)
                    val desImage = File(des, newName)
                    val fout = FileOutputStream(desImage)
                    var c: Int = zin.read()
                    while (c != -1) {
                        fout.write(c)
                        c = zin.read()
                    }
                    zin.closeEntry()
                    fout.close()
                }
            }
            zin.close()
        } catch (e: Exception) {
            Log.e("Debuggg: FileManagerImpl", "Unzip file failed, error: ${e.message}")
        }
    }

    companion object {
        const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        const val PHOTO_EXTENSION = ".jpg"

        const val IMAGE_FOLDER_PATH = "images"
    }
}