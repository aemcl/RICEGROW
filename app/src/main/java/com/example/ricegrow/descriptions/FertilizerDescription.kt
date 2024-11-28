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
import com.example.ricegrow.ImageCaptureAndUpload
import com.example.ricegrow.ListCard
import com.example.ricegrow.MainTopBar
import com.example.ricegrow.R
import com.example.ricegrow.Routes

class FertilizerDescription {

    @Composable
    fun FertilizerDescriptionView(
        fertilizerName: String,
        fertilizerDescription: String,
        @DrawableRes fertilizerImage: Int,
        usage: String
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
                    painter = painterResource(fertilizerImage),
                    contentDescription = "Black Rice",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(50.dp))
                )

                Text(
                    text = fertilizerName,
                    fontSize = 38.sp,
                    color = Color(0xFF2b2b2b),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(start = 10.dp,top = 20.dp, bottom = 10.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = fertilizerDescription,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF2b2b2b),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(10.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "USAGE:",
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,
                    color = Color(0xFF2b2b2b),
                    fontSize = 28 .sp,
                    modifier = Modifier.padding(10.dp))

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = usage,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify,
                    color = Color(0xFF2b2b2b),
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }


    @Composable
    fun ListOfFertilizer(navController: NavController) {

        val fertilizerList = listOf(
            CardItem(R.drawable.fertone, "fertone", "14-14-14" , Routes.fertOne),
            CardItem(R.drawable.ferttwo, "ferttwo",  "16-20-0" , Routes.fertTwo),
            CardItem(R.drawable.fertthree, "fertthree", "46-0-0 + 0-0-60" , Routes.fertThree)
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = { ImageCaptureAndUpload().FAB(navController) }
        ) { padding ->
            Column {
                MainTopBar(
                    icon = Icons.Filled.Home,
                    pageTitle = "Rice Fertilizer",
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
                        columns = GridCells.Fixed(2), // 2 cards per row
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(fertilizerList) { fertilizer ->
                            ListCard(
                                painter = painterResource(id = fertilizer.painter), // Fix: Using painterResource for drawable IDs
                                contentDescription = fertilizer.description,
                                name = fertilizer.name,
                                navController = navController,
                                destinationRoute = fertilizer.route
                            )
                        }
                    }
                }
            }
        }
        }


    @Composable
    fun FertOne(navController: NavController){
        FertilizerDescription().FertilizerDescriptionView(
            fertilizerName = "14-14-14",
            fertilizerDescription = "Triple 14 Complete Fertilizer (Harvester) also called 14-14-14 has " +
                    "equal percentage of Nitrogen, phosphate, and potassium that will help crops carry out to " +
                    "their full cycle that can last up to 3 months.",
            fertilizerImage = R.drawable.fertone,
            usage = "• It will be placed during 0-14 days\n" +
                    "• 3-4 sacks"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "14-14-14",Routes.listfertilizer, navController = navController)
    }

    @Composable
    fun FertTwo(navController: NavController) {
        FertilizerDescription().FertilizerDescriptionView(
            fertilizerName = "16-20-0 ",
            fertilizerDescription = "It used for and root & foliage development during the early stages of plant.",
            fertilizerImage = R.drawable.ferttwo,
            usage = "• 25-30 days placed after transplanting \n" +
                    "• 1.5-2 sacks(Optional)"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "16-20-0",Routes.listfertilizer, navController = navController)
    }

    @Composable
    fun FertThree(navController: NavController) {
        FertilizerDescription().FertilizerDescriptionView(
            fertilizerName = "46-0-0 + 0-0-60",
            fertilizerDescription = "Provides aids in the development of strong root systems, enhances drought tolerance, and improves nutrient uptake.",
            fertilizerImage = R.drawable.fertthree,
            usage = "• 45-55 Days(Conception/Pregnant)\n" +
                    "• for the 46-0-0 it takes 1.5-2 sacks\n" +
                    "  for the 0-0-60 it takes 1-2 sacks"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "46-0-0 + 0-0-60",Routes.listfertilizer, navController = navController)
    }
}