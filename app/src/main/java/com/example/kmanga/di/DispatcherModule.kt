package com.example.kmanga.di

import com.example.kmanga.util.dispatcher.DispatcherProvider
import com.example.kmanga.util.dispatcher.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    abstract fun bindDispatcher(dispatcherProviderImpl: DispatcherProviderImpl): DispatcherProvider
}