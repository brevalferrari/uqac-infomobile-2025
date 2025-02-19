package com.ferhatozcelik.jetpackcomposetemplate.ui.Update

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun EditScreen(navController: NavHostController, routineId: String?) {
    // Utilisez routineId pour charger et modifier la routine
    Text(text = "Éditer la routine : $routineId")
}