package com.example.ricegrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ricegrow.disease.Bakanae
import com.example.ricegrow.fertilizer.FertOne
import com.example.ricegrow.fertilizer.FertThree
import com.example.ricegrow.fertilizer.FertTwo
import com.example.ricegrow.lists.RiceList
import com.example.ricegrow.menubuttons.ListOfDisease
import com.example.ricegrow.menubuttons.ListOfFertilizer
import com.example.ricegrow.menubuttons.ListOfPest
import com.example.ricegrow.pest.GreenLeafhoppers
import com.example.ricegrow.pest.RiceBug
import com.example.ricegrow.pest.RiceCaseworm
import com.example.ricegrow.rice.BlackRice
import com.example.ricegrow.rice.Dinorado
import com.example.ricegrow.rice.Kasolid
import com.example.ricegrow.rice.Pilit
import com.example.ricegrowII.pest.RiceMealyBugs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.home, builder = {
                composable(Routes.home){
                    Home(navController)
                }

                composable(Routes.ricelist) {
                    RiceList(navController)
                }

                composable(Routes.help) {
                    Help()
                }

                composable(Routes.mainScreen) {
                    MainScreen(navController)
                }

                composable(Routes.camera) {
                    CameraIdentifier(navController)
                }

                composable(Routes.blackrice) {
                    BlackRice()
                }

                composable(Routes.kasolid) {
                    Kasolid()
                }

                composable(Routes.pilit) {
                    Pilit()
                }

                composable(Routes.dinorado) {
                    Dinorado()
                }

                composable(Routes.listfertilizer) {
                    ListOfFertilizer(navController)
                }

                composable(Routes.fertOne) {
                    FertOne(navController)
                }

                composable(Routes.fertTwo) {
                    FertTwo(navController)
                }

                composable(Routes.fertThree) {
                    FertThree(navController)
                }

                composable(Routes.listpest) {
                    ListOfPest(navController)
                }

                composable(Routes.greenleafhoppers) {
                    GreenLeafhoppers(navController)
                }

                composable(Routes.ricebug) {
                    RiceBug(navController)
                }

                composable(Routes.ricemealy) {
                    RiceMealyBugs(navController)
                }

                composable(Routes.caseworm) {
                    RiceCaseworm(navController)
                }

                composable(Routes.listdiseases) {
                    ListOfDisease(navController)
                }

                composable(Routes.bakanae) {
                    Bakanae(navController)
                }
            })

        }
    }
}

