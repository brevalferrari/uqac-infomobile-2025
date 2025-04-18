package com.ferhatozcelik.jetpackcomposetemplate.util

import java.time.LocalDateTime
import java.time.Period

fun format(period: Period): String {
    val y = period.years
    val m = period.months
    val d = period.days

    val output = StringBuilder()
    if (y > 0) {
        output.append(
            if (y > 1) "$y ans" else "1 an"
        )
        if (m > 0) {
            if (d > 0) output.append(", ") else output.append(" et ")
        }
    }
    if (m > 0) {
        output.append(
            if (m > 1) "$m mois" else "1 mois"
        )
        if (d > 0) output.append(" et ")
    } else if (d > 0) output.append(" et ")
    if (d > 0) {
        output.append(
            if (d > 1) "$d jours" else "1 jour"
        )
    }
    return output.toString()
}

fun format(datetime: LocalDateTime): String {
    val time = datetime.toLocalTime()

    val output = StringBuilder()
    output.append("${time.hour}h")
    if (time.minute < 10) output.append("0")
    output.append(time.minute)
    val now = LocalDateTime.now()
    if ((now.dayOfYear != datetime.dayOfYear) or (now.year != datetime.year)) {
        output.append(" (${datetime.dayOfMonth}/${datetime.month.value}")
        if (datetime.year != LocalDateTime.now().year) {
            val décennie: Int = datetime.year / 100
            output.append(
                "/" + if (décennie == LocalDateTime.now().year / 100) {
                    datetime.year % (décennie * 100)
                } else {
                    datetime.year
                }
            )
        }
        output.append(")")
    }
    return output.toString()
}
