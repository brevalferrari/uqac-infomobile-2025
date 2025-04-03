package com.ferhatozcelik.jetpackcomposetemplate.util

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES.TIRAMISU
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

class NotificationIntent(
    val id: Int?,
    val title: String,
    val message: String,
    val deadAt: LocalDateTime?
) {
    companion object {
        @RequiresApi(TIRAMISU)
        fun fromIntent(intent: Intent): NotificationIntent? {
            val id = intent.getIntExtra(Notification.NOTIFICATION_ID_EXTRA_KEY, -666)
            return intent.getStringExtra(Notification.TITLE_EXTRA_KEY)?.let { title ->
                intent.getStringExtra(Notification.MESSAGE_EXTRA_KEY)?.let { message ->
                    NotificationIntent(
                        if (id == -666) null else id, title, message, intent.getSerializableExtra(
                            Notification.DEAD_AT_KEY,
                            LocalDateTime::class.java
                        )
                    )
                }
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun toIntent(context: Context): Intent {
        var intent = Intent(context, Notification::class.java)
        intent.putExtra(Notification.NOTIFICATION_ID_EXTRA_KEY, id)
        intent.putExtra(Notification.TITLE_EXTRA_KEY, title)
        intent.putExtra(Notification.MESSAGE_EXTRA_KEY, message)
        intent.putExtra(Notification.DEAD_AT_KEY, deadAt)
        return intent
    }
}
