package com.example.kmanga.domain.model

import com.google.gson.annotations.SerializedName

data class BaseListData<T>(
    @SerializedName("data")
    val data: List<T>
)