package com.example.kmanga.data.repo.top_manga

import com.example.kmanga.domain.dto.TopMangaDto
import kotlinx.coroutines.flow.Flow

interface TopMangaRepository {
    fun getTopMangas(category: String): Flow<List<TopMangaDto>>
}