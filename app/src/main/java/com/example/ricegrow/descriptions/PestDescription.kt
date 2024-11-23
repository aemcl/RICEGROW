package com.example.ricegrow.descriptions

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ricegrow.CardItem
import com.example.ricegrow.FAB
import com.example.ricegrow.MainTopBar
import com.example.ricegrow.R
import com.example.ricegrow.Routes
import com.example.ricegrow.ListCard

class PestDescription{

    @Composable
    fun PestDescriptionView(
        pestName: String,
        pestDescription: String,
        @DrawableRes pestImage: Int,
        management: String
    ){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC))
        )
        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5DC)),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState()),
            ){
                Image(
                    painter = painterResource(pestImage),
                    contentDescription = "Black Rice",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(50.dp))
                )

                Text(
                    text = pestName,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(start = 10.dp,top = 20.dp, bottom = 10.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = pestDescription ,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(10.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "HOW TO MANAGE:",
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,
                    fontSize = 28 .sp,
                    modifier = Modifier.padding(10.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = management,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify,
                    color = Color.Gray,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }

    @Composable
    fun ListOfPest(navController: NavController){

        val pestList = listOf(
            CardItem(R.drawable.greenleafhoppers,"Green LeafHoppers Image","Green Leafhoppers" , Routes.greenleafhoppers),
            CardItem(R.drawable.ricebug,"Rice Bug","Rice Bug" , Routes.ricebug),
            CardItem(R.drawable.ricecase,"Rice Case Worm Image","Rice Case Worm" , Routes.caseworm),
            CardItem(R.drawable.ricemealybugs,"Rice Mealy Bugs Image", "Rice Mealy Bugs" , Routes.ricemealy),
        )
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = { FAB(navController) }
        ) { padding ->
            Column {
                MainTopBar(
                    icon = Icons.Filled.Home,
                    pageTitle = "Rice Pest",
                    iconRoute = Routes.home,
                    navController = navController
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF5F5DC))
                        .padding(padding)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(pestList) { pest ->
                            ListCard(
                                painter = painterResource(id = pest.painter),
                                contentDescription = pest.description,
                                name = pest.name,
                                navController = navController,
                                destinationRoute = pest.route
                            )
                        }
                    }
                }
            }
        }
        }

    @Composable
    fun RiceMealyBugs(navController: NavController){
        PestDescription().PestDescriptionView(
            pestName = "Rice Mealy Bugs",
            pestDescription = "We can see white wax covered eggs, nymphs and adults on infected plants. " +
                    "The insects are common in field with well drained soil. Both adult and nymphs feed on rice plant by sucking sap." +
                    "The main symptoms are stunting and wilting of plants, yellowing and curling of leaves.",
            pestImage = R.drawable.ricemealybugs,
            management = "Augment biocontrol agents (like lady bird beetles, chloropid flies, spiders, small encyrtid wasps) in rice fields. "
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Rice Mealy Bugs", iconRoute = "", navController = navController)
    }

    @Composable
    fun RiceCaseworm(navController: NavController){
        PestDescription().PestDescriptionView(
            pestName = "Rice Caseworm Nymphula depunctalis (Guenee)",
            pestDescription = "The damaged leaf tissue looks like ladder. Case worm larvae scraps chlorophyll from leaves. Another important symptom is the larvae cuts off leaf tips and make cylindrical tubes around them. " +
                    "In infected field you can see cylindrical tubes attached to plants or floating on water surface.",
            pestImage = R.drawable.ricecase,
            management = "Drain water to remove floating larvae from field. Follow proper cultivation practices like nitrogen application and spacing. " +
                    "Encourage biological control agents like snails, spiders, lady bird beetles, dragon flies in rice field."
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Rice Caseworm", iconRoute = "", navController = navController)
    }

    @Composable
    fun RiceBug(navController: NavController){
        PestDescription().PestDescriptionView(
            pestName = "Rice Bug",
            pestDescription = "Right.  Rice bug infested grains\n" +
                            "Left. Rice bug attacking spikelet\n\n " +
                    "The insect attacks during spikelet stage of rice crop. Both nymphs and adults suck the content out of grains from " +
                    "pre-flowering spikelets to soft dough stage. This leads to unfilled, empty and discolored grains. \n" +
                    "\nIf the infestation is severe it may cause yield loss up to 30%.",
            pestImage = R.drawable.ricebug,
            management = "Keep the field and surrounding area free from weeds which serves as alternative host for insect during non cropping season. Equal distribution of fertilizers and water in rice field to encourage even crop growth. Collect and kill " +
                    "insects manually by using net during early morning and late after noon. Encourage biological control agents."
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Rice Bug", iconRoute = "", navController = navController)
    }

    @Composable
    fun GreenLeafhoppers(navController: NavController){
        PestDescription().PestDescriptionView(
            pestName = "Green Leafhoppers",
            pestDescription = "Plants may show no symptoms of leafhopper of planthopper damage; feeding punctures can leave the plants susceptible to bacterial or fungal infections; insects transmit many rice viruses;" +
                    " if infestations is severe, insects may cause plant to completely dry out; " +
                    "adults insects are pale green or brown winged insects with piercing-sucking mouthpartsLeaf and planthoppers transmit many rice viruses, including grassy stunt and tungro virus",
            pestImage = R.drawable.greenleafhoppers,
            management = "Rotating crop for a period of one year is an effective and economical method of controlling hopper numbers; " +
                    "natural enemies and predators are often very successful at controlling hoppers and should be conserved by avoiding inappropriate use of insecticides which can damage their populations; " +
                    "planting resistant varieties is a very effective control method; chemical control with an appropriate insecticide may be necessary but should only be applied if the insects have reached an economic threshold"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Green Leafhoppers", iconRoute = "", navController = navController)
    }
}//class end