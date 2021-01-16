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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.harrisonbacordo.flatmate.data.models.User
import com.harrisonbacordo.flatmate.ui.auth.AuthFlow
import com.harrisonbacordo.flatmate.ui.home.HomeFlow
import com.harrisonbacordo.flatmate.ui.onboarding.OnboardingFlow
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
    val onboardingRoute: (user: User) -> Unit = {
        createNavRoute(
            navController,
            Destinations.Onboarding,
            "/$it",
        )
    }
    val homeRoute = { createNavRoute(navController, Destinations.Home) }
    NavHost(navController, startDestination = Destinations.Auth.name) {
        composable(Destinations.Auth.name) {
            FlatmateAuthTheme {
                AuthFlow(
                    onLoginSuccessful = homeRoute,
                    onCreateNewAccountSuccessful = onboardingRoute
                )
            }
        }
        composable(Destinations.Onboarding.name.plus("/{user}"), arguments = listOf(navArgument("user") { type = NavType.SerializableType(User::class.java) })) {
            FlatmateOnboardingTheme {
                val user = it.arguments?.getSerializable("user") as User
                /* TODO
                   Need to pass the user object instead of the user id. This is a temporary fix since Kotlin/Compose does not yet support
                   serializables/parcelables to be passed since they don't support default values. Keep an eye on that
                 */
                OnboardingFlow(user.id, onOnboardingComplete = homeRoute)
            }
        }
        composable(Destinations.Home.name) {
            FlatMateHomeTheme(darkTheme = false) {
                HomeFlow(onLogoutClicked = authRoute)
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
    Auth,
    Onboarding,
    Home,
}
