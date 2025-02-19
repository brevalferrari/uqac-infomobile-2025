package com.ferhatozcelik.jetpackcomposetemplate.ui.create

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
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
import androidx.navigation.NavController
import com.ferhatozcelik.jetpackcomposetemplate.R
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Category
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Priority
import com.ferhatozcelik.jetpackcomposetemplate.navigation.Screen
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.Black
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.Grey
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.White
import java.time.Period

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateScreen(
    viewModel: CreateViewModel = hiltViewModel(),
    state: CreateScreenState,
    navController: NavController,
) {
    val robotoSerifFontFamily = FontFamily(
        Font(R.font.roboto),
        Font(R.font.robotoserif),
        Font(R.font.robotoserif_italic, style = FontStyle.Italic),
    )
    val context = LocalContext.current


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            item {
                // Title
                Text(
                    text = "Créer une routine",
                    fontFamily = robotoSerifFontFamily,
                    fontStyle = FontStyle.Italic,
                    fontSize = 54.sp,
                    lineHeight = 40.sp,
                    color = Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Routine Name
                Column {
                    Text(
                        text = "Nom de la routine *",
                        fontFamily = robotoSerifFontFamily,
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        color = Black,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    TextField(
                        value = viewModel.routineName.value,
                        onValueChange = { viewModel.setName(it) },
                        placeholder = {
                            Text(
                                "Un nom de routine random",
                                color = Grey,
                                fontFamily = robotoSerifFontFamily
                            )
                        },
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

                Spacer(modifier = Modifier.height(16.dp))

                // Routine Description
                Column {
                    Text(
                        text = "Description de la routine *",
                        fontFamily = robotoSerifFontFamily,
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        color = Black,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    TextField(
                        value = viewModel.routineDescription.value,
                        onValueChange = { viewModel.setDescription(it) },
                        placeholder = {
                            Text(
                                "Une description plus ou moins longue",
                                fontFamily = robotoSerifFontFamily,
                                color = Grey
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .border(1.dp, Grey, RoundedCornerShape(8.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            cursorColor = Grey,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Routine Category
                Column {
                    Text(
                        text = "Catégorie de la routine *",
                        fontFamily = robotoSerifFontFamily,
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
                                .pointerInput(Unit) {
                                    detectTapGestures {
                                        state.expanded.value = !state.expanded.value
                                    }
                                }
                        ) {
                            Text(
                                text = viewModel.selectedCategory.value?.toString()
                                    ?: "Sélectionnez une catégorie",
                                fontFamily = robotoSerifFontFamily,
                                color = if (viewModel.selectedCategory.value == null) Grey else Black,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.arrowdown),
                                contentDescription = "ArrowDown",
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = state.expanded.value,
                            onDismissRequest = { state.expanded.value = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Category.entries.forEach { category ->
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.setCategory(category)
                                        state.expanded.value = false
                                    },
                                    text = { Text(text = category.toString()) }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Date + Heures
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Routine Date
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Date *",
                            fontFamily = robotoSerifFontFamily,
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
                                    .pointerInput(Unit) {
                                        detectTapGestures {
                                            // Action pour ouvrir le sélecteur de date
                                        }
                                    }
                            ) {
                                Text(
                                    text = "00/00/00",
                                    fontFamily = robotoSerifFontFamily,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.calendar),
                                    contentDescription = "Calendar Icon",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .pointerInput(Unit) {
                                            detectTapGestures {
                                                // Action pour ouvrir le sélecteur de date
                                            }
                                        }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.size(16.dp)) // Espace entre date et heure

                    // Heures
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Heures *",
                            fontFamily = robotoSerifFontFamily,
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
                                    .pointerInput(Unit) {
                                        detectTapGestures {
                                            // Action pour ouvrir le sélecteur d'heure
                                        }
                                    }
                            ) {
                                Text(
                                    text = "00:00 PM",
                                    fontFamily = robotoSerifFontFamily,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.clock),
                                    contentDescription = "Clock Icon",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .pointerInput(Unit) {
                                            detectTapGestures {
                                                // Action pour ouvrir le sélecteur d'heure
                                            }
                                        }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Priority
                Column {
                    Text(
                        text = "Importance *",
                        fontFamily = robotoSerifFontFamily,
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
                                .pointerInput(Unit) {
                                    detectTapGestures {
                                        state.priorityExpanded.value = !state.priorityExpanded.value
                                    }
                                }
                        ) {
                            Text(
                                text = viewModel.selectedPriority.value?.toString()
                                    ?: "Sélectionnez une importance",
                                fontFamily = robotoSerifFontFamily,
                                color = if (viewModel.selectedPriority.value == null) Grey else Black,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.arrowdown),
                                contentDescription = "ArrowDown",
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = state.priorityExpanded.value,
                            onDismissRequest = { state.priorityExpanded.value = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Priority.entries.forEach { priority ->
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.setPriority(priority)
                                        state.priorityExpanded.value = false
                                    },
                                    text = { Text(text = priority.toString()) }
                                )
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                // Périodicité
                Column {
                    Text(
                        text = "Périodicité *",
                        fontFamily = robotoSerifFontFamily,
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
                                .pointerInput(Unit) {
                                    detectTapGestures {
                                        state.periodExpanded.value = !state.periodExpanded.value
                                    }
                                }
                        ) {
                            Text(
                                text = viewModel.selectedPeriod.value?.toString()
                                    ?: "Sélectionnez une périodicité",
                                fontFamily = robotoSerifFontFamily,
                                color = if (viewModel.selectedPeriod.value == null) Grey else Black,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.arrowdown),
                                contentDescription = "ArrowDown",
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = state.periodExpanded.value,
                            onDismissRequest = { state.periodExpanded.value = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.setPeriod(Period.ofDays(1))
                                    state.periodExpanded.value = false
                                },
                                text = { Text(text = "Journalière") }
                            )
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.setPeriod(Period.ofWeeks(1))
                                    state.periodExpanded.value = false
                                },
                                text = { Text(text = "Hebdomadaire") }
                            )
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.setPeriod(Period.ofMonths(1))
                                    state.periodExpanded.value = false
                                },
                                text = { Text(text = "Mensuelle") }
                            )
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.setPeriod(Period.ofYears(1))
                                    state.periodExpanded.value = false
                                },
                                text = { Text(text = "Annuelle") }
                            )
                        }
                    }
                }
            }
        }

        // Close Button
        Button(
            onClick = {
                navController.navigate(Screen.Main.route)
            },
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

        // Bouton Publier ma routine
        Button(
            onClick = {
                try {
                    viewModel.createRoutine()
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter) // S'assure que le bouton est bien en bas
                .padding(16.dp)
                .fillMaxWidth(),
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

