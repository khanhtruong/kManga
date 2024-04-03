package com.example.kmanga.domain.model

import com.google.gson.annotations.SerializedName

data class BaseData<T>(
    @SerializedName("data")
    val data: T,
)