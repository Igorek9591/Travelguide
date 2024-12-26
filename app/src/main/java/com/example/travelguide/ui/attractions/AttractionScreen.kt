package com.example.travelguide.ui.attractions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelguide.Screen
import com.example.travelguide.ui.components.AttractionItem
import org.koin.androidx.compose.getViewModel

@Composable
fun AttractionScreen(
    navController: NavController,
    viewModel: AttractionsViewModel = getViewModel()
) {
    val attractions by viewModel.attractions.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Button(
            onClick = {
                navController.navigate(Screen.AddAttraction.route)
            },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Добавить достопримечательность")
        }

        LazyColumn {
            items(attractions) { attraction ->
                AttractionItem(
                    attraction = attraction,
                    // При клике: переходим на Notes
                    onClick = {
                        navController.navigate(Screen.Notes.createRoute(attraction.id))
                    }
                )
            }
        }
    }
}
