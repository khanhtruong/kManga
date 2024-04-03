package com.example.kmanga.di

import com.example.kmanga.data.repo.top_manga.TopMangaRepository
import com.example.kmanga.data.repo.top_manga.TopMangaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindTopMangaRepo(topMangaRepositoryImpl: TopMangaRepositoryImpl): TopMangaRepository
}