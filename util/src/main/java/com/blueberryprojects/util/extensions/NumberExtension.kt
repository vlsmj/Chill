package com.blueberryprojects.util.extensions

import java.util.concurrent.TimeUnit

/**
 * Format the milliseconds to hour and minute custom format.
 *
 * - 6302760 milliseconds will be 1h 45m
 */
fun Long.toHourAndMinute(): String {
    val milliseconds = this

    val hour = TimeUnit.MILLISECONDS.toHours(milliseconds)
    val totalMinutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)

    val minute = totalMinutes - (hour * 60)

    val hourLabel = "${hour}h"
    val minuteLabel = "${minute}m"

    return StringBuilder().apply {
        if (hour > 0) append(hourLabel)
        if (minute > 0) {
            append(" ")
            append(minuteLabel)
        }
    }.toString()
}