package com.example.ricegrow.fertilizer

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.ricegrow.R
import com.example.ricegrow.descriptions.FertilizerDescription

@Composable
fun FertTwo(navController: NavController) {
    FertilizerDescription(
        fertilizerName = "16-20-0 ",
        fertilizerDescription = "It used for and root & foliage development during the early stages of plant.",
        fertilizerImage = R.drawable.ferttwo ,
        usage = "• 25-30 days placed after transplanting \n" +
                "• 1.5-2 sacks(Optional)"
    )
}
