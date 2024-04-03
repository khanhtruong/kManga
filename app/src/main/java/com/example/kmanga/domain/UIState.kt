package com.example.kmanga.domain

sealed class UIState<T> {
    data class Success<T>(val data: T) : UIState<T>()
    data class Error<T>(val error: ErrorType) : UIState<T>()
    class Loading<T> : UIState<T>()
}