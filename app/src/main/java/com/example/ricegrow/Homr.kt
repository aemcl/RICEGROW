package com.example.ricegrow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController){

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Rice makes everything nice!",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraLight,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Monospace,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(100.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color(red = 0, green = 204, blue = 0)),
            modifier = Modifier
                .height(50.dp)
                .width(250.dp)
                .shadow(
                    elevation = 10.dp,
                    spotColor = Color.DarkGray,
                    shape = RoundedCornerShape(30.dp)
                ),
            onClick = {navController.navigate(Routes.mainScreen)}
        ){
            Text(text = "START", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(100.dp))

    }//Column

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.qm),
            contentDescription = "Help",
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.TopEnd)
                .padding(top = 50.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(Routes.help)
                }
        )
    }
   CameraIcon(navController)
}//fun Home