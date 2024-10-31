package com.example.ricegrow

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.CardDefaults

@Composable
fun ClassifyScreen(modifier: Modifier = Modifier, navController: NavController) {
    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }
    var resultText by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        capturedImage = bitmap
        // Placeholder logic for classification
        resultText = classifyImage(bitmap)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color(0xFF00C4FF)), // Blue background color
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Capture Your Image",
            fontSize = 24.sp,
            color = Color.Black // Black text color
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
            shape = androidx.compose.material3.MaterialTheme.shapes.medium
        ) {
            capturedImage?.let { image ->
                Image(
                    bitmap = image.asImageBitmap(),
                    contentDescription = "Captured Image",
                    modifier = Modifier.fillMaxSize()
                )
            } ?: run {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No image captured yet", fontSize = 18.sp, color = Color.Black) // Black text color
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to launch the camera
        Button(onClick = { launcher.launch(null) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Capture Image", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display result text
        Text(
            text = resultText,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp),
            color = Color.Black // Black text color
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to navigate back to the home page
        Button(onClick = { navController.navigate("home") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Back to Home", color = Color.White)
        }
    }
}

// Placeholder function for image classification
fun classifyImage(bitmap: Bitmap?): String {
    // Return a placeholder message for now
    return if (bitmap != null) {
        "You're fat! Consider a balanced diet and regular exercise."
    } else {
        "Normal. Keep up the good work!"
    }
}