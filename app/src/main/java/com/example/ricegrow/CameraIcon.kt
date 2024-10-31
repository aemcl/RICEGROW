package com.example.ricegrow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CameraIcon(navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Icon(
            imageVector = Icons.Default.PhotoCamera,
            contentDescription = "Camera",
            tint = Color.Black,
            modifier = Modifier
                .size(200.dp)
                .clickable { navController.navigate(Routes.camera) }
                .align(Alignment.BottomCenter)
                .padding(start = 50.dp, bottom = 150.dp, end = 30.dp)
                .shadow(
                    elevation = 10.dp,
                    spotColor = Color.DarkGray,
                    shape = RoundedCornerShape(30.dp)
        )
        )
    }
}