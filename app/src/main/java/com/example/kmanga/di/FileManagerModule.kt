package com.example.kmanga.di

import com.example.kmanga.service.file_manager.FileManager
import com.example.kmanga.service.file_manager.FileManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class FileManagerModule {

    @Binds
    @ActivityScoped
    abstract fun bindFileManager(fileManagerImpl: FileManagerImpl): FileManager
}
