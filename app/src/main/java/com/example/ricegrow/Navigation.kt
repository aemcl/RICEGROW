package com.example.ricegrow

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ricegrow.descriptions.DiseaseDescription
import com.example.ricegrow.descriptions.FertilizerDescription
import com.example.ricegrow.descriptions.PestDescription
import com.example.ricegrow.descriptions.RiceDescription


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.home, builder = {
        composable(Routes.home){
            Home(navController)
        }

        composable(Routes.ricelist) {
            RiceDescription().ListOfRice(navController)
        }

        composable(Routes.help) {
            Help().ShowHelp()
        }

        composable(Routes.mainbottombar) {
            MainBottomBar(navController)
        }

        composable("take_picture") { ImageCaptureAndUpload().TakePicture(navController) }
        composable("image_display/{imageUri}") { backStackEntry ->
            val imageUri = backStackEntry.arguments?.getString("imageUri")
            IdentifyDisease().ImageDisplayScreen(imageUri = imageUri, navController)
        }





        //rice
        composable(Routes.blackrice) {
            RiceDescription().BlackRice(navController)
        }
        composable(Routes.kasolid) {
            RiceDescription().Kasolid(navController)
        }
        composable(Routes.pilit) {
            RiceDescription().Pilit(navController)
        }
        composable(Routes.dinorado) {
            RiceDescription().Dinorado(navController)
        }
        composable(Routes.glutinous) {
            RiceDescription().GlutinousRice(navController)
        }
        composable(Routes.pusabasmati) {
            RiceDescription().PusaBasmati21(navController)
        }
        composable(Routes.basmati) {
            RiceDescription().PusaBasmati92(navController)
        }
        composable(Routes.pirurutong) {
            RiceDescription().Pirurutong(navController)
        }
        composable(Routes.metao) {
            RiceDescription().Metao(navController)
        }

        //fertilizers
        composable(Routes.listfertilizer) {
            FertilizerDescription().ListOfFertilizer(navController)
        }
        composable(Routes.fertOne) {
            FertilizerDescription().FertOne(navController)
        }
        composable(Routes.fertTwo) {
            FertilizerDescription().FertTwo(navController)
        }
        composable(Routes.fertThree) {
            FertilizerDescription().FertThree(navController)
        }

        //pests
        composable(Routes.listpest) {
            PestDescription().ListOfPest(navController)
        }
        composable(Routes.greenleafhoppers) {
            PestDescription().GreenLeafhoppers(navController)
        }
        composable(Routes.ricebug) {
            PestDescription().RiceBug(navController)
        }
        composable(Routes.ricemealy) {
            PestDescription().RiceMealyBugs(navController)
        }
        composable(Routes.caseworm) {
            PestDescription().RiceCaseworm(navController)
        }


        //Diseases
        composable(Routes.listdiseases) {
            DiseaseDescription().ListOfDisease(navController)
        }
        composable(Routes.tungro) {
            DiseaseDescription().Tungro(navController)
        }
        composable(Routes.brownspot) {
            DiseaseDescription().BrownSpot(navController)
        }
        composable(Routes.riceBacterialblight) {
            DiseaseDescription().RiceBacterialBlight(navController)
        }
        composable(Routes.riceblast) {
            DiseaseDescription().RiceBlast(navController)
        }

    })
}