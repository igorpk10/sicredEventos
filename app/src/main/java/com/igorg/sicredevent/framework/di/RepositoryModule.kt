package com.igorg.sicredevent.framework.di

import com.igorg.sicredevent.data.repository.EventsRepository
import com.igorg.sicredevent.data.repository.EventsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindFavoritesRepository(
        repository: EventsRepositoryImpl
    ): EventsRepository
}