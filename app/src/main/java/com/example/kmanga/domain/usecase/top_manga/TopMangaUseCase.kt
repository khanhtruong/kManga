package com.example.kmanga.domain.usecase.top_manga

import com.example.kmanga.domain.model.TopManga
import kotlinx.coroutines.flow.Flow

interface TopMangaUseCase {
    fun getTopManga(category: String): Flow<List<TopManga>>
}