package com.example.ricegrow.menubuttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ricegrow.CameraIcon
import com.example.ricegrow.MainTopBar
import com.example.ricegrow.Routes
import com.example.ricegrow.lists.MenuButton

@Composable
fun ListOfDisease(navController: NavController) {

    val diseaseList = listOf(
        "Whitehead" to Routes.whitehead,
        "Bacterial leaf streak" to Routes.streak,
        "Bacterial blight infected leaf" to Routes.blight,
        "Bakanae" to Routes.bakanae,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC))
            .verticalScroll(rememberScrollState())
            .padding(top = 50.dp, bottom = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        diseaseList.forEach { (diseaseName, route) ->
            MenuButton(
                name = diseaseName,
                navController = navController,
                destinationRoute = route
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
    MainTopBar(pageTitle = "DISEASE")
}
