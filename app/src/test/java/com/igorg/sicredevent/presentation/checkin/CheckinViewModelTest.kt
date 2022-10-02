package com.igorg.sicredevent.presentation.checkin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.igorg.sicredevent.domain.usecase.RegisterEventUseCase
import com.igorg.sicredevent.domain.usecase.RegisterEventUseCaseImpl
import com.igorg.sicredevent.factorys.CheckinFactory
import com.igorg.sicredevent.rule.MainCoroutineRule
import com.nhaarman.mockitokotlin2.any
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
class CheckinViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule()
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var usecase: RegisterEventUseCase

    private lateinit var viewModel: CheckinViewModel

    private val checkin = CheckinFactory.getCheckinMock()


    @Before
    fun setUp() {
        viewModel = CheckinViewModel(usecase)
    }

    @Test
    fun `should get success when register event`() = runTest {
        //Arrange
        whenever(usecase.registerEvent(any())).thenReturn(Any())

        //Act
        viewModel.registerEvent(checkin)

        //Assert
        assertNotNull(viewModel.viewState.value)
        assertTrue(viewModel.viewState.value is CheckinViewModel.CheckinState.StateSuccess)
    }

    @Test
    fun `should get error when register event`() = runTest {
        //Arrange
        whenever(usecase.registerEvent(any())).thenThrow(RuntimeException("Error"))

        //Act
        viewModel.registerEvent(checkin)

        //Assert
        assertTrue(viewModel.viewState.value is CheckinViewModel.CheckinState.StateError)
    }

}