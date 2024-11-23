package com.example.ricegrow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Help {
    @Composable
    fun ShowHelp(){

        val instructions = listOf(
            "Start the App\n" +
                    "Tap the Start button on the home screen to begin.",
            "View Rice, Fertilizer, Pest, and Disease Pages\n" +
                    "After tapping Start, you will see four main options: Rice, Fertilizer, Pest, and Disease.\n" +
                    "Tap on any of these options to explore related information.",
            "Using the Camera for Disease Identification\n" +
                    "Tap the Plus icon on the to identify diseases.\n" +
                    "Choose either Take Picture or Upload Image.\n" +
                    "The app will analyze the image and display the identified disease and you may view the full details.",
            "Browsing Rice Varieties\n" +
                    "In the Rice section, you will see different types of rice \n" +
                    "Tap on any rice variety to see detailed information like planting and harvesting months.",
            "Selecting Fertilizers\n" +
                    "In the Fertilizer section, browse through various fertilizer options.\n" +
                    "Tap on a fertilizer type to view details about its usage and benefits.",
            "Identifying Pests\n" +
                    "Go to the Pest section to see common rice pests.\n" +
                    "Select a pest to learn about it, including symptoms and management tips.",
            "Understanding Diseases\n" +
                    "In the Disease section, find common rice diseases.\n" +
                    "Tap a disease to see details, including causes and ways to manage it.",
            "Navigating Back\n" +
                    "Use the Back icon at the top or bottom to return to the previous screen.",
            "Home Navigation\n" +
                    "Access the main screen at any time by tapping the Home icon at the top.\n"
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
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = "How to Use the Rice Grow App:", textAlign = TextAlign.Center,fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 15.dp))
                Spacer(modifier = Modifier.height(10.dp))

                instructions.forEach{ item ->
                    Text(text = "•   $item", fontSize = 16.sp, modifier = Modifier.padding(start = 12.dp, end = 10.dp, bottom = 20.dp))
                }

            }
        }
    }
}