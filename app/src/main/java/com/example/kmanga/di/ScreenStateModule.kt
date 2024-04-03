package com.example.kmanga.di

import com.example.kmanga.base.ScreenState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ScreenStateModule {

    @Provides
    fun screenStateProvider(): ScreenState {
        return ScreenState()
    }
}