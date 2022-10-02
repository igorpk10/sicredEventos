package com.igorg.sicredevent.factorys

import com.igorg.sicredevent.domain.model.Checkin

object CheckinFactory {

    fun getCheckinMock() = Checkin(
        eventId = 11L,
        name = "Evento",
        email = "gg.@gg.com"
    )
}