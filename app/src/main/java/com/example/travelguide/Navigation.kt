package com.example.travelguide

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.travelguide.ui.attractions.AttractionScreen
import com.example.travelguide.ui.notes.NotesScreen
import com.example.travelguide.ui.attractions.AddAttractionScreen

sealed class Screen(val route: String) {
    object Attractions : Screen("attractions")
    object AddAttraction : Screen("add_attraction")

    object Notes : Screen("notes/{attractionId}") {
        fun createRoute(attractionId: Int): String {
            return "notes/$attractionId"
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Attractions.route
    ) {
        composable(Screen.Attractions.route) {
            AttractionScreen(navController = navController)
        }

        composable(Screen.AddAttraction.route) {
            AddAttractionScreen(navController)
        }

        composable(
            route = Screen.Notes.route,
            arguments = listOf(navArgument("attractionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val attractionId = backStackEntry.arguments?.getInt("attractionId") ?: 0
            NotesScreen(
                userId = 1,          // Выбор пользователя
                attractionId = attractionId
            )
        }
    }
}
