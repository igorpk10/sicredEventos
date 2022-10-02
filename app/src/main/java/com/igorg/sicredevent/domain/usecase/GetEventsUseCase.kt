package com.igorg.sicredevent.domain.usecase

import com.igorg.sicredevent.domain.model.Event


interface GetEventsUseCase {

    suspend fun fetchEvents(): List<Event>
}