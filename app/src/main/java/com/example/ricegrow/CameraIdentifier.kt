package com.example.ricegrow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CameraIdentifier(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {navController.navigate(Routes.listfertilizer)},
            colors = ButtonDefaults.buttonColors(containerColor = Color(red = 0, green = 204, blue = 0)),
            modifier = Modifier
                .height(50.dp)
                .width(250.dp)
                .shadow(
                    elevation = 10.dp,
                    spotColor = Color.DarkGray,
                    shape = RoundedCornerShape(30.dp)
                )
        ) {
            Text(text = "Take Picture",fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {navController.navigate(Routes.listfertilizer)},
            colors = ButtonDefaults.buttonColors(containerColor = Color(red = 0, green = 204, blue = 0)),
            modifier = Modifier
                .height(50.dp)
                .width(250.dp).shadow(
                    elevation = 10.dp,
                    spotColor = Color.DarkGray,
                    shape = RoundedCornerShape(30.dp)
                )
        ) {
            Text(text = "Upload Image",fontSize = 20.sp)
        }

    }
}