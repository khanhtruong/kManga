package com.example.kmanga.data.di

import com.example.kmanga.data.di.qualifier.AuthRetrofit
import com.example.kmanga.data.network.MyAnimeAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class APIModule {

    @Provides
    fun myAnimeProvider(@AuthRetrofit retrofit: Retrofit): MyAnimeAPI {
        return retrofit.create(MyAnimeAPI::class.java)
    }
}