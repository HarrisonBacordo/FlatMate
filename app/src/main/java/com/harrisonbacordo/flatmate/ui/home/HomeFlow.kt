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
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.harrisonbacordo.flatmate.R
import com.harrisonbacordo.flatmate.ui.home.calendar.HomeCalendar
import com.harrisonbacordo.flatmate.ui.home.calendar.HomeNewEvent
import com.harrisonbacordo.flatmate.ui.home.chores.HomeChores
import com.harrisonbacordo.flatmate.ui.home.chores.HomeNewChore
import com.harrisonbacordo.flatmate.ui.home.groceries.HomeGroceries
import com.harrisonbacordo.flatmate.ui.home.groceries.HomeNewGrocery
import com.harrisonbacordo.flatmate.ui.home.settings.HomeSettings
import com.harrisonbacordo.flatmate.ui.home.settings.flatmates.HomeSettingsFlatmates

/**
 * High level composable that coordinates the routes and screens for the Home flow
 *
 * @param onLogoutClicked Callback that is executed when a logout event has successfully completed
 */
@Composable
fun HomeFlow(userId: String, onLogoutClicked: () -> Unit) {
    val homeNavController = rememberNavController()
    val homeDestinations = listOf(
        HomeDestinations.Chores,
        HomeDestinations.Calendar,
        HomeDestinations.Groceries,
        HomeDestinations.Settings
    )
    val flatmatesRoute = { executeNavRoute(homeNavController, HomeDestinations.Flatmates.route) }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Flatmate") })
        },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.get(KEY_ROUTE)
                homeDestinations.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon!!) },
                        label = { Text(stringResource(screen.resourceId!!)) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            homeNavController.popBackStack(homeNavController.graph.startDestination, false)
                            if (currentRoute != screen.route) {
                                homeNavController.navigate(screen.route)
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(homeNavController, startDestination = HomeDestinations.Chores.route) {
            composable(HomeDestinations.Chores.route) { HomeChores() }
            composable(HomeDestinations.Calendar.route) { HomeCalendar() }
            composable(HomeDestinations.Groceries.route) { HomeGroceries() }
            composable(HomeDestinations.Settings.route) { HomeSettings(onFlatmatesClicked = flatmatesRoute, onLogoutSuccessful = onLogoutClicked) }
            composable(HomeDestinations.NewChore.route) { HomeNewChore() }
            composable(HomeDestinations.NewEvent.route) { HomeNewEvent() }
            composable(HomeDestinations.NewGrocery.route) { HomeNewGrocery() }
            composable(HomeDestinations.Flatmates.route) { HomeSettingsFlatmates() }
        }
    }
}

/**
 * Executes home-specific navigation to [route] via [navController]. Clears the backstack if [route] is [HomeDestinations.Chores]
 * to ensure that the application closes if back is pressed from there.
 *
 * @param navController [NavController] to navigate with
 * @param route [String] that identifies the route
 */
private fun executeNavRoute(navController: NavController, route: String) {
    if (route == HomeDestinations.Chores.route) {
        navController.popBackStack()
    }
    navController.navigate(route)
}

sealed class HomeDestinations(val route: String, @StringRes val resourceId: Int? = null, val icon: ImageVector? = null) {
    object Chores : HomeDestinations("chores", R.string.chores, Icons.Filled.CleaningServices)
    object Calendar : HomeDestinations("calendar", R.string.calendar, Icons.Filled.Event)
    object Groceries : HomeDestinations("groceries", R.string.groceries, Icons.Filled.LocalGroceryStore)
    object Settings : HomeDestinations("settings", R.string.settings, Icons.Filled.Settings)
    object NewChore : HomeDestinations("newChore")
    object NewEvent : HomeDestinations("newEvent")
    object NewGrocery : HomeDestinations("newGrocery")
    object Flatmates : HomeDestinations("flatmates")
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeFlow("", onLogoutClicked = {})
}
