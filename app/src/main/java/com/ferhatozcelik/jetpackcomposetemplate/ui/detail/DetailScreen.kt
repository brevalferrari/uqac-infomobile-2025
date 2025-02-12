package com.ferhatozcelik.jetpackcomposetemplate.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navController: NavController,
    id: Int
) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = id.toString())

    }

}



