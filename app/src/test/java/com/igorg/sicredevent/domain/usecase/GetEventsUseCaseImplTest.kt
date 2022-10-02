package com.igorg.sicredevent.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.igorg.sicredevent.data.repository.EventsRepository
import com.igorg.sicredevent.factorys.EventFactory
import com.igorg.sicredevent.factorys.EventResponseFactory
import com.igorg.sicredevent.rule.MainCoroutineRule
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetEventsUseCaseImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule()
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: EventsRepository

    private lateinit var useCase: GetEventsUseCase

    private val eventResponse = EventResponseFactory.getEventResponseMock()
    private val event = EventFactory.getEventMock()

    @Before
    fun setUp() {
        useCase = GetEventsUseCaseImpl(repository, mainCoroutineRule.testDispatcherProvider)
    }


    @Test
    fun `should get success when repository has called`() = runTest {
        //Arrange
        whenever(repository.fetchEvents()).thenReturn(listOf(eventResponse))

        //Act
        val response = useCase.fetchEvents()

        //Assert
        assertNotNull(response)
        assertEquals(event.id, response[0].id)
    }

    @Test
    fun `should get error when repository has called`() = runTest {
        //Arrange
        whenever(repository.fetchEvents()).thenThrow(java.lang.RuntimeException("Error"))

        //Assert
        assertThrows(java.lang.RuntimeException::class.java){
            runTest {
                useCase.fetchEvents()
            }
        }
    }
}