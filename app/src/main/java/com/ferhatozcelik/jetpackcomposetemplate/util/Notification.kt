package com.ferhatozcelik.jetpackcomposetemplate.util

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.ferhatozcelik.jetpackcomposetemplate.R

class Notification : BroadcastReceiver() {
    companion object {
        const val CHANNEL_ID = "default"
        const val TITLE_EXTRA_KEY = "titleExtra"
        const val MESSAGE_EXTRA_KEY = "messageExtra"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        val notification =
            NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(R.drawable.logo)
                .setContentTitle(intent.getStringExtra(TITLE_EXTRA_KEY))
                .setContentText(intent.getStringExtra(MESSAGE_EXTRA_KEY)).build()
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        sendNotification(notification, manager)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun sendNotification(notification: Notification, manager: NotificationManager) {
        manager.notify(manager.activeNotifications.maxBy { it.id }.id + 1, notification)
    }
}
