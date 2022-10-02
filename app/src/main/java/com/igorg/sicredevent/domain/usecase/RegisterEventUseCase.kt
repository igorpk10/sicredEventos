package com.igorg.sicredevent.domain.usecase

import com.igorg.sicredevent.domain.model.Checkin

interface RegisterEventUseCase {

    suspend fun registerEvent(checkin: Checkin): Any
}