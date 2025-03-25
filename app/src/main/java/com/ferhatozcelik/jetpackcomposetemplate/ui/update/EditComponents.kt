package com.ferhatozcelik.jetpackcomposetemplate.ui.update

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ferhatozcelik.jetpackcomposetemplate.R
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Category
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Priority
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.Black
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.Grey
import com.ferhatozcelik.jetpackcomposetemplate.util.DateUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter

@Composable
fun RoutineTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(
            text = label,
            fontStyle = FontStyle.Italic,
            fontSize = 12.sp,
            color = Black,
            modifier = Modifier.padding(start = 16.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text("...", color = Grey) },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Grey, RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                cursorColor = Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun RoutineCategoryDropdown(
    selectedCategory: Category?,
    onSelect: (Category) -> Unit,
    expanded: MutableState<Boolean>,
    fontFamily: FontFamily
) {
    Column {
        Text(
            text = "Catégorie *",
            fontStyle = FontStyle.Italic,
            fontSize = 12.sp,
            color = Black,
            modifier = Modifier.padding(start = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Grey, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) { detectTapGestures { expanded.value = !expanded.value } }
            ) {
                Text(
                    text = selectedCategory?.toString() ?: "Sélectionnez une catégorie",
                    fontFamily = fontFamily,
                    color = if (selectedCategory == null) Grey else Black,
                    modifier = Modifier.weight(1f).padding(8.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.arrowdown),
                    contentDescription = "ArrowDown",
                    modifier = Modifier.size(40.dp)
                )
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                Category.entries.forEach { category ->
                    DropdownMenuItem(
                        onClick = {
                            onSelect(category)
                            expanded.value = false
                        },
                        text = { Text(text = category.toString()) }
                    )
                }
            }
        }
    }
}

@Composable
fun RoutinePriorityDropdown(
    selected: Priority?,
    onSelect: (Priority) -> Unit,
    expanded: MutableState<Boolean>,
    fontFamily: FontFamily
) {
    Column {
        Text(
            text = "Importance *",
            fontStyle = FontStyle.Italic,
            fontSize = 12.sp,
            color = Black,
            modifier = Modifier.padding(start = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Grey, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) { detectTapGestures { expanded.value = !expanded.value } }
            ) {
                Text(
                    text = selected?.toString() ?: "Sélectionnez une importance",
                    fontFamily = fontFamily,
                    color = if (selected == null) Grey else Black,
                    modifier = Modifier.weight(1f).padding(8.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.arrowdown),
                    contentDescription = "ArrowDown",
                    modifier = Modifier.size(40.dp)
                )
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                Priority.entries.forEach { priority ->
                    DropdownMenuItem(
                        onClick = {
                            onSelect(priority)
                            expanded.value = false
                        },
                        text = { Text(text = priority.toString()) }
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RoutinePeriodDropdown(
    selected: Period?,
    onSelect: (Period?) -> Unit,
    expanded: MutableState<Boolean>,
    fontFamily: FontFamily
) {
    Column {
        Text(
            text = "Périodicité *",
            fontStyle = FontStyle.Italic,
            fontSize = 12.sp,
            color = Black,
            modifier = Modifier.padding(start = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Grey, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) { detectTapGestures { expanded.value = !expanded.value } }
            ) {
                Text(
                    text = when (selected) {
                        Period.ofDays(1) -> "Journalière"
                        Period.ofWeeks(1) -> "Hebdomadaire"
                        Period.ofMonths(1) -> "Mensuelle"
                        Period.ofYears(1) -> "Annuelle"
                        else -> "Aucune"
                    },
                    fontFamily = fontFamily,
                    color = Black,
                    modifier = Modifier.weight(1f).padding(8.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.arrowdown),
                    contentDescription = "ArrowDown",
                    modifier = Modifier.size(40.dp)
                )
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                DropdownMenuItem(onClick = {
                    onSelect(null)
                    expanded.value = false
                }, text = { Text("Aucune") })
                DropdownMenuItem(onClick = {
                    onSelect(Period.ofDays(1))
                    expanded.value = false
                }, text = { Text("Journalière") })
                DropdownMenuItem(onClick = {
                    onSelect(Period.ofWeeks(1))
                    expanded.value = false
                }, text = { Text("Hebdomadaire") })
                DropdownMenuItem(onClick = {
                    onSelect(Period.ofMonths(1))
                    expanded.value = false
                }, text = { Text("Mensuelle") })
                DropdownMenuItem(onClick = {
                    onSelect(Period.ofYears(1))
                    expanded.value = false
                }, text = { Text("Annuelle") })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RoutineDateTimePicker(
    label: String,
    date: LocalDateTime,
    onDateClick: () -> Unit,
    onTimeClick: () -> Unit,
    datePickerVisible: MutableState<Boolean>,
    timePickerVisible: MutableState<Boolean>,
    datePickerState: DatePickerState,
    timePickerState: TimePickerState,
    onDateSelected: (LocalDate) -> Unit,
    onTimeSelected: (TimePickerState) -> Unit,
    fontFamily: FontFamily
) {
    Column {
        Text(
            text = label,
            fontStyle = FontStyle.Italic,
            fontSize = 12.sp,
            color = Black,
            modifier = Modifier.padding(start = 16.dp)
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Grey, RoundedCornerShape(8.dp))
                    .padding(12.dp)
                    .pointerInput(Unit) { detectTapGestures { onDateClick() } }
            ) {
                Text(
                    text = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    fontFamily = fontFamily
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Grey, RoundedCornerShape(8.dp))
                    .padding(12.dp)
                    .pointerInput(Unit) { detectTapGestures { onTimeClick() } }
            ) {
                Text(
                    text = date.format(DateTimeFormatter.ofPattern("HH:mm")),
                    fontFamily = fontFamily
                )
            }
        }

        if (datePickerVisible.value) {
            DatePickerDialog(
                onDismissRequest = { datePickerVisible.value = false },
                confirmButton = {
                    TextButton(onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val localDate = java.time.Instant.ofEpochMilli(millis)
                                .atZone(java.time.ZoneId.systemDefault())
                                .toLocalDate()

                            onDateSelected(localDate)
                        }
                        datePickerVisible.value = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { datePickerVisible.value = false }) { Text("Annuler") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        if (timePickerVisible.value) {
            Dialog(onDismissRequest = { timePickerVisible.value = false }) {
                Column(modifier = Modifier.background(Color.White)) {
                    TimePicker(state = timePickerState)
                    TextButton(
                        onClick = {
                            onTimeSelected(timePickerState)
                            timePickerVisible.value = false
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}
