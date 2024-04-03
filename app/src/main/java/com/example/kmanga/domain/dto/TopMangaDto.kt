package com.example.kmanga.domain.dto

import com.google.gson.annotations.SerializedName

data class TopMangaDto(
    @SerializedName("title")
    var title: String,
    @SerializedName("picture_url")
    var pictureURL: String,
    @SerializedName("myanimelist_url")
    var url: String,
    @SerializedName("myanimelist_id")
    var id: String,
    @SerializedName("rank")
    var rank: String,
    @SerializedName("score")
    var score: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("aired_on")
    var airedOn: String,
    @SerializedName("members")
    var members: String,
)