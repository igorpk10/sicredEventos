package com.igorg.sicredevent.domain.extensions

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

private const val CURRENCY_SCALE = 2
private const val CURRENCY_LANGUAGE = "pt"
private const val CURRENCY_COUNTRY = "BR"

fun BigDecimal.toCurrency(): String{
    val displayVal: BigDecimal = this.setScale(CURRENCY_SCALE, RoundingMode.HALF_EVEN)
    val usdCostFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale(CURRENCY_LANGUAGE, CURRENCY_COUNTRY))
    usdCostFormat.minimumFractionDigits = CURRENCY_SCALE
    usdCostFormat.maximumFractionDigits = CURRENCY_SCALE
    return usdCostFormat.format(displayVal)
}