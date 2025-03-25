package com.ferhatozcelik.jetpackcomposetemplate.ui.update

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ferhatozcelik.jetpackcomposetemplate.R
import com.ferhatozcelik.jetpackcomposetemplate.navigation.Screen
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.Black
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.White
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditScreen(
    navController: NavHostController,
    routineId: UUID,
    viewModel: EditViewModel = hiltViewModel(),
    state: EditScreenState
) {
    val context = LocalContext.current
    val robotoSerifFontFamily = FontFamily(
        Font(R.font.roboto),
        Font(R.font.robotoserif),
        Font(R.font.robotoserif_italic, style = FontStyle.Italic),
    )

    // Charger la routine depuis Room
    LaunchedEffect(routineId) {
        viewModel.loadRoutineById(routineId)
    }

    viewModel.routine.value?.let { routine ->
        Box(modifier = Modifier.fillMaxSize().padding(10.dp)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    Text(
                        text = "Modifier une routine",
                        fontFamily = robotoSerifFontFamily,
                        fontStyle = FontStyle.Italic,
                        fontSize = 54.sp,
                        lineHeight = 40.sp,
                        color = Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Nom de la routine
                    RoutineTextField("Nom de la routine *", viewModel.routineName.value) {
                        viewModel.setName(it)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Description
                    RoutineTextField("Description de la routine *", viewModel.routineDescription.value) {
                        viewModel.setDescription(it)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Catégorie
                    RoutineCategoryDropdown(
                        selectedCategory = viewModel.selectedCategory.value,
                        onSelect = viewModel::setCategory,
                        expanded = state.expanded,
                        fontFamily = robotoSerifFontFamily
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Date & Heure de début
                    RoutineDateTimePicker(
                        label = "Date *",
                        date = viewModel.selectedStartDate.value,
                        onDateClick = { state.showDatePicker1.value = true },
                        onTimeClick = { state.showTimePicker1.value = true },
                        datePickerVisible = state.showDatePicker1,
                        timePickerVisible = state.showTimePicker1,
                        datePickerState = state.datePickerState,
                        timePickerState = state.timePickerState,
                        onDateSelected = { newDate ->
                            viewModel.setStartDate(
                                viewModel.selectedStartDate.value.withYear(newDate.year)
                                    .withMonth(newDate.month.value)
                                    .withDayOfMonth(newDate.dayOfMonth)
                            )
                        },
                        onTimeSelected = {
                            viewModel.setStartDate(
                                viewModel.selectedStartDate.value.withHour(it.hour)
                                    .withMinute(it.minute)
                            )
                        },
                        fontFamily = robotoSerifFontFamily
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Checkbox fin
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = viewModel.hasEndDate.value,
                            onCheckedChange = viewModel::setHasEndDate
                        )
                        Text("La routine a une date de fin")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Date & heure de fin
                    if (viewModel.hasEndDate.value) {
                        RoutineDateTimePicker(
                            label = "Date de fin *",
                            date = viewModel.selectedEndDate.value,
                            onDateClick = { state.showDatePicker2.value = true },
                            onTimeClick = { state.showTimePicker2.value = true },
                            datePickerVisible = state.showDatePicker2,
                            timePickerVisible = state.showTimePicker2,
                            datePickerState = state.datePickerState,
                            timePickerState = state.timePickerState,
                            onDateSelected = { newDate ->
                                viewModel.setEndDate(
                                    viewModel.selectedEndDate.value.withYear(newDate.year)
                                        .withMonth(newDate.month.value)
                                        .withDayOfMonth(newDate.dayOfMonth)
                                )
                            },
                            onTimeSelected = {
                                viewModel.setEndDate(
                                    viewModel.selectedEndDate.value.withHour(it.hour)
                                        .withMinute(it.minute)
                                )
                            },
                            fontFamily = robotoSerifFontFamily
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Importance
                    RoutinePriorityDropdown(
                        selected = viewModel.selectedPriority.value,
                        onSelect = viewModel::setPriority,
                        expanded = state.priorityExpanded,
                        fontFamily = robotoSerifFontFamily
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Périodicité
                    RoutinePeriodDropdown(
                        selected = viewModel.selectedPeriod.value,
                        onSelect = viewModel::setPeriod,
                        expanded = state.periodExpanded,
                        fontFamily = robotoSerifFontFamily
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Bouton enregistrer
                    Button(
                        onClick = {
                            try {
                                viewModel.updateRoutine(routine)
                                navController.navigate(Screen.Main.route)
                            } catch (e: Exception) {
                                Toast.makeText(context, e.message ?: "Erreur", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Black)
                    ) {
                        Text(
                            text = "Mettre à jour la routine",
                            fontFamily = robotoSerifFontFamily,
                            color = White,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                }
            }

            // Bouton retour
            Button(
                onClick = { navController.navigate(Screen.Main.route) },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Black)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = "Back Home",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.size(140.dp)
                )
            }
        }
    }
}
