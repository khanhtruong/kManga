package com.example.kmanga.data.repo.top_manga

import com.example.kmanga.data.network.MyAnimeAPI
import com.example.kmanga.domain.dto.TopMangaDto
import com.example.kmanga.domain.mapper.top_manga.TopMangaMapper
import com.example.kmanga.domain.model.TopManga
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TopMangaRepositoryImpl @Inject constructor(
    private val myAnimeAPI: MyAnimeAPI,
) : TopMangaRepository {
    override fun getTopMangas(category: String): Flow<List<TopMangaDto>> {
        return flow {
            val response = myAnimeAPI.topMangas(category)
            emit(response.body() ?: emptyList())
        }
    }
}