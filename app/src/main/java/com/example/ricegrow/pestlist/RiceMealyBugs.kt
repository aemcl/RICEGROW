package com.example.ricegrowII.pest

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.ricegrow.R
import com.example.ricegrow.descriptions.PestDescription

@Composable
fun RiceMealyBugs(navController: NavController){
    PestDescription(
        pestName = "Rice Mealy Bugs",
        pestDescription = "We can see white wax covered eggs, nymphs and adults on infected plants. " +
                "The insects are common in field with well drained soil. Both adult and nymphs feed on rice plant by sucking sap." +
                "The main symptoms are stunting and wilting of plants, yellowing and curling of leaves.",
        pestImage = R.drawable.ricemealybugs,
        management = "Augment biocontrol agents (like lady bird beetles, chloropid flies, spiders, small encyrtid wasps) in rice fields. "
    )
}