package com.igorg.sicredevent.presentation.base.bottomsheet

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.igorg.sicredevent.R

abstract class BaseBottomSheet: BottomSheetDialogFragment() {

    override fun getTheme(): Int = R.style.CustomBottomSheetDialog
}