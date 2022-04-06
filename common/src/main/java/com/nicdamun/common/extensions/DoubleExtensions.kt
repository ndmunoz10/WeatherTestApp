package com.nicdamun.weatherapp.extensions

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Double.roundToOneDecimalPlace(): Double {
    val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.getDefault())).apply {
        roundingMode = RoundingMode.HALF_UP
    }
    return df.format(this).toDouble()
}