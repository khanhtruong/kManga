package com.example.kmanga.domain.usecase.top_manga

import com.example.kmanga.data.repo.top_manga.TopMangaRepository
import com.example.kmanga.domain.mapper.top_manga.TopMangaMapper
import com.example.kmanga.domain.model.TopManga
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopMangaUseCaseImpl @Inject constructor(
    private val topMangaRepository: TopMangaRepository
) : TopMangaUseCase {
    private val mapper = TopMangaMapper()

    override fun getTopManga(category: String): Flow<List<TopManga>> {
        // TODO: Can provide caching on Local DB if necessary
        return topMangaRepository.getTopMangas(category)
            .map {
                it.map(mapper::toDomain)
            }
    }
}