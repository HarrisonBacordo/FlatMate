/*
 * Designed and developed by 2020 FlatMate (Harrison Bacordo)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.R
import com.harrisonbacordo.flatmate.ui.home.calendar.CalendarScreen
import com.harrisonbacordo.flatmate.ui.home.chores.HomeChoresScreen
import com.harrisonbacordo.flatmate.ui.home.groceries.GroceriesScreen
import com.harrisonbacordo.flatmate.ui.home.settings.SettingsScreen

/**
 * High level composable that coordinates the routes and screens for the Home flow
 *
 * @param onLogoutClicked Callback that is executed when a logout event has successfully completed
 */
@Composable
fun HomeFlow(onLogoutClicked: () -> Unit) {
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
                        }
                    )
                }
            }
        }
    ) {
        NavHost(bottomNavController, startDestination = HomeDestinations.Chores.route) {
            composable(HomeDestinations.Chores.route) { HomeChoresScreen() }
            composable(HomeDestinations.Calendar.route) { CalendarScreen() }
            composable(HomeDestinations.Groceries.route) { GroceriesScreen() }
            composable(HomeDestinations.Settings.route) { SettingsScreen(onLogoutClicked = onLogoutClicked) }
        }
    }
}

sealed class HomeDestinations(val route: String, @StringRes val resourceId: Int, val icon: VectorAsset) {
    object Chores : HomeDestinations("chores", R.string.chores, Icons.Filled.CleaningServices)
    object Calendar : HomeDestinations("calendar", R.string.calendar, Icons.Filled.Event)
    object Groceries : HomeDestinations("groceries", R.string.groceries, Icons.Filled.LocalGroceryStore)
    object Settings : HomeDestinations("settings", R.string.settings, Icons.Filled.Settings)
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeFlow(onLogoutClicked = {})
}
