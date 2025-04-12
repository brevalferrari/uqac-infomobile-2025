package com.ferhatozcelik.jetpackcomposetemplate.util.notifications

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.ferhatozcelik.jetpackcomposetemplate.R
import java.time.LocalDateTime

class Notification : BroadcastReceiver() {
    companion object {
        const val CHANNEL_ID = "default"
        const val CHANNEL_NAME = "Default"
        const val NOTIFICATION_ID_EXTRA_KEY = "idExtra"
        const val TITLE_EXTRA_KEY = "titleExtra"
        const val MESSAGE_EXTRA_KEY = "messageExtra"
        const val DEAD_AT_KEY = "stopTimeExtra"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onReceive(context: Context, intent: Intent) {
        NotificationIntent.Companion.fromIntent(intent)?.let { n ->
            if (n.deadAt.isBefore(LocalDateTime.now())) {
                Log.i(
                    "RIP",
                    "routine \"${n.title}\" (\"${n.message}\") has reached end of life, ignoring notification request"
                ) // TODO: make it stop (needs AlarmManager)
                return
            }
            val notification =
                NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(R.drawable.logo)
                    .setContentTitle(n.title)
                    .setContentText(n.message).build()
            val manager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            sendNotification(
                notification,
                manager,
                n.id ?: (manager.activeNotifications.maxBy { it.id }.id + 1)
            )
        } ?: Log.e(null, "received invalid notification intent!")
    }

    private fun sendNotification(
        notification: Notification,
        manager: NotificationManager,
        notificationId: Int
    ) {
        Log.d(null, "sending notification for routine hash $notificationId")
        manager.notify(notificationId, notification)
        Log.d(null, "notification sent for routine hash $notificationId.")
    }
}
