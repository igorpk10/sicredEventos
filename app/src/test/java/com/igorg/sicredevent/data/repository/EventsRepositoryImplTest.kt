package com.igorg.sicredevent.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.igorg.sicredevent.data.api.EventAPI
import com.igorg.sicredevent.factorys.EventResponseFactory
import com.igorg.sicredevent.rule.MainCoroutineRule
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class EventsRepositoryImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule()
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var api: EventAPI

    private lateinit var repository: EventsRepository

    val listEvents = listOf(EventResponseFactory.getEventResponseMock())


    @Before
    fun setup() {
        repository = EventsRepositoryImpl(api)
    }


    @Test
    fun `should get sucess when api has called`() = runTest {
        //Arrange
        whenever(api.fetchEvents()).thenReturn(listEvents)

        //Act
        val response = repository.fetchEvents()

        //Assert
        Assert.assertNotNull(response)
        Assert.assertEquals(response, listEvents)
    }

    @Test
    fun `should get error when api has called`() = runTest {
        //Arrange
        whenever(api.fetchEvents()).thenThrow(java.lang.RuntimeException("Error when api has called"))

        //Assert
        Assert.assertThrows(java.lang.RuntimeException::class.java) {
            runTest {
                repository.fetchEvents()
            }
        }
    }
}