package com.igorg.sicredevent.presentation.checkin

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.igorg.sicredevent.R
import com.igorg.sicredevent.databinding.DialogCheckinBinding
import com.igorg.sicredevent.domain.model.Checkin
import com.igorg.sicredevent.presentation.base.bottomsheet.BaseBottomSheet
import com.igorg.sicredevent.presentation.base.extensions.hide
import com.igorg.sicredevent.presentation.base.extensions.show
import com.igorg.sicredevent.presentation.checkin.states.CheckinErrorBottomSheet
import com.igorg.sicredevent.presentation.checkin.states.CheckinSucessBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckinBottomSheeet(
    private val eventId: Long,
) : BaseBottomSheet() {

    private var _binding: DialogCheckinBinding? = null
    private val binding: DialogCheckinBinding get() = _binding!!

    val viewModel: CheckinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DialogCheckinBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(false)


        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                CheckinViewModel.CheckinState.StateError -> setStateError()
                CheckinViewModel.CheckinState.StateLoading -> setStateLoading()
                is CheckinViewModel.CheckinState.StateSuccess -> setStateSuccess()
            }
        }

        binding.close.setOnClickListener {
            this@CheckinBottomSheeet.dismiss()
        }

        binding.checkinButton.setOnClickListener {
            val name = binding.nameEdit.text.toString()
            val email = binding.emailEdit.text.toString()

            val hasNameValid = validateName(name)
            val hasEmailValid = validateEmail(email)

            if (!hasNameValid) {
                binding.nameLayout.error = "Ops, algo está incorreto com o nome"
                return@setOnClickListener
            }

            if (!hasEmailValid) {
                binding.emailLayout.error = "Ops, algo está incorreto com o email"
                return@setOnClickListener
            }

            this.viewModel.registerEvent(
                Checkin(
                    eventId = eventId,
                    name = name,
                    email = email
                )
            )
        }
    }

    private fun validateName(name: String): Boolean {
        return !name.isNullOrBlank()
    }

    private fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun setStateLoading() {
        binding.checkinButton.hide()
        binding.progress.show()
    }

    private fun setStateSuccess() {
        CheckinSucessBottomSheet().show(
            parentFragmentManager,
            CheckinBottomSheeet::class.toString()
        )
        this@CheckinBottomSheeet.dismiss()
    }

    private fun setStateError() {
        CheckinErrorBottomSheet().show(parentFragmentManager, CheckinBottomSheeet::class.toString())
        this@CheckinBottomSheeet.dismiss()
    }
}
