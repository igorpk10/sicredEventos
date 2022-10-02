package com.igorg.sicredevent.domain.extensions

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal


@RunWith(MockitoJUnitRunner::class)
class BigDecimalExtensionsKtTest {

    @Test
    fun toCurrency() {
        val expected = "R$ 21,99"
        val bigDecimal = BigDecimal(21.99).toCurrency()
        assertEquals(expected, bigDecimal)
    }
}