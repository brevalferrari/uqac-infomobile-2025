package com.ferhatozcelik.jetpackcomposetemplate.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

class DateUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertMillisToLocalDate(millis: Long): ZonedDateTime {
        // Interpret the milliseconds as the start of the day in UTC, then convert to Los Angeles time
        val utcDateAtStartOfDay = Instant
            .ofEpochMilli(millis)
            .atZone(ZoneOffset.UTC)
            .toLocalDate()

        // Convert to the same instant in Local time zone
        val localDate = utcDateAtStartOfDay.atStartOfDay(ZoneId.systemDefault())

        return localDate

    }
}
