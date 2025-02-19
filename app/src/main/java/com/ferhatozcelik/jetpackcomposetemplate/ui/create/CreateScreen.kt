package com.ferhatozcelik.jetpackcomposetemplate.ui.create

import android.widget.DatePicker
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
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ferhatozcelik.jetpackcomposetemplate.navigation.Screen
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.Black
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.Grey
import com.ferhatozcelik.jetpackcomposetemplate.R
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.White
import java.util.Calendar

@Composable
fun CreateScreen(
    viewModel: CreateViewModel = hiltViewModel(),
    navController: NavController,
) {
    val robotoSerifFontFamily = FontFamily(
        Font(R.font.roboto),
        Font(R.font.robotoserif),
        Font(R.font.robotoserif_italic, style = FontStyle.Italic),
    )

    val categoryOptions = listOf("Sports", "Hygiène", "Travail", "Autres")
    val selectedCategory = remember { mutableStateOf<String?>(null) }

    val importanceOptions = listOf("Haute", "Moyenne", "Faible")
    val selectedImportance = remember { mutableStateOf<String?>(null) }
    val importanceExpanded = remember { mutableStateOf(false) }

    val expanded = remember { mutableStateOf(false) }
    val routineName = remember { mutableStateOf("") }
    val routineDescription = remember { mutableStateOf("") }
    val selectedDate = remember { mutableStateOf("Sélectionnez une date") }
    val showDatePicker = remember { mutableStateOf(false) }

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
                        value = routineName.value,
                        onValueChange = { routineName.value = it },
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
                        value = routineDescription.value,
                        onValueChange = { routineDescription.value = it },
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
                                        expanded.value = !expanded.value
                                    }
                                }
                        ) {
                            Text(
                                text = selectedCategory.value ?: "Sélectionnez une catégorie",
                                fontFamily = robotoSerifFontFamily,
                                color = if (selectedCategory.value == null) Grey else Black,
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
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            categoryOptions.forEach { category ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedCategory.value = category
                                        expanded.value = false
                                    },
                                    text = { Text(text = category) }
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

                // Importance
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
                                        importanceExpanded.value = !importanceExpanded.value
                                    }
                                }
                        ) {
                            Text(
                                text = selectedImportance.value ?: "Sélectionnez une importance",
                                fontFamily = robotoSerifFontFamily,
                                color = if (selectedImportance.value == null) Grey else Black,
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
                            expanded = importanceExpanded.value,
                            onDismissRequest = { importanceExpanded.value = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            importanceOptions.forEach { importance ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedImportance.value = importance
                                        importanceExpanded.value = false
                                    },
                                    text = { Text(text = importance) }
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
                    TextField(
                        value = routineName.value,
                        onValueChange = { routineName.value = it },
                        placeholder = {
                            Text(
                                "Tous les...",
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
                // Action pour publier la routine
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

