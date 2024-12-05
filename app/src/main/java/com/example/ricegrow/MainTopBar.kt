@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.ricegrow

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
            spotColor = MaterialTheme.colorScheme.onSurface
        ),
        title = {
            Text(text = pageTitle,
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.ExtraBold)
                },
        navigationIcon = {
            IconButton(onClick = {
                if (navController.previousBackStackEntry != null) {
                    // Navigate back if there's a previous screen
                    navController.popBackStack()
                } else {
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
                    imageVector = icon,
                    modifier = Modifier.size(50.dp),
                    contentDescription = "Back",
                    tint =  MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)

    )
}