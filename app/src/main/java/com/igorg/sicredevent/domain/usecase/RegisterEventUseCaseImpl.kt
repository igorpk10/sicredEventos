package com.igorg.sicredevent.domain.usecase

import com.igorg.sicredevent.data.repository.EventsRepository
import com.igorg.sicredevent.domain.model.Checkin
import com.igorg.sicredevent.framework.coroutines.CoroutinesDispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterEventUseCaseImpl @Inject constructor(
    private val repository: EventsRepository,
    private val coroutineContext: CoroutinesDispatchers
) : RegisterEventUseCase {
    override suspend fun registerEvent(checkin: Checkin): Any {
        return withContext(coroutineContext.io()){
            repository.registerEvent(checkin)
        }
    }
}