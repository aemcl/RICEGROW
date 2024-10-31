package com.example.ricegrow.rice

import androidx.compose.runtime.Composable
import com.example.ricegrow.R
import com.example.ricegrow.descriptions.RiceDescription


@Composable
fun Pilit(){
  RiceDescription(
      riceName = "Pilit (11022)",
      riceDescription = "Pilit (11022) is a rice variety known for its high-quality grains " +
              "and good cooking properties. It's often cultivated for its resilience to various " +
              "environmental stresses and is favored in regions where specific taste and texture are desired.",
      riceImage = R.drawable.pilit,
      plantingMonth = "May\nJune\nJuly\nAugust" ,
      harvestingMonth = "Around 3 to 4 Months (Between September & December)"
  )
}
