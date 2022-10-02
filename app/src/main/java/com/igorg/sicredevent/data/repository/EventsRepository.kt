package com.igorg.sicredevent.data.repository

import com.igorg.sicredevent.data.response.EventResponse
import com.igorg.sicredevent.domain.model.Checkin

interface EventsRepository {

    suspend fun fetchEvents(): List<EventResponse>

    suspend fun registerEvent(checkin: Checkin): Any
}