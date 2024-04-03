package com.example.kmanga.domain

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Failure<T>(val error: ErrorType) : Resource<T>()

    fun isSuccess() = this is Success

    fun isError() = this is Failure

    fun value(): T? {
        return when (this) {
            is Success -> this.data
            else -> null
        }
    }

    fun error(): ErrorType? {
        return when (this) {
            is Failure -> this.error
            else -> null
        }
    }
}

inline fun <T> Resource<T>.doOnSuccess(block: (T) -> Unit): Resource<T> {
    when (this) {
        is Resource.Success -> {
            block.invoke(this.data)
        }
        else -> {
            return this
        }
    }
    return this
}

inline fun <T> Resource<T>.doOnError(block: (ErrorType) -> Unit): Resource<T> {
    when (this) {
        is Resource.Failure -> {
            block.invoke(this.error)
        }
        else -> {
            return this
        }
    }
    return this
}