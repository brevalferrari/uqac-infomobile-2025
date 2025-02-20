package com.ferhatozcelik.jetpackcomposetemplate.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ferhatozcelik.jetpackcomposetemplate.ui.create.CreateScreen
import com.ferhatozcelik.jetpackcomposetemplate.ui.create.CreateScreenState
import com.ferhatozcelik.jetpackcomposetemplate.ui.home.HomeViewModel
import com.ferhatozcelik.jetpackcomposetemplate.ui.home.MainScreen
import com.ferhatozcelik.jetpackcomposetemplate.ui.update.EditScreen
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController, startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen(
                navController = navController, viewModel = HomeViewModel()
            )
        }
        composable(Screen.Create.route) {
            val cal = Calendar.getInstance()
            val screenState = CreateScreenState(
                datePickerState = rememberDatePickerState(),
                timePickerState = TimePickerState(
                    cal.get(Calendar.HOUR),
                    cal.get(Calendar.MINUTE),
                    true
                )
            )
            CreateScreen(navController = navController, state = screenState)
        }
        composable("edit/{routineId}") { backStackEntry ->
            val routineId = backStackEntry.arguments?.getString("routineId")
            EditScreen(navController = navController, routineId = routineId)
        }
    }
}
