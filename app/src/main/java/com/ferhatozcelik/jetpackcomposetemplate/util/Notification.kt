package com.ferhatozcelik.jetpackcomposetemplate.util

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

class Notification : BroadcastReceiver() {
    companion object {
        const val CHANNEL_ID = "default"
        const val CHANNEL_NAME = "Default"
        const val NOTIFICATION_ID_EXTRA_KEY = "idExtra"
        const val TITLE_EXTRA_KEY = "titleExtra"
        const val MESSAGE_EXTRA_KEY = "messageExtra"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        NotificationIntent.fromIntent(intent)?.let { n ->
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

    @RequiresApi(Build.VERSION_CODES.M)
    private fun sendNotification(
        notification: Notification,
        manager: NotificationManager,
        notificationId: Int
    ) {
        manager.notify(notificationId, notification)
    }
}
