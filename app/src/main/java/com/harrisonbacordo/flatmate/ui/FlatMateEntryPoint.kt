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
package com.harrisonbacordo.flatmate.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.ui.auth.AuthFlow
import com.harrisonbacordo.flatmate.ui.home.HomeFlow
import com.harrisonbacordo.flatmate.ui.onboarding.OnboardingFlow
import com.harrisonbacordo.flatmate.ui.theme.FlatMateTheme

/**
 * Entrypoint for the FlatMate application
 */
@Preview
@Composable
fun FlatMateEntryPoint() {
    val navController = rememberNavController()
    val authRoute = { createNavRoute(navController, Destinations.Auth.name) }
    val onboardingRoute = { createNavRoute(navController, Destinations.Onboarding.name) }
    val homeRoute = { createNavRoute(navController, Destinations.Home.name) }
    NavHost(navController, startDestination = Destinations.Auth.name) {
        composable(Destinations.Auth.name) {
            FlatMateTheme(darkTheme = false) {
                AuthFlow(
                    onLoginSuccessful = homeRoute,
                    onCreateNewAccountSuccessful = onboardingRoute
                )
            }
        }
        composable(Destinations.Onboarding.name) {
            FlatMateTheme(darkTheme = false) {
                OnboardingFlow(onOnboardingComplete = homeRoute)
            }
        }
        composable(Destinations.Home.name) {
            FlatMateTheme(darkTheme = false) {
                HomeFlow(onLogoutClicked = authRoute)
            }
        }
    }
}

/**
 * Executes app-wide navigation to [route] via [navController]. Clears the backstack
 * to ensure that the application closes if back is pressed from the root of any flow.
 *
 * @param navController [NavController] to navigate with
 * @param route [String] that identifies the route
 */
private fun createNavRoute(navController: NavController, route: String) {
    navController.popBackStack()
    navController.navigate(route)
}

/**
 * Identifies the different destinations that can be reached app-wide
 */
enum class Destinations {
    Auth,
    Onboarding,
    Home,
}
