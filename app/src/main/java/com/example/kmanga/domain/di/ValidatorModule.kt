package com.example.kmanga.domain.di

import com.example.kmanga.domain.validator.ValidatorImpl
import com.example.kmanga.domain.validator.ValidatorService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ValidatorModule {

    @Binds
    abstract fun validatorServiceProvider(validatorImpl: ValidatorImpl): ValidatorService
}