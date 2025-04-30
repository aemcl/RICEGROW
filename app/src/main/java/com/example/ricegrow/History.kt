package com.example.ricegrow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable

fun History(navController: NavController){
    val historyList = listOf(
        HistoryCardItem(R.drawable.brownspot, "h1",  "20250315001248" , Routes.brownspot),
        HistoryCardItem(R.drawable.bacterialblight, "h2", "2025031800108" , Routes.riceBacterialblight),
        HistoryCardItem(R.drawable.tungro, "h3", "20250320001018" , Routes.tungro),
        HistoryCardItem(R.drawable.riceblast, "h4", "20250330001348" , Routes.riceblast)
    )
    Column {
        MainTopBar(
            icon = Icons.Filled.Home,
            pageTitle = "History",
            iconRoute = Routes.home,
            action_icon = Icons.Filled.Delete,
            actionTitle = "delete",
            actionRoute = "",
            navController = navController
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp)
                .padding(bottom = 10.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1), // 2 cards per row
                contentPadding = PaddingValues(5.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(historyList) { history ->
                    HistoryListCard(
                        painter = painterResource(id = history.painter), // Fix: Using painterResource for drawable IDs
                        contentDescription = history.description,
                        historyname = history.historyname,
                        navController = navController,
                        destinationRoute = history.route
                    )
                }
            }
        }
    }
}

@Composable
fun HistoryListCard(
    painter: Painter,
    contentDescription: String,
    historyname: String,
    navController: NavController,
    destinationRoute: String
) {
    Card(
        onClick = { navController.navigate(destinationRoute) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Box(modifier = Modifier.height(50.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0xFF504F4F)
                            ),
                            startY = 20f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    historyname,
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
            }
        }
    }
}

// Data class for card items
data class HistoryCardItem(
    val painter: Int,
    val description: String,
    val historyname: String,
    val route: String
)