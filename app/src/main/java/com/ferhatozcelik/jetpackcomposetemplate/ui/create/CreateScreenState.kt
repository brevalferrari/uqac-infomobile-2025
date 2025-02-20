package com.ferhatozcelik.jetpackcomposetemplate.ui.create

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class CreateScreenState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val expanded: MutableState<Boolean> = mutableStateOf(false),
    val priorityExpanded: MutableState<Boolean> = mutableStateOf(false),
    val periodExpanded: MutableState<Boolean> = mutableStateOf(false),
    val showDatePicker1: MutableState<Boolean> = mutableStateOf(false),
    val showTimePicker1: MutableState<Boolean> = mutableStateOf(false),
    val showDatePicker2: MutableState<Boolean> = mutableStateOf(false),
    val showTimePicker2: MutableState<Boolean> = mutableStateOf(false),
    val datePickerState: DatePickerState,
    val timePickerState: TimePickerState
)
