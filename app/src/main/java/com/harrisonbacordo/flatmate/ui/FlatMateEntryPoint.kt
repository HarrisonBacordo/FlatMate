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

import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.ui.auth.landing.LandingScreen
import com.harrisonbacordo.flatmate.ui.home.HomeScreen
import com.harrisonbacordo.flatmate.ui.onboarding.OnboardingScreen
import com.harrisonbacordo.flatmate.ui.theme.FlatMateTheme

@Preview
@Composable
fun FlatMateEntryPoint() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Destinations.Auth.name) {
        composable(Destinations.Auth.name) {
            FlatMateTheme(darkTheme = false) {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Flatmate") })
                    },
                    bodyContent = { LandingScreen(navController) }
                )
            }
        }
        composable(Destinations.Onboarding.name) {
            FlatMateTheme(darkTheme = false) {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Flatmate") })
                    },
                    bodyContent = { OnboardingScreen(navController) }
                )
            }
        }
        composable(Destinations.Home.name) {
            FlatMateTheme(darkTheme = false) {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Flatmate") })
                    },
                    bodyContent = { HomeScreen(navController) }
                )
            }
        }
    }

}

enum class Destinations {
    Auth,
    Onboarding,
    Home,
}
