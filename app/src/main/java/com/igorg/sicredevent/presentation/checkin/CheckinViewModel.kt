package com.igorg.sicredevent.presentation.checkin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorg.sicredevent.domain.model.Checkin
import com.igorg.sicredevent.domain.usecase.RegisterEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckinViewModel @Inject constructor(
    private val useCase: RegisterEventUseCase
) : ViewModel() {

    private val _viewState: MutableLiveData<CheckinState> = MutableLiveData()
    val viewState: LiveData<CheckinState> get() = _viewState


    fun registerEvent(event: Checkin) = viewModelScope.launch {
        _viewState.value = CheckinState.StateLoading

        try {
            val result = useCase.registerEvent(event)

            _viewState.value = CheckinState.StateSuccess(result)
        } catch (ex: Exception) {
            _viewState.value = CheckinState.StateError
        }
    }

    sealed class CheckinState {
        object StateLoading : CheckinState()
        object StateError : CheckinState()
        data class StateSuccess(val result: Any) : CheckinState()
    }
}