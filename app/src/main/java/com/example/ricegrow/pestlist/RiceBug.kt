package com.example.ricegrow.pest

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.ricegrow.R
import com.example.ricegrow.descriptions.PestDescription

@Composable
fun RiceBug(navController: NavController){
    PestDescription(
        pestName = "Rice Bug",
        pestDescription = "The insect attacks during spikelet stage of rice crop. Both nymphs and adults suck the content out of grains from " +
                "pre-flowering spikelets to soft dough stage. This leads to unfilled, empty and discolored grains. \n If the infestation is severe it may cause yield loss up to 30%.",
        pestImage = R.drawable.ricebug,
        management = "Keep the field and surrounding area free from weeds which serves as alternative host for insect during non cropping season. Equal distribution of fertilizers and water in rice field to encourage even crop growth. Collect and kill " +
                "insects manually by using net during early morning and late after noon. Encourage biological control agents."
    )
}