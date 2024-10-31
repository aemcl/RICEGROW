package com.example.ricegrow.menubuttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.ricegrow.lists.MenuButton

@Composable
fun ListOfFertilizer(navController: NavController) {

    val fertilizerList = listOf(
        "14-14-14" to Routes.fertOne,
        "16-20-0" to Routes.fertTwo,
        "46-0-0 + 0-0-60" to Routes.fertThree
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
        fertilizerList.forEach { (item, route) ->
            MenuButton(
                name = item,
                navController = navController,
                destinationRoute = route
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
    MainTopBar(pageTitle = "FERTILIZER")
}


