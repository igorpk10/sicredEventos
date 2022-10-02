package com.igorg.sicredevent.framework.di

import com.igorg.sicredevent.framework.coroutines.AppCoroutinesDispatcher
import com.igorg.sicredevent.framework.coroutines.CoroutinesDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {

    @Binds
    fun bindDispatchers(dispatcher: AppCoroutinesDispatcher): CoroutinesDispatchers
}