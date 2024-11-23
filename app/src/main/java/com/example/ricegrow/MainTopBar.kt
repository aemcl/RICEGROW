@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.ricegrow

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainTopBar(icon: ImageVector, pageTitle: String, iconRoute: String, navController: NavController){
    CenterAlignedTopAppBar(
        modifier = Modifier.shadow(
            elevation = 10.dp,
            spotColor = Color.DarkGray
        ),
        title = {
            Text(text = pageTitle,
                fontSize = 40.sp,
                color = Color(0xFF2b2b2b),
                fontWeight = FontWeight.ExtraBold)
                },
        navigationIcon = {
            IconButton(onClick = {
                if (navController.previousBackStackEntry != null) {
                    // Navigate back if there's a previous screen
                    navController.popBackStack()
                } else {
                    // Optional: Navigate to the home screen or another safe fallback
                    navController.navigate("home") {
                        // Clear backstack to avoid redundant navigation
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            })
            {
                Icon(
                    imageVector = icon,//Icons.Filled.Home,
                    modifier = Modifier.size(50.dp),
                    //imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint =  Color(0xFF2b2b2b)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFFEBDA98))

    )
}