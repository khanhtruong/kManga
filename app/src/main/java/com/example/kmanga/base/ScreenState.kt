package com.example.kmanga.base

import com.example.kmanga.domain.ErrorType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class ScreenState {
    private val _errorsFlow = MutableSharedFlow<ErrorType>()
    private val _loadingFlow = MutableSharedFlow<Boolean>()
    val errorsFlow = _errorsFlow.asSharedFlow()
    val loadingFlow = _loadingFlow.asSharedFlow()

    suspend fun emitError(error: ErrorType) {
        _errorsFlow.emit(error)
    }

    suspend fun emitLoading(showLoading: Boolean) {
        _loadingFlow.emit(showLoading)
    }
}