package com.example.kmanga.di

import com.example.kmanga.service.shared_preferences.SPImpl
import com.example.kmanga.service.shared_preferences.SPService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class SPModule {

    @Binds
    @ActivityScoped
    abstract fun bindSharedPreferences(spImpl: SPImpl): SPService
}