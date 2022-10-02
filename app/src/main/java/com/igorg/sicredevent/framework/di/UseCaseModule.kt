package com.igorg.sicredevent.framework.di

import com.igorg.sicredevent.domain.usecase.GetEventsUseCase
import com.igorg.sicredevent.domain.usecase.GetEventsUseCaseImpl
import com.igorg.sicredevent.domain.usecase.RegisterEventUseCase
import com.igorg.sicredevent.domain.usecase.RegisterEventUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetEventUseCase(useCase: GetEventsUseCaseImpl): GetEventsUseCase

    @Binds
    fun bindRegisterEventUseCase(useCase: RegisterEventUseCaseImpl): RegisterEventUseCase
}