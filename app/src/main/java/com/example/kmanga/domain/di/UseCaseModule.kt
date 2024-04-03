package com.example.kmanga.domain.di

import com.example.kmanga.domain.usecase.top_manga.TopMangaUseCase
import com.example.kmanga.domain.usecase.top_manga.TopMangaUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun topMangaUseCaseProvider(topManga: TopMangaUseCaseImpl): TopMangaUseCase
}