package com.goncalo.myapplication.common.extensions

import java.text.DecimalFormat


/**
 * This method takes a double and add the number of decimals that we want
 */
fun Double.formatDecimalDigits(decimalDigits: Int): String {
    val decimalFormat = DecimalFormat("#.##")
    val value = decimalFormat.format(this).toDouble()
    return "%.${decimalDigits}f".format(value)
}