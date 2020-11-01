package com.harrisonbacordo.flatmate.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.Text
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.harrisonbacordo.flatmate.R
import com.harrisonbacordo.flatmate.ui.home.calendar.CalendarScreen
import com.harrisonbacordo.flatmate.ui.home.chores.ChoresScreen
import com.harrisonbacordo.flatmate.ui.home.groceries.GroceriesScreen
import com.harrisonbacordo.flatmate.ui.home.settings.SettingsScreen

@Composable
fun HomeScreen(navController: NavController) {
    val bottomNavController = rememberNavController()
    val homeDestinations = listOf(
        HomeDestinations.Chores,
        HomeDestinations.Calendar,
        HomeDestinations.Groceries,
        HomeDestinations.Settings
    )
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Flatmate") })
        },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.get(KEY_ROUTE)
                homeDestinations.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            bottomNavController.popBackStack(bottomNavController.graph.startDestination, false)
                            if (currentRoute != screen.route) {
                                bottomNavController.navigate(screen.route)
                            }
                        })
                }
            }
        }
    ) {
        NavHost(bottomNavController, startDestination = HomeDestinations.Chores.route) {
            composable(HomeDestinations.Chores.route) { ChoresScreen() }
            composable(HomeDestinations.Calendar.route) { CalendarScreen() }
            composable(HomeDestinations.Groceries.route) { GroceriesScreen() }
            composable(HomeDestinations.Settings.route) { SettingsScreen(navController) }
        }
    }
}

sealed class HomeDestinations(val route: String, @StringRes val resourceId: Int, val icon: VectorAsset) {
    object Chores : HomeDestinations("chores", R.string.chores, Icons.Filled.CleaningServices)
    object Calendar : HomeDestinations("calendar", R.string.calendar, Icons.Filled.Event)
    object Groceries : HomeDestinations("groceries", R.string.groceries, Icons.Filled.LocalGroceryStore)
    object Settings : HomeDestinations("settings", R.string.settings, Icons.Filled.Settings)
}