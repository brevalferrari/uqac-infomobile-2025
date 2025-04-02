package com.ferhatozcelik.jetpackcomposetemplate.ui.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.ferhatozcelik.jetpackcomposetemplate.navigation.NavGraph
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.MyApplicationTheme
import com.ferhatozcelik.jetpackcomposetemplate.util.Notification
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            NotificationChannel(
                Notification.CHANNEL_ID, Notification.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
        )
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavGraph(navController = navController)
                }
            }
        }
    }
}
