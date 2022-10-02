package com.igorg.sicredevent.factorys

import com.igorg.sicredevent.domain.model.Event

object EventFactory {
    fun getEventMock() = Event(
        id = 1L,
        title = "titulo",
        imageURL = "http://imagem.com",
        description = "descrição",
        date = "20 de ago de 2018",
        price = "R$ 21,00"
    )

    fun getEventsMock() = listOf(
        Event(
            id = 1L,
            title = "titulo",
            imageURL = "http://imagem.com",
            description = "descrição",
            date = "20 de ago de 2018",
            price = "R$ 21,00"
        ),
        Event(
            id = 1L,
            title = "titulo",
            imageURL = "http://imagem.com",
            description = "descrição",
            date = "20 de ago de 2018",
            price = "R$ 21,00"
        )
    )
}