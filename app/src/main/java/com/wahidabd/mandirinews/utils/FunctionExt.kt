package com.wahidabd.mandirinews.utils

import com.wahidabd.library.utils.common.emptyString
import java.text.SimpleDateFormat
import java.util.Locale


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */

val localeIndonesia = Locale("ID", "id")

fun String.formatStringToDate(): String {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", localeIndonesia)
    val output = SimpleDateFormat("dd MMMM yyyy", localeIndonesia)
    val date = input.parse(this)
    return output.format(date ?: emptyString())
}