package com.ferhatozcelik.jetpackcomposetemplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ferhatozcelik.jetpackcomposetemplate.ui.create.CreateScreen
import com.ferhatozcelik.jetpackcomposetemplate.ui.home.MainScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen(
                navController = navController
            )
        }
        composable(Screen.Create.route) {
            CreateScreen(navController = navController)
        }
    }
}
