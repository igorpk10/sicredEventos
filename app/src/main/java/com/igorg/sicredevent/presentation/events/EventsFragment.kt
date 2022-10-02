package com.igorg.sicredevent.presentation.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.igorg.sicredevent.databinding.FragmentMainBinding
import com.igorg.sicredevent.domain.model.Event
import com.igorg.sicredevent.presentation.base.adapter.getGenericAdapterOf
import com.igorg.sicredevent.presentation.detail.EventDetailViewArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : Fragment() {

    companion object {
        private const val STATE_ERROR = 1
        private const val STATE_LOADING = 0
        private const val STATE_SUCCESS = 2
    }

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    private val viewModel: EventsViewModel by viewModels()

    private val eventsAdapter by lazy {
        getGenericAdapterOf {
            EventsViewHolder.create(it) {
                onSelectedItem(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initAdater()
        viewModel.getEvents()
    }

    private fun initObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            binding.flipper.displayedChild = when (it) {
                EventsViewModel.MainViewState.StateError -> {
                    STATE_ERROR
                }
                EventsViewModel.MainViewState.StateLoading -> {
                    STATE_LOADING
                }
                is EventsViewModel.MainViewState.StateSuccess -> {
                    eventsAdapter.submitList(it.events)
                    STATE_SUCCESS
                }
            }
        }
    }

    private fun initAdater() {
        binding.stateSuccess.eventsrecycler.apply {
            setHasFixedSize(true)
            this.adapter = eventsAdapter
        }
    }

    private fun onSelectedItem(it: Event) {
        val extras = it.title
        val direction = EventsFragmentDirections.actionEventsFragmentToEventDetailsFragment(
            extras,
            EventDetailViewArgs(it)
        )
        findNavController().navigate(direction)
    }
}