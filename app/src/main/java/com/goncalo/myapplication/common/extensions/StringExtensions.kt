package com.goncalo.myapplication.common.extensions

fun String.empty() = ""

fun String.concatAddress(addressTwo: String) =
    if (addressTwo.isNotEmpty()) "$this, $addressTwo" else this
