package com.example.kmanga.data.di

import android.content.Context
import com.example.kmanga.BuildConfig
import com.google.gson.GsonBuilder
import com.example.kmanga.data.di.qualifier.AuthOkhttpInterceptorClient
import com.example.kmanga.data.di.qualifier.AuthRetrofit
import com.example.kmanga.data.di.qualifier.DefaultOkhttpInterceptorClient
import com.example.kmanga.data.di.qualifier.DefaultRetrofit
import com.example.kmanga.service.LogoutManager
import com.example.kmanga.data.network.interceptor.TokenInterceptor
import com.example.kmanga.data.network.parser.DatetimeParser
import com.example.kmanga.service.shared_preferences.SPImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    @Provides
    @Singleton
    @DefaultRetrofit
    fun defaultRetrofitProvider(
        @DefaultOkhttpInterceptorClient client: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverter)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    @AuthRetrofit
    fun authRetrofitProvider(
        @AuthOkhttpInterceptorClient client: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverter)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun gsonFactoryProvider(): GsonConverterFactory {
        return GsonConverterFactory
            .create(
                GsonBuilder()
                    .setLenient()
                    .disableHtmlEscaping()
                    .registerTypeAdapter(Date::class.java, DatetimeParser())
                    .create()
            )
    }

    @Provides
    @Singleton
    @DefaultOkhttpInterceptorClient
    fun defaultOkhttpProvider(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
        httpBuilder
            .connectTimeout(TIMEOUT_TIME_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_TIME_IN_SECONDS, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpBuilder.addInterceptor(interceptor)
        }
        return httpBuilder.build()
    }

    @Provides
    @Singleton
    @AuthOkhttpInterceptorClient
    fun authOkhttpProvider(@ApplicationContext context: Context): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
        val spService = SPImpl(context)
        val logoutManager = LogoutManager(context)

        httpBuilder
            .connectTimeout(TIMEOUT_TIME_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_TIME_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(TokenInterceptor(spService, logoutManager))
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpBuilder.addInterceptor(interceptor)
        }
        return httpBuilder.build()
    }

    companion object {
        const val TIMEOUT_TIME_IN_SECONDS = 30L
    }
}