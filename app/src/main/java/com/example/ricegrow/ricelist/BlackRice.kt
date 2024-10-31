package com.example.ricegrow.rice

import androidx.compose.runtime.Composable
import com.example.ricegrow.R
import com.example.ricegrow.descriptions.RiceDescription


@Composable

fun BlackRice() {
    RiceDescription(
        riceName = "Black Rice",
        riceDescription = "Black rice or forbidden rice is a whole grain rice variety known " +
                "for its deep color and high nutritional value. It's rich in antioxidants " +
                "particularly anthocyanins, and has a slightly sweet, nutty flavor.",
        riceImage = R.drawable.blackrice ,
        plantingMonth = "May\nJune\nJuly\n"  ,
        harvestingMonth = "Around 3 to 4 months (Between September and December)"
    )
}
