package com.example.kmanga.data.network

import com.example.kmanga.domain.dto.TopMangaDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MyAnimeAPI {
    @GET("/manga/top/{category}")
    suspend fun topMangas(@Path("category") category: String): Response<List<TopMangaDto>>
}