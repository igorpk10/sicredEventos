package com.igorg.sicredevent.factorys

import com.igorg.sicredevent.data.response.EventResponse
import java.math.BigDecimal

object EventResponseFactory {

    fun getEventResponseMock() = EventResponse(
        id = 1L,
        title = "titulo",
        price = BigDecimal.ZERO,
        latitude = BigDecimal.ZERO,
        longitude = BigDecimal.ZERO,
        imageURL = "http://imagem.com.br",
        description = "Descrição",
        date = 313131313,
        people = listOf()
    )

}