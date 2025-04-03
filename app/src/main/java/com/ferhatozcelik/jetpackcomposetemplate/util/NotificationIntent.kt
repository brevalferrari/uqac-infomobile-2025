package com.ferhatozcelik.jetpackcomposetemplate.util

import android.content.Context
import android.content.Intent

class NotificationIntent(
    val id: Int?,
    val title: String,
    val message: String
) {
    companion object {
        fun fromIntent(intent: Intent): NotificationIntent? {
            val id = intent.getIntExtra(Notification.NOTIFICATION_ID_EXTRA_KEY, -666)
            return intent.getStringExtra(Notification.TITLE_EXTRA_KEY)?.let { title ->
                intent.getStringExtra(Notification.MESSAGE_EXTRA_KEY)?.let { message ->
                    NotificationIntent(if (id == -666) null else id, title, message)
                }
            }

        }
    }

    fun toIntent(context: Context): Intent {
        var intent = Intent(context, Notification::class.java)
        intent.putExtra(Notification.NOTIFICATION_ID_EXTRA_KEY, id)
        intent.putExtra(Notification.TITLE_EXTRA_KEY, title)
        intent.putExtra(Notification.MESSAGE_EXTRA_KEY, message)
        return intent
    }
}
