package com.example.kmanga.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T> Flow<T>.skipOnNull(): Flow<T> = transform { value ->
    if (value == null) {
        return@transform
    }
    return@transform emit(value)
}