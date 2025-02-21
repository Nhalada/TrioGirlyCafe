package com.project.triogirlycafe

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.triogirlycafe.models.drinksList
import com.project.triogirlycafe.screen.AboutScreen
import com.project.triogirlycafe.screen.DrinkDetailScreen
import com.project.triogirlycafe.screen.LoginScreen
import com.project.triogirlycafe.screen.MainScreen
import com.project.triogirlycafe.screen.SettingsScreen

@Composable
fun TrioGirlyCafeApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login_screen"
    ) {
        composable("login_screen") {
            LoginScreen(navController)
        }
        composable("main_screen") {
            MainScreen(navController)
        }
        composable(
            route = "drink_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            val selectedItem = drinksList.firstOrNull { it.id == id }
            if (selectedItem != null) {
                DrinkDetailScreen(navController, selectedItem)
            }
        }


        composable("about_us_screen") {
            AboutScreen(navController)
        }
        composable("settings_screen") {
            SettingsScreen(navController)
        }
    }
}
