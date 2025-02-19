package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.Black
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.Grey
import androidx.compose.foundation.clickable
import androidx.navigation.NavController

@Composable
fun RoutineCard(
    routine: Routine,
    navController: NavController
) {
    val robotoSerifFontFamily = FontFamily(
        Font(com.ferhatozcelik.jetpackcomposetemplate.R.font.roboto),
        Font(com.ferhatozcelik.jetpackcomposetemplate.R.font.robotoserif),
        Font(
            com.ferhatozcelik.jetpackcomposetemplate.R.font.robotoserif_italic,
            style = FontStyle.Italic
        ),
    )

    // Utilisation d'une carte pour encapsuler les informations de la routine
    Card(
        modifier = Modifier.padding(top = 16.dp, start = 0.dp, end = 0.dp, bottom = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                /* .verticalScroll(rememberScrollState()) */
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = routine.name,
                    fontFamily = robotoSerifFontFamily,
                    fontSize = 26.sp,
                    color = Black
                )

                Row {

                    Image(
                        painter = painterResource(id = com.ferhatozcelik.jetpackcomposetemplate.R.drawable.edit),
                        contentDescription = "Edit",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                navController.navigate("edit/${routine.id}")
                            }

                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Image(
                        painter = painterResource(id = com.ferhatozcelik.jetpackcomposetemplate.R.drawable.delete),
                        contentDescription = "Delete",
                        modifier = Modifier.size(24.dp)
                    )

                }
            }

            // Description de la routine
            Text(
                text = routine.description ?: "Aucune description disponible",
                fontFamily = robotoSerifFontFamily,
                fontSize = 18.sp,
                color = Black,
                modifier = Modifier
                    .padding(top = 8.dp) // Ajouter un espace entre le nom et la description
                    .fillMaxWidth(0.7f),
            )


            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Catégorie de la routine
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 16.dp)
                ) {
                    Column {
                        Text(
                            text = "Catégorie",
                            fontFamily = robotoSerifFontFamily,
                            fontStyle = FontStyle.Italic,
                            fontSize = 14.sp,
                            color = Grey
                        )

                        Text(
                            text = "${routine.category}",
                            fontFamily = robotoSerifFontFamily,
                            fontSize = 16.sp,
                            color = Black,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(0.8f)) // 70% of the total width as space

                // Périodicité
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 16.dp)
                ) {
                    Column {
                        Text(
                            text = "Périodicité:",
                            fontFamily = robotoSerifFontFamily,
                            fontStyle = FontStyle.Italic,
                            fontSize = 14.sp,
                            color = Grey
                        )

                        Text(
                            text = "${routine.period}",
                            fontFamily = robotoSerifFontFamily,
                            fontSize = 16.sp,
                            color = Black,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Horaires
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .weight(1f)
                ) {
                    Column {
                        Text(
                            text = "Horaires:",
                            fontFamily = robotoSerifFontFamily,
                            fontStyle = FontStyle.Italic,
                            fontSize = 14.sp,
                            color = Grey
                        )

                        Text(
                            text = "De: ${routine.startTime ?: "Non défini"} à ${routine.endTime ?: "Non défini"}",
                            fontFamily = robotoSerifFontFamily,
                            fontSize = 16.sp,
                            color = Black,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .fillMaxWidth(1.0f),
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(0.7f)) // 70% of the total width as space

                // Importance
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 16.dp)
                ) {
                    Column {
                        Text(
                            text = "Importance",
                            fontFamily = robotoSerifFontFamily,
                            fontStyle = FontStyle.Italic,
                            fontSize = 14.sp,
                            color = Grey
                        )

                        Text(
                            text = routine.priority.name,
                            fontFamily = robotoSerifFontFamily,
                            fontSize = 16.sp,
                            color = Black
                        )
                    }
                }
            }

        }
    }
}
