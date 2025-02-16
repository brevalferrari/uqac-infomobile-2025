package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine

@Composable
fun RoutineCard(
    routine: Routine
) {
    Text(text = routine.name)
}
