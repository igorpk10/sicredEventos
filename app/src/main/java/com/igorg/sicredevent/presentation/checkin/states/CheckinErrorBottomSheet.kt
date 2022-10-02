package com.igorg.sicredevent.presentation.checkin.states

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.igorg.sicredevent.databinding.DialogCheckinErrorBinding
import com.igorg.sicredevent.databinding.DialogCheckinSucessBinding
import com.igorg.sicredevent.presentation.base.bottomsheet.BaseBottomSheet

class CheckinErrorBottomSheet : BaseBottomSheet() {

    private var _binding: DialogCheckinErrorBinding? = null
    private val binding: DialogCheckinErrorBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DialogCheckinErrorBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(true)


        binding.button.setOnClickListener {
            this@CheckinErrorBottomSheet.dismiss()
        }
    }
}