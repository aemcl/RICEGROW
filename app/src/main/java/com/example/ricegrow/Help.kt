package com.example.ricegrow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Help(){

    val instructions = listOf(
        "Tap the 'Start' button to start",
        "Tap the 'Type of Rice' button to view the list of rice type, and select your rice variety to view its information",
        "Tap 'Fertilizer' button to see the best fertilizers and how to use them",
        "Tap the 'Pest' button to learn about common pests and how to manage them",
        "Tap the 'Diseases' button to learn about common diseases and how to treat them",
        "Tap the Camera (located on the top-right corner of the Pest and Disease page): Use the Camera to take pictures or upload an image of a pest and disease in your field"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEBDA98)),
            modifier = Modifier.size(width =320.dp, height = 500.dp)
        ) {
            Text(text = "How to Use the Rice Grow App:", fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 15.dp))
            Spacer(modifier = Modifier.height(10.dp))

            instructions.forEach{ item ->
                Text(text = "•   " +item, fontSize = 16.sp, modifier = Modifier.padding(start = 12.dp, end = 10.dp, bottom = 20.dp))
            }

        }
    }
}