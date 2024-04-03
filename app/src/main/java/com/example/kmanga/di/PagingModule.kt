//package com.example.kmanga.di
//
//import com.example.kmanga.service.paging.MyFilePagingSource
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//
//@Module
//@InstallIn(SingletonComponent::class)
//class PagingModule {
//
//    @Provides
//    fun bindMyFilePagingSource(apiFile: FileAPI): MyFilePagingSource {
//        return MyFilePagingSource(apiFile)
//    }
//}