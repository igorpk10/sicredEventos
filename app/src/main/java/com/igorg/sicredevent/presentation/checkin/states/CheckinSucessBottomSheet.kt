package com.igorg.sicredevent.presentation.checkin.states

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.igorg.sicredevent.databinding.DialogCheckinSucessBinding
import com.igorg.sicredevent.presentation.base.bottomsheet.BaseBottomSheet

class CheckinSucessBottomSheet : BaseBottomSheet() {

    private var _binding: DialogCheckinSucessBinding? = null
    private val binding: DialogCheckinSucessBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DialogCheckinSucessBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(true)

        binding.button.setOnClickListener {
            this@CheckinSucessBottomSheet.dismiss()
        }
    }
}