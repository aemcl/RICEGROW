package com.example.ricegrow.rice

import androidx.compose.runtime.Composable
import com.example.ricegrow.R
import com.example.ricegrow.descriptions.RiceDescription

@Composable
fun Dinorado(){
   RiceDescription(
       riceName = "Dinorado",
       riceDescription =
       "Dinorado is another rice variety known for its aromatic qualities and high-quality grains. " +
       "It is often sought after for its excellent cooking characteristics and flavor, making it a popular choice in culinary applications.",

       riceImage = R.drawable.dinorado,
       plantingMonth = "May\nJune\nJuly" ,
       harvestingMonth = "About 3 to 4 months (Between September and December"
   )
}