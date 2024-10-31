package com.example.ricegrow.lists

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
import com.example.ricegrow.MainTopBar
import com.example.ricegrow.Routes

@Composable
fun RiceList(navController: NavController){
    val riceList = listOf(
        "Black Rice" to Routes.blackrice,
        "Kasolid (11026)" to Routes.kasolid,
        "Pilit (11022)" to Routes.pilit,
        "Dinorado (11036)" to Routes.dinorado,
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
        riceList.forEach { (riceName, route) ->
            MenuButton(
                name = riceName,
                navController = navController,
                destinationRoute = route
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
    MainTopBar(pageTitle = "RICE")
}