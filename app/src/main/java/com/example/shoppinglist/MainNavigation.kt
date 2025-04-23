package com.example.shoppinglist

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController

// Import the routes and screens
import com.example.shoppinglist.ui.navigation.ItemDetailScreenRoute
import com.example.shoppinglist.ui.navigation.ShoppingListScreenRoute
import com.example.shoppinglist.ui.navigation.SplashScreenRoute
import com.example.shoppinglist.ui.screen.ItemDetailScreen
import com.example.shoppinglist.ui.screen.ShoppingListScreen
import com.example.shoppinglist.ui.screen.SplashScreen

@Composable
fun ShoppingListApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SplashScreenRoute
    ) {
        composable(SplashScreenRoute) {
            SplashScreen(navController)
        }
        composable(ShoppingListScreenRoute) {
            ShoppingListScreen(navController = navController)
        }
        composable(ItemDetailScreenRoute) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toInt()
            if (itemId != null) {
                ItemDetailScreen(itemId = itemId, navController = navController)
            }
        }
    }
}