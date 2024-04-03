package com.example.kmanga.data.di

import android.content.Context
import androidx.room.Room
//import com.example.kmanga.service.database.AppDatabase
//import com.example.kmanga.service.database.dao.FileDao
//import com.example.kmanga.service.database.dao.RecentFileDao
//import com.example.kmanga.service.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//class DatabaseModule {

//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
//        return Room.databaseBuilder(
//            context,
//            AppDatabase::class.java, "local-database"
//        )
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//
//    @Provides
//    fun provideMyFileDao(database: AppDatabase): FileDao {
//        return database.myFileDao()
//    }
//
//    @Provides
//    fun provideUserDao(database: AppDatabase): UserDao {
//        return database.userDao()
//    }
//
//    @Provides
//    fun provideRecentFileDao(database: AppDatabase): RecentFileDao {
//        return database.recentFileDao()
//    }
//}