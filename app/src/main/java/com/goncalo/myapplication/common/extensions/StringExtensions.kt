package com.goncalo.myapplication.common.extensions

fun String.empty() = ""

/**
 * This method is used to concat two address. It is used to concat the address
 * of the property because the value comes in two different fields
 */
fun String.concatAddress(addressTwo: String) =
    if (addressTwo.isNotEmpty()) "$this, $addressTwo" else this
