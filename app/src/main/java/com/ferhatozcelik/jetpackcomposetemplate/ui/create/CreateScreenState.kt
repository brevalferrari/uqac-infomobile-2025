package com.ferhatozcelik.jetpackcomposetemplate.ui.create

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class CreateScreenState(
    val expanded: MutableState<Boolean> = mutableStateOf(false),
    val priorityExpanded: MutableState<Boolean> = mutableStateOf(false),
    val periodExpanded: MutableState<Boolean> = mutableStateOf(false),
    val showDatePicker: MutableState<Boolean> = mutableStateOf(false),
)
