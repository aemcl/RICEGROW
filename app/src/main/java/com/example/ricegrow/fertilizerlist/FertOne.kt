package com.example.ricegrow.fertilizer

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.ricegrow.R
import com.example.ricegrow.descriptions.FertilizerDescription

@Composable
fun FertOne(navController: NavController){
    FertilizerDescription(
        fertilizerName = "14-14-14",
        fertilizerDescription = "Triple 14 Complete Fertilizer (Harvester) also called 14-14-14 has " +
                "equal percentage of Nitrogen, phosphate, and potassium that will help crops carry out to " +
                "their full cycle that can last up to 3 months.",
        fertilizerImage = R.drawable.fertone,
        usage = "• It will be placed during 0-14 days\n" +
                "• 3-4 sacks",
    )
}