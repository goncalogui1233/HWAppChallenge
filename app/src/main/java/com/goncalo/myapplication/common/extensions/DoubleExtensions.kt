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

/**
 * This method is used to format the ratings throughout the application
 */
fun Double.formatRatingDecimals(): String {
    val decimal = this.div(10)

    return if(decimal == 10.0){
        decimal.formatDecimalDigits(0)
    } else {
        decimal.formatDecimalDigits(1)
    }
}