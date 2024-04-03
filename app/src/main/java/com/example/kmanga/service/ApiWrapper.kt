package com.example.kmanga.service

import com.example.kmanga.domain.ErrorType
import com.example.kmanga.domain.Resource
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Handle only response for retrofit client
 */
suspend fun <T> safeCallApi(
    api: suspend () -> Response<T>
): Resource<T> {
    return try {
        val response = api.invoke()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            Resource.Success(body)
        } else {
            throw HttpException(response)
        }
    } catch (throwable: Throwable) {
        val errorType = when (throwable) {
            is HttpException -> {
                val response = throwable.response()
                response?.errorBody()?.string()
                    ?.let {
                        Gson().fromJson(it, BaseErrorDto::class.java)
                    }?.let {
                        ErrorType.ResponseError(
                            it.code,
                            it.detail ?: throwable.localizedMessage
                        )
                    } ?: ErrorType.NetworkError(
                    httpCode = response?.code(),
                    message = response?.message() ?: throwable.localizedMessage
                )
            }
            is IOException -> {
                ErrorType.NetworkError(
                    message = throwable.localizedMessage ?: "Unknow error"
                )
            }
            else -> {
                ErrorType.ResponseError(
                    message = throwable.localizedMessage ?: "Unknow error"
                )
            }
        }
        Resource.Failure(errorType)
    }
}


data class BaseErrorDto(
    @SerializedName("detail")
    val detail: String? = "",
    @SerializedName("code")
    val code: String? = "",
    @SerializedName("message")
    val message: String? = ""
) : java.io.Serializable