package com.example.kmanga.data.network.parser

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DatetimeParser : JsonDeserializer<Date?> {
    private val format1: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
    private val format2: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): Date? {
        try {
            val j = json.asJsonPrimitive.asString
            return parseDate(j)
        } catch (e: ParseException) {
            throw JsonParseException(e.message, e)
        }
    }

    private fun parseDate(dateString: String): Date? {
        format1.timeZone = TimeZone.getTimeZone("UTC")
        format2.timeZone = TimeZone.getTimeZone("UTC")

        return if (dateString.trim().isNotEmpty()) {
            try {
                format1.parse(dateString)
            } catch (pe: ParseException) {
                format2.parse(dateString)
            }
        } else {
            null
        }
    }
}