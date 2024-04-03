package com.example.kmanga.data.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultOkhttpInterceptorClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthOkhttpInterceptorClient