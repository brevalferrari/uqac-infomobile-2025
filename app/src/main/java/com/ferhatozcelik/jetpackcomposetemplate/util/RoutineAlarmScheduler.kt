package com.ferhatozcelik.jetpackcomposetemplate.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build.VERSION_CODES.O
import android.util.Log
import androidx.annotation.RequiresApi
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import java.time.LocalDateTime
import java.time.Period
import java.time.ZonedDateTime
import javax.inject.Inject

class RoutineAlarmScheduler @Inject constructor(
    private val context: Context,
    private val alarmManager: AlarmManager
) {
    @RequiresApi(O)
    private fun intent(
        id: Int,
        title: String,
        message: String,
        deadAt: LocalDateTime?
    ): PendingIntent {
        val intent = NotificationIntent(id, title, message, deadAt).toIntent(context)
        return PendingIntent.getBroadcast(
            context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    @RequiresApi(O)
    private fun scheduleRepeating(intent: PendingIntent, start: LocalDateTime, period: Period) {
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            localDateTimeToAlarmDumbUnit(start),
            period.days * AlarmManager.INTERVAL_DAY + period.months * AlarmManager.INTERVAL_DAY * 31 + period.years * AlarmManager.INTERVAL_DAY * 365,
            intent
        )
    }

    @RequiresApi(O)
    private fun localDateTimeToAlarmDumbUnit(time: LocalDateTime): Long {
        return time.toInstant(ZonedDateTime.now().offset).toEpochMilli()
    }

    @RequiresApi(O)
    private fun scheduleOnce(intent: PendingIntent, start: LocalDateTime) {
        alarmManager.set(AlarmManager.RTC_WAKEUP, localDateTimeToAlarmDumbUnit(start), intent)
    }

    private fun cancel(intent: PendingIntent) {
        alarmManager.cancel(intent)
    }

    @RequiresApi(O)
    fun schedule(routine: Routine) {
        Log.d(
            null,
            "received schedule request for routine \"${routine.name}\" (\"${routine.description}\")"
        )
        val intent =
            intent(routine.id.hashCode(), routine.name, routine.description, routine.endTime)
        routine.period?.let { period ->
            Log.d(null, "scheduling a repeating alarm")
            scheduleRepeating(intent, routine.startTime, period)
        }
            ?: {
                Log.d(null, "scheduling a single alarm")
                scheduleOnce(intent, routine.startTime)
            }
    }

    @RequiresApi(O)
    fun cancel(routine: Routine) {
        Log.d(
            null,
            "received cancel request for routine \"${routine.name}\" (\"${routine.description}\")"
        )
        cancel(
            intent(
                routine.id.hashCode(),
                routine.name,
                routine.description,
                routine.endTime
            )
        )
    }
}
