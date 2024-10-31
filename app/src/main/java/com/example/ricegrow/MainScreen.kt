package com.example.ricegrow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ricegrow.lists.RiceList
import com.example.ricegrow.menubuttons.ListOfDisease
import com.example.ricegrow.menubuttons.ListOfFertilizer
import com.example.ricegrow.menubuttons.ListOfPest
import com.example.ricegrow.pages.NavItem

@Composable
fun MainScreen(navController: NavController){
    val navItemList = listOf(
        NavItem("Rice", R.drawable.rice ),
        NavItem("Fertilizer",R.drawable.fertilizer),
        NavItem("Pest",R.drawable.pest),
        NavItem("Disease", R.drawable.disease)
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        // .background(color = Color(0XFF2E77AE))

        bottomBar = {
            NavigationBar(
                modifier = Modifier .shadow(
                    elevation = 10.dp,
                    spotColor = Color.DarkGray
                ),
                containerColor = Color(0xFFEBDA98),
            ) {
                navItemList.forEachIndexed{ index, navItem ->
                    NavigationBarItem(
                        modifier = Modifier.padding(top = 15.dp),
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index},
                        icon = {
                            Icon(
                                painter = painterResource(id = navItem.iconImage),
                                contentDescription = "Icon",
                                modifier = Modifier.size(50.dp))
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) {
            innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex, navController )
    }
}

@Composable
fun ContentScreen (modifier: Modifier = Modifier, selectedIndex : Int, navController: NavController){
    when(selectedIndex){
        0 -> RiceList(navController)
        1 -> ListOfFertilizer(navController)
        2 -> ListOfPest(navController)
        3 -> ListOfDisease(navController)
    }
}