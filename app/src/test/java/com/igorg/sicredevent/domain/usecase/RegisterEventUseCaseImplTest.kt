package com.igorg.sicredevent.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.igorg.sicredevent.data.repository.EventsRepository
import com.igorg.sicredevent.factorys.CheckinFactory
import com.igorg.sicredevent.rule.MainCoroutineRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RegisterEventUseCaseImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule()
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: EventsRepository

    private lateinit var useCase: RegisterEventUseCase

    private val checkin = CheckinFactory.getCheckinMock()

    @Before
    fun setUp() {
        useCase = RegisterEventUseCaseImpl(repository, mainCoroutineRule.testDispatcherProvider)
    }

    @Test
    fun `should get success when register for event`() = runTest {
        //Arrange
        whenever(repository.registerEvent(any())).thenReturn(Any())

        //Act
        val response = useCase.registerEvent(checkin)

        //Assert
        assertNotNull(response)
        assertTrue(response::class.java == Any::class.java)
    }

    @Test
    fun `should get error when register for event` () = runTest {
        //Arrange
        whenever(repository.registerEvent(any())).thenThrow(RuntimeException("Error"))

        //Act
        assertThrows(RuntimeException::class.java){
            runTest {
                val response = useCase.registerEvent(checkin)
            }
        }
    }
}