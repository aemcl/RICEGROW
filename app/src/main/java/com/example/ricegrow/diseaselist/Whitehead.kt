package com.example.ricegrow.disease


import androidx.compose.runtime.Composable
import com.example.ricegrow.R
import com.example.ricegrow.descriptions.DiseaseDescription

@Composable
fun DiseasesInfo() {
    DiseaseDescription(
        diseaseName = "",
        diseaseDescription = "",
        diseaseImage = R.drawable.d,
        treatment = ""
    )
}
