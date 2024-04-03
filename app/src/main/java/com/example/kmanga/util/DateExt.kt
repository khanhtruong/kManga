package com.example.kmanga.util

import java.text.SimpleDateFormat
import java.util.*

// toStringBy format with Location
// Default "dd/MM/yyyy HH:mm" and default locale
fun Date.toStringBy(format: String = "dd/MM/yyyy HH:mm", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}