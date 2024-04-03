package com.example.kmanga.service.file_manager

import android.graphics.Bitmap
import okhttp3.MultipartBody
import java.io.File

interface FileManager {
    fun getInternalStorageDir(): File?
    fun getInternalImageFolder(): File?

    fun createFile(baseFolder: File, format: String, extension: String): File
    fun createFileWithName(baseFolder: File, name: String, format: String, extension: String): File
    fun createFolder(baseFolder: File, name: String): File
    fun writeBitmapToFile(bitmap: Bitmap, quality: Int, des: File)
    fun prepareFilePart(partName: String, file: File): MultipartBody.Part?

    fun unzipFile(file: File, des: File)
}