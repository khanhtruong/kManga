package com.example.kmanga.domain.mapper.top_manga

import com.example.kmanga.domain.dto.TopMangaDto
import com.example.kmanga.domain.mapper.BaseMapper
import com.example.kmanga.domain.model.TopManga

class TopMangaMapper : BaseMapper<TopManga, TopMangaDto> {
    override fun toDomain(dto: TopMangaDto): TopManga {
        return TopManga(
            title = dto.title,
            pictureURL = dto.pictureURL,
            url = dto.url,
            id = dto.id,
            rank = dto.rank,
            score = dto.score,
            type = dto.type,
            airedOn = dto.airedOn,
            members = dto.members,
        )
    }

    override fun toDto(model: TopManga): TopMangaDto {
        return TopMangaDto(
            title = model.title,
            pictureURL = model.pictureURL,
            url = model.url,
            id = model.id,
            rank = model.rank,
            score = model.score,
            type = model.type,
            airedOn = model.airedOn,
            members = model.members,
        )
    }

}