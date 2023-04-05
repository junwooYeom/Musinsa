package com.example.musinsa.di

import com.example.musinsa.data.ListApiRepositoryImpl
import com.example.musinsa.domain.ListApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsListApiRepository(impl: ListApiRepositoryImpl): ListApiRepository
}