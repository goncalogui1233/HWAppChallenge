package com.goncalo.myapplication.common.extensions

import org.junit.Assert.assertEquals
import org.junit.Test

class DoubleExtensionsKtTest {

    @Test
    fun testFormatDecimalDigits() {
        assertEquals("17.50", 17.5.formatDecimalDigits(2))
        assertEquals("17.50", 17.500000000000.formatDecimalDigits(2))
        assertEquals("20.00", 20.0.formatDecimalDigits(2))
        assertEquals("18.49", 18.49.formatDecimalDigits(2))
        assertEquals("32.32", 32.322.formatDecimalDigits(2))
        assertEquals("40.87", 40.867.formatDecimalDigits(2))
        assertEquals("40.9", 40.86.formatDecimalDigits(1))
    }

    @Test
    fun testFormatRatingDecimals() {
        assertEquals("10", 100.0.formatRatingDecimals())
        assertEquals("9.3", 93.0.formatRatingDecimals())
        assertEquals("4.7", 47.0.formatRatingDecimals())
    }

}