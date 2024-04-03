package com.example.kmanga.util

import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.kmanga.R

fun TextView.highLightingText(highLight: String) {
    val message = this.text.toString()
    val color = ResourcesCompat.getColor(this.resources, R.color.highlighted_text, null)

    this.setText(
        highlightKeywords(color, message, listOf(highLight)),
        TextView.BufferType.SPANNABLE
    )
}

fun highlightKeywords(
    highlightColor: Int,
    message: String,
    keywords: List<String>,
): SpannableString {
    val spannableString = SpannableString(message)
    keywords.forEach { keyword ->
        if (keyword.isNotBlank()) {
            var startIndex = message.lowercase().indexOf(keyword.lowercase())

            while (startIndex >= 0) {
                spannableString.setSpan(
                    BackgroundColorSpan(highlightColor),
                    startIndex,
                    startIndex + keyword.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                startIndex = message.lowercase().indexOf(keyword.lowercase(), startIndex + keyword.length)
            }
        }
    }
    return spannableString
}