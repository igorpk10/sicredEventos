package com.igorg.sicredevent.presentation.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorg.sicredevent.domain.model.Event
import com.igorg.sicredevent.domain.usecase.GetEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    val useCase: GetEventsUseCase
) : ViewModel() {

    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()
    val viewState: LiveData<MainViewState> get() = _viewState

    fun getEvents() = viewModelScope.launch {
        _viewState.value = MainViewState.StateLoading

        try {
            val events = useCase.fetchEvents()
            _viewState.value = MainViewState.StateSuccess(events)
        } catch (ex: Exception) {
            _viewState.value = MainViewState.StateError
        }
    }

    sealed class MainViewState {
        object StateLoading : MainViewState()
        object StateError : MainViewState()
        data class StateSuccess(val events: List<Event>) : MainViewState()
    }
}
