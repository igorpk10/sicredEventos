package com.igorg.sicredevent.data.repository

import com.igorg.sicredevent.data.api.EventAPI
import com.igorg.sicredevent.data.response.EventResponse
import com.igorg.sicredevent.domain.model.Checkin
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val api: EventAPI
) : EventsRepository {

    override suspend fun fetchEvents(): List<EventResponse> = api.fetchEvents()

    override suspend fun registerEvent(checkin: Checkin): Any = api.registerEvent(checkin)
}
