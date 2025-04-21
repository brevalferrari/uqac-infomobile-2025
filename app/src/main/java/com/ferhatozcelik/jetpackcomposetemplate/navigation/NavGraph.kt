package com.ferhatozcelik.jetpackcomposetemplate.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ferhatozcelik.jetpackcomposetemplate.ui.create.CreateScreen
import com.ferhatozcelik.jetpackcomposetemplate.ui.create.CreateScreenState
import com.ferhatozcelik.jetpackcomposetemplate.ui.home.HomeViewModel
import com.ferhatozcelik.jetpackcomposetemplate.ui.home.MainScreen
import com.ferhatozcelik.jetpackcomposetemplate.ui.update.EditScreen
import com.ferhatozcelik.jetpackcomposetemplate.ui.update.EditScreenState
import java.util.Calendar
import java.util.UUID
import android.content.Context

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(navController: NavHostController, activityContext: Context) {

    NavHost(
        navController = navController, startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            MainScreen(
                navController = navController,
                viewModel = viewModel,
                activityContext = activityContext
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
            val routineIdString = backStackEntry.arguments?.getString("routineId")
            val routineId = UUID.fromString(routineIdString) // Convert String to UUID

            val cal = Calendar.getInstance()
            EditScreen(
                navController = navController,
                routineId = routineId, // Pass the UUID to EditScreen
                state = EditScreenState(
                    datePickerState = rememberDatePickerState(),
                    timePickerState = TimePickerState(
                        cal.get(Calendar.HOUR),
                        cal.get(Calendar.MINUTE),
                        true
                    )
                )
            )
        }

    }
}
