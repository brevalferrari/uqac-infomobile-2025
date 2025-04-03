package com.ferhatozcelik.jetpackcomposetemplate.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import java.time.LocalDateTime
import java.time.Period
import javax.inject.Inject

class RoutineAlarmScheduler @Inject constructor(
    private val context: Context,
    private val alarmManager: AlarmManager
) {
    private fun intent(id: Int, title: String, message: String): PendingIntent {
        val intent = NotificationIntent(id, title, message).toIntent(context)
        return PendingIntent.getBroadcast(
            context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun scheduleRepeating(intent: PendingIntent, start: LocalDateTime, period: Period) {
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            start.nano.toLong() / 1000000,
            period.days * AlarmManager.INTERVAL_DAY + period.months * AlarmManager.INTERVAL_DAY * 31 + period.years * AlarmManager.INTERVAL_DAY * 365,
            intent
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun scheduleOnce(intent: PendingIntent, start: LocalDateTime) {
        alarmManager.set(AlarmManager.RTC_WAKEUP, start.nano.toLong() / 1000000, intent)
    }

    private fun cancel(intent: PendingIntent) {
        alarmManager.cancel(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun schedule(routine: Routine) {
        val intent = intent(routine.id.hashCode(), routine.name, routine.description)
        routine.period?.let { period -> scheduleRepeating(intent, routine.startTime, period) }
            ?: { scheduleOnce(intent, routine.startTime) }
    }

    fun cancel(routine: Routine) {
        alarmManager.cancel(intent(routine.id.hashCode(), routine.name, routine.description))
    }
}
