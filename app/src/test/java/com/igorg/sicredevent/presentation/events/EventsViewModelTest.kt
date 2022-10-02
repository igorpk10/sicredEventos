package com.igorg.sicredevent.presentation.events

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.igorg.sicredevent.domain.usecase.GetEventsUseCase
import com.igorg.sicredevent.factorys.EventFactory
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
class EventsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule()
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: EventsViewModel

    @Mock
    private lateinit var useCase: GetEventsUseCase

    private val events = EventFactory.getEventsMock()

    @Before
    fun setUp() {
        viewModel = EventsViewModel(useCase)
    }

    @Test
    fun `should get sucess when requested to fetch events`() = runTest {
        //Arrange
        whenever(useCase.fetchEvents()).thenReturn(events)

        //Act
        val result = viewModel.getEvents()

        //Assert

        assertNotNull(result)
        assertTrue(viewModel.viewState.value is EventsViewModel.MainViewState.StateSuccess)
    }

    @Test
    fun `should get error when requested to fetch events`() = runTest {
        //Arrange
        whenever(useCase.fetchEvents()).thenThrow(RuntimeException("Error"))

        //Act
        viewModel.getEvents()

        //Assert
        assertTrue(viewModel.viewState.value is EventsViewModel.MainViewState.StateError)
    }
}