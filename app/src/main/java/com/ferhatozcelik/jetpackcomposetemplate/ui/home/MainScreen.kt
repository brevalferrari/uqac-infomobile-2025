package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ferhatozcelik.jetpackcomposetemplate.navigation.Screen
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.Black
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.Grey
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.White

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navController: NavController, viewModel: HomeViewModel
) {
    val robotoSerifFontFamily = FontFamily(
        Font(com.ferhatozcelik.jetpackcomposetemplate.R.font.roboto),
        Font(com.ferhatozcelik.jetpackcomposetemplate.R.font.robotoserif),
        Font(
            com.ferhatozcelik.jetpackcomposetemplate.R.font.robotoserif_italic,
            style = FontStyle.Italic
        ),
    )

    // Image d'arrière-plan
    Image(
        painter = painterResource(id = com.ferhatozcelik.jetpackcomposetemplate.R.drawable.backgroundhome), // Remplacez par l'ID de votre image d'arrière-plan
        contentDescription = "Background Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top, // Aligner les éléments en haut
            horizontalAlignment = Alignment.Start // Aligner les éléments à gauche
        ) {
            Text(
                text = "Routine",
                fontFamily = robotoSerifFontFamily,
                fontStyle = FontStyle.Italic,
                fontSize = 54.sp,
                color = Black,
            )
            Text(
                text = "(not poutine)",
                fontFamily = robotoSerifFontFamily,
                fontStyle = FontStyle.Italic,
                fontSize = 54.sp,
                color = Grey,
            )
            LazyColumn {
                items(viewModel.routines.value) { routine ->
                    RoutineCard(routine, navController, viewModel)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        // Bouton en bas à droite
        Button(
            onClick = {
                navController.navigate(Screen.Create.route)
            }, modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(56.dp), // Taille du bouton
            colors = ButtonDefaults.buttonColors(containerColor = Black)
        ) {
            Text(
                text = "+", color = White, // Couleur du texte
                fontSize = 25.sp, textAlign = TextAlign.Center
            )
        }

    }

}
