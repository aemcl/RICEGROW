package com.example.ricegrow.descriptions

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

class DiseaseDescription{

    @Composable
    fun DiseaseDescriptionView(
        info: Information,
        diseaseCauses: String,
        treatment: String
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(bottom = 100.dp)
        )
        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
        ) {
           Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
            ){
                Image(
                    painter = painterResource(info.image),
                    contentDescription = "Disease Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(50.dp))
                )
                Text(
                    text = info.name,
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = info.description ,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(10.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "Cause: $diseaseCauses",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(10.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))

                HorizontalDivider()
                Text(
                    text = "HOW TO MANAGE:",
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 28 .sp,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = treatment,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }

    @Composable
    fun ListOfDisease(navController: NavController) {

        val diseaseList = listOf(
            CardItem(R.drawable.brownspot, "Brown Spot Image",  "Brown Spot" , Routes.brownspot),
            CardItem(R.drawable.bacterialblight, "Rice Bacterial Blight Image", "Rice Bacterial Blight" , Routes.riceBacterialblight),
            CardItem(R.drawable.tungro, "Tungro Image", "Tungro" , Routes.tungro),
            CardItem(R.drawable.riceblast, "Rice Blast", "Rice Blast" , Routes.riceblast)
        )
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = { ImageCaptureAndUpload().FAB(navController) }
        ) { padding ->
            Column {
                MainTopBar(
                    icon = Icons.Filled.Home,
                    pageTitle = "Rice Disease",
                    iconRoute = Routes.home,
                    navController = navController
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(padding)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // 2 cards per row
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(diseaseList) { disease ->
                            ListCard(
                                painter = painterResource(id = disease.painter),
                                contentDescription = disease.description,
                                name = disease.name,
                                navController = navController,
                                destinationRoute = disease.route
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun RiceBacterialBlight(navController: NavController){
        DiseaseDescription().DiseaseDescriptionView(
            info = Information(
                name = "Rice Bacterial Blight",
                image = R.drawable.bacterialblight,
                description = "Water-soaked stripes on leaf blades; yellow or white stripes on leaf blades; " +
                        "leaves appear grayish in color; plants wilting and rolling up; leaves turning yellow; " +
                        "stunted plants; plant death; youngest leaf on plant turning yellow"),
            diseaseCauses = "Bacterium",
            treatment = "Bacterial blight can be effectively controlled by planting resistant rice varieties;" +
                    " avoid excessive nitrogen fertilization; plow stubble and straw into soil after harvest"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Rice Bacterial Blight",Routes.listdiseases, navController = navController)
    }

    @Composable
    fun Tungro(navController: NavController){
        DiseaseDescription().DiseaseDescriptionView(
            info = Information(
                name = "Tungro",
                image = R.drawable.tungro,
                description = "Tungro is caused by two viral agents: the Rice Tungro Spherical Virus (RTSV) and " +
                        "the Rice Tungro Bacilliform Virus (RTBV). The disease is characterized by yellowing, stunting, " +
                        "and reduced leaf growth. Infected plants often show orange-yellow streaks on the leaves and " +
                        "the rice grains may become discolored or malformed. The disease is transmitted by leafhoppers, " +
                        "especially the species *Nephotettix virescens*."),
            diseaseCauses = "Virus (RTSV and RTBV), transmitted by leafhoppers (*Nephotettix virescens*)",
            treatment = "Management of Tungro includes controlling the leafhopper vector using insecticides, " +
                    "as well as removing and destroying infected plants. Early planting and using resistant " +
                    "varieties can help reduce the incidence of the disease. Avoiding excessive nitrogen fertilization " +
                    "and controlling the movement of rice plants between fields can also limit spread."
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Tungro",Routes.listdiseases, navController = navController)
    }

    @Composable
    fun BrownSpot(navController: NavController){
        DiseaseDescription().DiseaseDescriptionView(
            info = Information (
                name = "Brown Spot",
                image = R.drawable.brownspot,
                description = "Brown Spot, caused by the fungus *Cochliobolus miyabeanus*, appears as " +
                        "small, round to oval lesions on the leaves, which become dark brown with yellow halos. " +
                        "As the disease progresses, the lesions enlarge and coalesce, leading to premature leaf senescence. " +
                        "This disease can cause significant yield losses by affecting photosynthesis and grain development. " +
                        "It is common in regions with high humidity and moderate temperatures, especially in Asia, " +
                        "Africa, and Latin America."),
            diseaseCauses = "Fungus: Cochliobolus miyabeanus",

            treatment = "Fungicide applications, particularly with active ingredients like propiconazole, " +
                    "may help reduce disease spread. Crop rotation, use of resistant rice varieties, and avoiding " +
                    "overcrowding are other effective preventive measures. Timely application of fungicides can " +
                    "help manage the disease, especially during the tillering and panicle initiation stages."
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Brown Spot",Routes.listdiseases, navController = navController)
    }

    @Composable
    fun RiceBlast(navController: NavController){
        DiseaseDescription().DiseaseDescriptionView(
            info = Information(
                name = "Rice Blast",
                image = R.drawable.riceblast,
                description = "Lesions on all parts of shoot; white to green or gray diamond-shaped " +
                        "lesions with dark green borders; death of leaf blades; black necrotic patches on culm;" +
                        " rotting panicles" + "Most important disease of rice worldwide; causes most damage " +
                        "in areas of intense cultivation; disease emergence favors high soil nitrogen content",
            ),
            diseaseCauses = "Fungus",
            treatment = "If disease is not endemic to the region, blast can be controlled by planting " +
                    "resistant rice varieties; avoid over-fertilizing crop with nitrogen as this increases " +
                    "the plant's susceptibility to the disease; utilize good water management to ensure " +
                    "plants do not suffer from drought stress; disease can be effectively controlled by " +
                    "the application of appropriate systemic fungicides, where available"
        )
        MainTopBar(icon = Icons.AutoMirrored.Filled.ArrowBack, pageTitle = "Rice Blast",Routes.listdiseases, navController = navController)

    }
}
