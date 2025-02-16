package com.ferhatozcelik.jetpackcomposetemplate.ui.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ferhatozcelik.jetpackcomposetemplate.navigation.Screen


@Composable
fun CreateScreen(
    viewModel: CreateViewModel = hiltViewModel(),
    navController: NavController,
) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bonjour")
        Button(onClick = { navController.navigate(Screen.Main.route) }) {
            Text(text = "Retourner à la maison")
        }
    }

}



