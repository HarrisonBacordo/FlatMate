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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.harrisonbacordo.flatmate.ui.auth.AuthFlow
import com.harrisonbacordo.flatmate.ui.home.HomeFlow
import com.harrisonbacordo.flatmate.ui.onboarding.OnboardingFlow
import com.harrisonbacordo.flatmate.ui.splash.Splash
import com.harrisonbacordo.flatmate.ui.theme.FlatMateHomeTheme
import com.harrisonbacordo.flatmate.ui.theme.FlatmateAuthTheme
import com.harrisonbacordo.flatmate.ui.theme.FlatmateOnboardingTheme

/**
 * Entrypoint for the FlatMate application
 */
@Preview
@Composable
fun FlatMateEntryPoint() {
    val navController = rememberNavController()
    val authRoute = { createNavRoute(navController, Destinations.Auth) }
    val onboardingRoute: (userId: String) -> Unit = { createNavRoute(navController, Destinations.Onboarding, "/$it") }
    val homeRoute: (userId: String) -> Unit = { createNavRoute(navController, Destinations.Home, "/$it") }
    NavHost(navController, startDestination = Destinations.Splash.name) {
        composable(Destinations.Splash.name) {
            FlatmateAuthTheme {
                Splash(
                    onRedirectToAuth = authRoute,
                    onRedirectToHome = homeRoute
                )
            }
        }
        composable(Destinations.Auth.name) {
            FlatmateAuthTheme {
                AuthFlow(
                    onLoginSuccessful = homeRoute,
                    onCreateNewAccountSuccessful = onboardingRoute
                )
            }
        }
        composable(Destinations.Onboarding.name.plus("/{userId}")) {
            FlatmateOnboardingTheme {
                val userId = it.arguments?.getSerializable("userId") as String
                OnboardingFlow(userId, onOnboardingComplete = homeRoute)
            }
        }
        composable(Destinations.Home.name.plus("/{userId}")) {
            FlatMateHomeTheme(darkTheme = false) {
                val userId = it.arguments?.getSerializable("userId") as String
                HomeFlow(userId, onLogoutClicked = authRoute)
            }
        }
    }
}

/**
 * Executes app-wide navigation to [destination] via [navController]. Clears the backstack
 * to ensure that the application closes if back is pressed from the root of any flow.
 *
 * @param navController [NavController] to navigate with
 * @param destination Destination that identifies the route
 */
private fun createNavRoute(navController: NavController, destination: Destinations, argument: String? = null) {
    navController.popBackStack()
    var route = destination.name
    argument?.let {
        route = route.plus(argument)
    }
    navController.navigate(route)
}

/**
 * Identifies the different destinations that can be reached app-wide
 */
enum class Destinations {
    Splash,
    Auth,
    Onboarding,
    Home,
}
