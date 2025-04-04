package com.ferhatozcelik.jetpackcomposetemplate.util.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneId
import javax.inject.Inject

class RoutineAlarmScheduler @Inject constructor(
    private val context: Context,
    private val alarmManager: AlarmManager
) {
    @RequiresApi(Build.VERSION_CODES.O)
    private fun intent(
        id: Int,
        title: String,
        message: String,
        deadAt: LocalDateTime
    ): PendingIntent {
        val intent = NotificationIntent(id, title, message, deadAt).toIntent(context)
        return PendingIntent.getBroadcast(
            context, id, intent, PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun scheduleRepeating(intent: PendingIntent, start: LocalDateTime, period: Period) {
        var triggerAtMillis = localDateTimeToAlarmDumbUnit(start)
        if (start.isBefore(LocalDateTime.now())) {
            Log.d(null, "routine start is before now, delaying notification till next period ends")
            val n: Double = (
                    localDateTimeToAlarmDumbUnit(LocalDateTime.now()).toDouble() -
                            localDateTimeToAlarmDumbUnit(start).toDouble()) /
                    (period.days * 60000 * 60 * 24 + period.months * 60000 * 60 * 24 * 30 + period.years * 60000 * 60 * 24 * 365)
            triggerAtMillis =
                localDateTimeToAlarmDumbUnit(LocalDateTime.now()) + (n.toBigDecimal().setScale(
                    0,
                    RoundingMode.UP
                ).toDouble() - n).toLong()
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            triggerAtMillis,
            period.days * AlarmManager.INTERVAL_DAY + period.months * AlarmManager.INTERVAL_DAY * 31 + period.years * AlarmManager.INTERVAL_DAY * 365,
            intent
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun localDateTimeToAlarmDumbUnit(time: LocalDateTime): Long {
        return time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000L
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun scheduleOnce(intent: PendingIntent, start: LocalDateTime) {
        alarmManager.set(AlarmManager.RTC_WAKEUP, localDateTimeToAlarmDumbUnit(start), intent)
    }

    private fun cancel(intent: PendingIntent) {
        alarmManager.cancel(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun schedule(routine: Routine) {
        Log.d(
            null,
            "received schedule request for routine \"${routine.name}\" (\"${routine.description}\")"
        )
        val intent =
            intent(
                routine.id.hashCode(),
                routine.name,
                routine.description,
                routine.endTime ?: routine.startTime.plusSeconds(1)
            )
        if (routine.period != null) {
            Log.d(null, "scheduling a repeating alarm")
            scheduleRepeating(intent, routine.startTime, routine.period!!)
        } else {
            Log.d(null, "scheduling a single alarm")
            scheduleOnce(intent, routine.startTime)
        }
        Log.d(null, "done scheduling.")
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
                routine.endTime ?: routine.startTime.plusSeconds(1)
            )
        )
    }
}
