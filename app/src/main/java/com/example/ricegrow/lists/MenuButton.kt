package com.example.ricegrow.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MenuButton(
    name: String,
    navController: NavController,
    destinationRoute: String
) {

    Button(
        onClick = { navController.navigate(destinationRoute) },
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5F5B0)),
        modifier = Modifier
            .height(40.dp)
            .width(350.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Color.DarkGray,
                shape = RoundedCornerShape(30.dp)
            )
//            .border(
//                BorderStroke(1.dp, Color.LightGray),
//                shape = RoundedCornerShape(30.dp)
//            )
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = name, fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.Light)
        }
    }
}