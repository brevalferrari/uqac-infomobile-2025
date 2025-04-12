package com.ferhatozcelik.jetpackcomposetemplate.util.notifications

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

class NotificationIntent(
    val id: Int?,
    val title: String,
    val message: String,
    val deadAt: LocalDateTime
) {
    companion object {
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        fun fromIntent(intent: Intent): NotificationIntent? {
            val id = intent.getIntExtra(Notification.NOTIFICATION_ID_EXTRA_KEY, -666)
            return intent.getStringExtra(Notification.TITLE_EXTRA_KEY)?.let { title ->
                intent.getStringExtra(Notification.MESSAGE_EXTRA_KEY)?.let { message ->
                    intent.getSerializableExtra(
                        Notification.DEAD_AT_KEY,
                        LocalDateTime::class.java
                    )?.let { deadAt ->
                        NotificationIntent(
                            if (id == -666) null else id, title, message, deadAt
                        )
                    }
                }
            }

        }
    }

    fun toIntent(context: Context): Intent {
        return Intent(
            context,
            Notification::class.java
        ).apply { putExtra(Notification.NOTIFICATION_ID_EXTRA_KEY, id) }
            .apply { putExtra(Notification.TITLE_EXTRA_KEY, title) }
            .apply { putExtra(Notification.MESSAGE_EXTRA_KEY, message) }
            .apply { putExtra(Notification.DEAD_AT_KEY, deadAt) }
    }
}
