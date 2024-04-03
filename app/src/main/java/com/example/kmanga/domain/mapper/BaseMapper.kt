package com.example.kmanga.domain.mapper

interface BaseMapper<M, D> {
    fun toDomain(dto: D): M
    fun toDto(model: M): D
}