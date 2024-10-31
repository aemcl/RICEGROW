package com.example.ricegrow.rice

import androidx.compose.runtime.Composable
import com.example.ricegrow.R
import com.example.ricegrow.descriptions.RiceDescription


@Composable

fun Kasolid() {
    RiceDescription(
        riceName = "Kasolid (11026)",
        riceDescription = "Kasolid (11026) is a variety of rice seed known for its high yield" +
                " and adaptability to various growing conditions. It's often used in breeding " +
                "programs due to its desirable traits, such as disease resistance and grain quality." ,
        riceImage = R.drawable.kasolid,
        plantingMonth = "May\nJune\nJuly\nAugust",
        harvestingMonth = "Around 3 to 4 Months (Between September & December)"
    )
}
