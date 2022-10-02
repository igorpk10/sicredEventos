package com.igorg.sicredevent.data.api

import com.igorg.sicredevent.data.response.EventResponse
import com.igorg.sicredevent.domain.model.Checkin
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventAPI {

    @GET("events")
    suspend fun fetchEvents(): List<EventResponse>

    @POST("checkin")
    suspend fun registerEvent(
        @Body checkin: Checkin
    ): Any
}
