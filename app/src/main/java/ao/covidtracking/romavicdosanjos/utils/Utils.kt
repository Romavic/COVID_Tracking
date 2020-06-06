@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package ao.covidtracking.romavicdosanjos.utils

import android.annotation.SuppressLint
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun doubleToCurrency(number: Double): String {
    val numFormat = NumberFormat.getCurrencyInstance(Locale.UK)
    return numFormat.format(number).removePrefix("£")
}

fun intToCurrency(number: Int): String {
    val numFormat = NumberFormat.getCurrencyInstance(Locale.UK)
    return numFormat.format(number).removePrefix("£")
}

@SuppressLint("SimpleDateFormat")
fun dateTimeToString(date: String): String {
    var value: String
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    SimpleDateFormat("dd/MM/yyyy HH:mm:ss").apply {
        value = format(parser.parse(date))
    }
    return value
}

@SuppressLint("SimpleDateFormat")
fun dateToString(date: String): String {
    var value: String
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    SimpleDateFormat("dd/MM/yyyy").apply {
        value = format(parser.parse(date))
    }
    return value
}