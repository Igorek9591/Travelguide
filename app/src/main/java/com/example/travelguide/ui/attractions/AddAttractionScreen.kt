package com.example.travelguide.ui.attractions

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelguide.data.db.entities.Attraction
import org.koin.androidx.compose.getViewModel
import com.example.travelguide.Screen

@Composable
fun AddAttractionScreen(
    navController: NavController,
    viewModel: AttractionsViewModel = getViewModel()
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Добавить достопримечательность", modifier = Modifier.padding(bottom = 8.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Название") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Описание") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Местоположение (город, адрес)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Тип (музей, парк, памятник или любой)") },
            modifier = Modifier.fillMaxWidth()
        )


        Button(
            onClick = {
                    viewModel.addAttraction(
                        Attraction(
                            title = title,
                            description = description,
                            location = location,
                            type = type
                        )
                    )

                navController.navigate(Screen.Attractions.route) {
                    popUpTo(Screen.Attractions.route) { inclusive = true }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Сохранить")
        }
    }
}
