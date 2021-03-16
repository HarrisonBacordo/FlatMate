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
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import kotlinx.coroutines.launch

/**
 * High level composable that coordinates the routes and screens for the Home flow
 *
 * @param onLogoutClicked Callback that is executed when a logout event has successfully completed
 */
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun HomeFlow(userId: String, onLogoutClicked: () -> Unit) {
    val homeNavController = rememberNavController()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scopedCoroutine = rememberCoroutineScope()
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.get(KEY_ROUTE)

    val flatmatesRoute = { executeNavRoute(homeNavController, HomeDestinations.Flatmates.route) }
    val newChoreRoute = { executeNavRoute(homeNavController, HomeDestinations.NewChore.route) }
    val newEventRoute = { executeNavRoute(homeNavController, HomeDestinations.NewEvent.route) }
    val newGroceryRoute = { executeNavRoute(homeNavController, HomeDestinations.NewGrocery.route) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Flatmate") },
                navigationIcon = {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                scopedCoroutine.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                            .padding(start = 16.dp)
                    )
                }
            )
        },
        drawerContent = {
            Text(text = "Flatmate", style = MaterialTheme.typography.h5, modifier = Modifier.padding(16.dp))
            homeDestinations.forEach {
                DrawerRow(it, selected = currentRoute == it.route) {
                    if (currentRoute != it.route) {
                        homeNavController.popBackStack(homeNavController.graph.startDestination, false)
                        homeNavController.navigate(it.route)
                        scopedCoroutine.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            if (homeDestinations.minus(HomeDestinations.Settings).find { it.route == currentRoute } != null) {
                var floatingButtonText = ""
                var onFabClickedDestination: HomeDestinations? = null
                when (currentRoute) {
                    HomeDestinations.Chores.route -> {
                        floatingButtonText = "Add Chore"
                        onFabClickedDestination = HomeDestinations.NewChore
                    }
                    HomeDestinations.Calendar.route -> {
                        floatingButtonText = "Add Event"
                        onFabClickedDestination = HomeDestinations.NewEvent
                    }
                    HomeDestinations.Groceries.route -> {
                        floatingButtonText = "Add Grocery"
                        onFabClickedDestination = HomeDestinations.NewGrocery
                    }
                    else -> {
                    }
                }
                ExtendedFloatingActionButton(
                    onClick = { homeNavController.navigate(onFabClickedDestination!!.route) },
                    text = { Text(floatingButtonText) },
                    icon = { Icon(Icons.Default.Add, contentDescription = null) },
                    modifier = Modifier
                        .animateContentSize()
                        .padding(8.dp),
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp, pressedElevation = 6.dp),
                )
            }
        },
        scaffoldState = scaffoldState,
    ) {
        NavHost(homeNavController, startDestination = HomeDestinations.Chores.route) {
            composable(HomeDestinations.Chores.route) { HomeChores(onNewChoreClicked = newChoreRoute) }
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

@ExperimentalMaterialApi
@Composable
private fun DrawerRow(homeDestination: HomeDestinations, selected: Boolean, onClick: () -> Unit) {
    val background = if (selected) MaterialTheme.colors.primary.copy(alpha = 0.12f) else Color.Transparent
    val foregroundColor = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
    ListItem(
        modifier = Modifier
            .clickable(onClick = onClick)
            .background(background)
    ) {
        Row {
            Icon(homeDestination.icon!!, contentDescription = null, tint = foregroundColor)
            Spacer(Modifier.padding(horizontal = 4.dp))
            Text(color = foregroundColor, text = stringResource(id = homeDestination.resourceId!!))
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

private val homeDestinations = listOf(
    HomeDestinations.Chores,
    HomeDestinations.Calendar,
    HomeDestinations.Groceries,
    HomeDestinations.Settings
)

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeFlow("", onLogoutClicked = {})
}
