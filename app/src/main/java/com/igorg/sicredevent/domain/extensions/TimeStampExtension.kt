package com.igorg.sicredevent.domain.extensions

import java.text.DateFormat
import java.util.*

fun Long.parseToDate(): String {
    val format = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault())
    val date = Date(this)
    return format.format(date)
}