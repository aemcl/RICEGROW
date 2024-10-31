package com.example.ricegrow.pest

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.ricegrow.R
import com.example.ricegrow.descriptions.PestDescription

@Composable
fun GreenLeafhoppers(navController: NavController){
    PestDescription(
        pestName = "Green Leafhoppers",
        pestDescription = "Plants may show no symptoms of leafhopper of planthopper damage; feeding punctures can leave the plants susceptible to bacterial or fungal infections; insects transmit many rice viruses;" +
                " if infestations is severe, insects may cause plant to completely dry out; " +
                "adults insects are pale green or brown winged insects with piercing-sucking mouthpartsLeaf and planthoppers transmit many rice viruses, including grassy stunt and tungro virus",
        pestImage = R.drawable.greenleafhoppers,
        management = "Rotating crop for a period of one year is an effective and economical method of controlling hopper numbers; natural enemies and predators are often very successful at controlling hoppers and should be conserved by avoiding inappropriate use of insecticides which can damage their populations; " +
                "planting resistant varieties is a very effective control method; chemical control with an appropriate insecticide may be necessary but should only be applied if the insects have reached an economic threshold"
    )
}