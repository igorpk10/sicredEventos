package com.igorg.sicredevent.domain.extensions

import org.junit.Assert.*

import org.junit.Test

class TimeStampExtensionKtTest {

    @Test
    fun parseToDate() {
        val expected = "20 de ago de 2018"
        val long: Long = 1534784400000L
        val date = long.parseToDate()
        assertEquals(expected, date)
    }
}