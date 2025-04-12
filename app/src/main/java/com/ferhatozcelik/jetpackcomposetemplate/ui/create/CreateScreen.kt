package com.ferhatozcelik.jetpackcomposetemplate.ui.create

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ferhatozcelik.jetpackcomposetemplate.R
import com.ferhatozcelik.jetpackcomposetemplate.navigation.Screen
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.Black
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.White
import com.ferhatozcelik.jetpackcomposetemplate.ui.update.RoutineCategoryDropdown
import com.ferhatozcelik.jetpackcomposetemplate.ui.update.RoutineDateTimePicker
import com.ferhatozcelik.jetpackcomposetemplate.ui.update.RoutinePeriodDropdown
import com.ferhatozcelik.jetpackcomposetemplate.ui.update.RoutinePriorityDropdown
import com.ferhatozcelik.jetpackcomposetemplate.ui.update.RoutineTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(
    viewModel: CreateViewModel = hiltViewModel(),
    state: CreateScreenState,
    navController: NavController
) {
    val context = LocalContext.current
    val robotoSerifFontFamily = FontFamily(
        Font(R.font.roboto),
        Font(R.font.robotoserif),
        Font(R.font.robotoserif_italic, style = FontStyle.Italic),
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            item {
                Text(
                    text = "Créer une routine",
                    fontFamily = robotoSerifFontFamily,
                    fontStyle = FontStyle.Italic,
                    fontSize = 54.sp,
                    lineHeight = 40.sp,
                    color = Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                RoutineTextField("Nom de la routine *", viewModel.routineName.value) {
                    viewModel.setName(it)
                }

                Spacer(modifier = Modifier.height(16.dp))

                RoutineTextField(
                    "Description de la routine *",
                    viewModel.routineDescription.value
                ) {
                    viewModel.setDescription(it)
                }

                Spacer(modifier = Modifier.height(16.dp))

                RoutineCategoryDropdown(
                    selectedCategory = viewModel.selectedCategory.value,
                    onSelect = { viewModel.setCategory(it) },
                    expanded = state.expanded,
                    fontFamily = robotoSerifFontFamily
                )

                Spacer(modifier = Modifier.height(16.dp))

                RoutineDateTimePicker(
                    label = "Date de début",
                    date = viewModel.selectedStartDate.value,
                    onDateClick = { state.showDatePicker1.value = true },
                    onTimeClick = { state.showTimePicker1.value = true },
                    datePickerVisible = state.showDatePicker1,
                    timePickerVisible = state.showTimePicker1,
                    datePickerState = state.datePickerState,
                    timePickerState = state.timePickerState,
                    onDateSelected = {
                        viewModel.setStartDate(
                            viewModel.selectedStartDate.value.withDayOfMonth(it.dayOfMonth)
                                .withMonth(it.monthValue).withYear(it.year)
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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = viewModel.hasEndDate.value,
                        onCheckedChange = { viewModel.setHasEndDate(it) }
                    )
                    Text("La routine a une date de fin")
                }

                if (viewModel.hasEndDate.value) {
                    Spacer(modifier = Modifier.height(16.dp))

                    RoutineDateTimePicker(
                        label = "Date de fin",
                        date = viewModel.selectedEndDate.value,
                        onDateClick = { state.showDatePicker2.value = true },
                        onTimeClick = { state.showTimePicker2.value = true },
                        datePickerVisible = state.showDatePicker2,
                        timePickerVisible = state.showTimePicker2,
                        datePickerState = state.datePickerState,
                        timePickerState = state.timePickerState,
                        onDateSelected = {
                            viewModel.setEndDate(
                                viewModel.selectedEndDate.value.withDayOfMonth(it.dayOfMonth)
                                    .withMonth(it.monthValue).withYear(it.year)
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
                }

                Spacer(modifier = Modifier.height(16.dp))

                RoutinePriorityDropdown(
                    selected = viewModel.selectedPriority.value,
                    onSelect = { viewModel.setPriority(it) },
                    expanded = state.priorityExpanded,
                    fontFamily = robotoSerifFontFamily
                )

                Spacer(modifier = Modifier.height(16.dp))

                RoutinePeriodDropdown(
                    selected = viewModel.selectedPeriod.value,
                    onSelect = { viewModel.setPeriod(it) },
                    expanded = state.periodExpanded,
                    fontFamily = robotoSerifFontFamily
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        viewModel.createRoutine(
                            onSuccess = { navController.navigate(Screen.Main.route) },
                            onError = { e ->
                                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Black)
                ) {
                    Text(
                        text = "Publier ma routine",
                        fontFamily = robotoSerifFontFamily,
                        color = White,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
            }
        }

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
