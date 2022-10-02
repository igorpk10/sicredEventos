package com.igorg.sicredevent.domain.usecase

import com.igorg.sicredevent.data.repository.EventsRepository
import com.igorg.sicredevent.domain.extensions.parseToDate
import com.igorg.sicredevent.domain.extensions.toCurrency
import kotlinx.coroutines.withContext
import com.igorg.sicredevent.domain.model.Event
import com.igorg.sicredevent.framework.coroutines.CoroutinesDispatchers
import javax.inject.Inject

class GetEventsUseCaseImpl @Inject constructor(
    private val repository: EventsRepository,
    private val coroutineContext: CoroutinesDispatchers
) : GetEventsUseCase {
    override suspend fun fetchEvents(): List<Event> {
        return withContext(coroutineContext.io()) {
            repository.fetchEvents().map {
                Event(
                    id = it.id,
                    title = it.title,
                    imageURL = it.imageURL,
                    description = it.description,
                    date = it.date.parseToDate(),
                    price = it.price.toCurrency()
                )
            }
        }
    }
}
