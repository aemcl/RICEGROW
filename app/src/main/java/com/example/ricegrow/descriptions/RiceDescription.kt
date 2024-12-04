package com.example.ricegrow.descriptions

import android.annotation.SuppressLint
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
import com.example.ricegrow.ImageCaptureAndUpload
import com.example.ricegrow.Information
import com.example.ricegrow.ListCard
import com.example.ricegrow.MainTopBar
import com.example.ricegrow.R
import com.example.ricegrow.Routes

class RiceDescription {
    @Composable
    fun RiceDescriptionView(
        info: Information,
        plantingMonth: String,
        harvestingMonth: String
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5DC))
                .padding(bottom = 100.dp)
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
            ) {
                Image(
                    painter = painterResource(info.image),
                    contentDescription = "Rice Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(50.dp))
                )

                Text(
                    text = info.name,
                    fontSize = 40.sp,
                    color = Color(0xFF2b2b2b),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = info.description,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF2b2b2b),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(10.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "PLANTING MONTH:",
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,
                    fontSize = 28.sp,
                    color = Color(0xFF2b2b2b),
                    modifier = Modifier.padding(10.dp)
                )

                Text(
                    text = plantingMonth,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify,
                    color = Color(0xFF2b2b2b),
                    modifier = Modifier.padding(10.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "HARVESTING MONTH:",
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,
                    color = Color(0xFF2b2b2b),
                    fontSize = 28.sp,
                    modifier = Modifier.padding(10.dp)
                )

                Text(
                    text = harvestingMonth,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify,
                    color = Color(0xFF2b2b2b),
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun ListOfRice(navController: NavController) {
        val riceList = listOf(
            CardItem(R.drawable.blackrice, "Black Rice Image", "Black Rice", Routes.blackrice),
            CardItem(R.drawable.kasolid, "Kasolid Image", "Kasolid (11026)" , Routes.kasolid),
            CardItem(R.drawable.pilit, "Pilit Image", "Pilit (11022)" , Routes.pilit),
            CardItem(R.drawable.dinorado, "Dinorado Image", "Dinorado (11036)" , Routes.dinorado),
            CardItem(R.drawable.glutinous, "Glutinous Rice Image", "Glutinous Rice", Routes.glutinous),
            CardItem(R.drawable.pusabasmati, "Pusa Basmati 1121 Image", "Pusa Basmati (1121)", Routes.pusabasmati),
            CardItem(R.drawable.basmati, "Pusa Basmati 1692 Image", "Pusa Basmati (1692)", Routes.basmati),
            CardItem(R.drawable.metao, "Metao Image", "Metao (11015)", Routes.metao),
            CardItem(R.drawable.pirurutong, "Pirurutong Image", "Pirurutong (10876)", Routes.pirurutong)
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = { ImageCaptureAndUpload().FAB(navController) }
        ) { padding ->
            Column {
                MainTopBar(
                    icon = Icons.Filled.Home,
                    pageTitle = "Rice",
                    iconRoute = Routes.home,
                    navController = navController
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF5F5DC))
                        .padding(padding)
                        .padding(bottom = 100.dp)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // 2 cards per row
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(riceList) { rice ->
                            ListCard(
                                painter = painterResource(id = rice.painter), // Fix: Using painterResource for drawable IDs
                                contentDescription = rice.description,
                                name = rice.name,
                                navController = navController,
                                destinationRoute = rice.route
                            )
                        }
                    }
                }
            }
        }
    }

@Composable
fun BlackRice(navController: NavController) {
    RiceDescription().RiceDescriptionView(
        info = Information(
            name = "Black Rice", image = R.drawable.blackrice,
            description = "Black rice or forbidden rice is a whole grain rice variety known " +
                    "for its deep color and high nutritional value. It's rich in antioxidants " +
                    "particularly anthocyanins, and has a slightly sweet, nutty flavor.",
        ),
        plantingMonth = "May\nJune\nJuly\n",
        harvestingMonth = "Around 3 to 4 months (Between September and December)"
    )
    MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Black Rice", iconRoute = "",navController = navController)
}


    @Composable
    fun Dinorado(navController: NavController) {
        RiceDescription().RiceDescriptionView(
            info = Information(
                name = "Dinorado",
                image = R.drawable.dinorado,
                description = "Dinorado is another rice variety known for its aromatic qualities and high-quality grains. " +
                        "It is often sought after for its excellent cooking characteristics and flavor, making it a popular choice in culinary applications.",
            ),
            plantingMonth = "May\nJune\nJuly",
            harvestingMonth = "About 3 to 4 months (Between September and December"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Dinorado", iconRoute = "",navController = navController)
    }

    @Composable
    fun Kasolid(navController: NavController) {
        RiceDescription().RiceDescriptionView(
            info = Information(
                name = "Kasolid (11026)", image = R.drawable.kasolid,
                description = "Kasolid (11026) is a variety of rice seed known for its high yield" +
                        " and adaptability to various growing conditions. It's often used in breeding " +
                        "programs due to its desirable traits, such as disease resistance and grain quality.",
            ),
            plantingMonth = "May\nJune\nJuly\nAugust",
            harvestingMonth = "Around 3 to 4 Months (Between September & December)"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Kasolid (11026)", iconRoute = "",navController = navController)
    }

    @Composable
    fun GlutinousRice(navController: NavController) {
        RiceDescription().RiceDescriptionView(
            info = Information(
                name = "Glutinous Rice", image = R.drawable.glutinous,
                description = "A delicacy made of short-grain rice and is known to be sticky and " +
                        "because of a larger amount of amylopectin starch, compared to ordinary types of rice.",
            ),
            plantingMonth = "Wet Season(May to June)\n" +
                            "Dry Seasom(November to December)",
            harvestingMonth = "Occurs about 100 to 120 days after planting"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Glutinous Rice", iconRoute = "",navController = navController)
    }

    @Composable
    fun Pilit(navController: NavController) {
        RiceDescription().RiceDescriptionView(
            info = Information(
                name = "Pilit (11022)", image = R.drawable.pilit,
                description = "Pilit (11022) is a rice variety known for its high-quality grains " +
                        "and good cooking properties. It's often cultivated for its resilience to various " +
                        "environmental stresses and is favored in regions where specific taste and texture are desired.",
            ),
            plantingMonth = "May\nJune\nJuly\nAugust",
            harvestingMonth = "Around 3 to 4 Months (Between September & December)"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Pilit (11022)", iconRoute = "",navController = navController)
    }

    @Composable
    fun PusaBasmati21(navController: NavController) {
        RiceDescription().RiceDescriptionView(
            info = Information(
                name = "Pusa Basmati (1121)", image = R.drawable.pusabasmati,
                description = "Pusa Basmati 1121 is an independently derived Basmati rice variety, " +
                        "evolved through the process of hybridization over a long breeding process",
            ),
            plantingMonth = "Wet Season (June to July)\n" +
                            "Dry Season (November to December)",
            harvestingMonth = "For the Wet Season (October to November)\n" +
                            "For the Dry Season ( March to April)"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Pusa Basmati (1121)", iconRoute = "",navController = navController)
    }

    @Composable
    fun PusaBasmati92(navController: NavController) {
        RiceDescription().RiceDescriptionView(
            info = Information(
                name = "Pusa Basmati (1692)", image = R.drawable.basmati,
                description = "Pusa Basmati 1692 is a semi-dwarf Basmati variety with a seed to seed" +
                        " maturity of 110-115 days producing an average yield of 5.26 t/ha",
            ),
            plantingMonth = "Wet Season(June to July)",
            harvestingMonth = "120-130 days after sowing"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Pusa Basmati (1692)", iconRoute = "",navController = navController)
    }

    @Composable
    fun Metao(navController: NavController) {
        RiceDescription().RiceDescriptionView(
            info = Information(
                name = "Metao (11015)", image = R.drawable.metao,
                description = "Metao (11015) - is a premium variety of rice popular in the Philippines, " +
                        "known for its long grains and excellent cooking qualities. It has a slightly " +
                        "aromatic scent and is often used in dishes where rice needs to remain fluffy and " +
                        "separate, such as fried rice or pilaf.",
            ),
            plantingMonth = "Wet Season: May to November\n"+
                    "Dry Season: December to April",
            harvestingMonth = "Wet Season: October to December.\n" +
                            "Dry Season: November and January"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Metao (11015)", iconRoute = "",navController = navController)
    }

    @Composable
    fun Pirurutong(navController: NavController) {
        RiceDescription().RiceDescriptionView(
            info = Information(
                name = "Pirurutong (10876)", image = R.drawable.pirurutong,
                description =
                        "Pirurutong (10876) also known as purple glutinous rice,is a variety of aromatic " +
                        "rice in the Philippines. It has a distinct purple-brown color and soft, glutinous texture when cooked.",
            ),
            plantingMonth = "Wet Season: May to June\n" +
                             "Dry Season: November to January ",
            harvestingMonth = "Wet Season: October to December \n" +
                             "Dry season: March to May"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Pirurutong", iconRoute = "",navController = navController)
    }
}
