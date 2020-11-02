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
package com.harrisonbacordo.flatmate.ui.auth

import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.ui.auth.forgotpassword.AuthForgotPasswordScreen
import com.harrisonbacordo.flatmate.ui.auth.landing.AuthLandingScreen
import com.harrisonbacordo.flatmate.ui.auth.login.AuthLoginScreen
import com.harrisonbacordo.flatmate.ui.auth.signup.AuthCreateNewAccountScreen
import com.harrisonbacordo.flatmate.ui.theme.FlatMateTheme

@Composable
fun AuthScreen(onLoginSuccessful: () -> Unit, onCreateNewAccountSuccessful: () -> Unit) {
    val authNavController = rememberNavController()
    val landingRoute = { createNavRoute(authNavController, AuthDestinations.Landing.name) }
    val loginRoute = { createNavRoute(authNavController, AuthDestinations.LogIn.name) }
    val createNewAccountRoute = { createNavRoute(authNavController, AuthDestinations.CreateNewAccount.name) }
    val forgotPasswordRoute = { createNavRoute(authNavController, AuthDestinations.ForgotPassword.name) }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Flatmate") })
        },
        bodyContent = {
            NavHost(authNavController, startDestination = AuthDestinations.Landing.name) {
                composable(AuthDestinations.Landing.name) {
                    FlatMateTheme(darkTheme = false) {
                        AuthLandingScreen(onCreateNewAccountClicked = createNewAccountRoute, onLoginClicked = loginRoute)
                    }
                }
                composable(AuthDestinations.CreateNewAccount.name) {
                    FlatMateTheme(darkTheme = false) {
                        AuthCreateNewAccountScreen(onCreateNewAccountClicked = onCreateNewAccountSuccessful)
                    }
                }
                composable(AuthDestinations.LogIn.name) {
                    FlatMateTheme(darkTheme = false) {
                        AuthLoginScreen(onLoginClicked = onLoginSuccessful, onForgotPasswordClicked = forgotPasswordRoute)
                    }
                }
                composable(AuthDestinations.ForgotPassword.name) {
                    FlatMateTheme(darkTheme = false) {
                        AuthForgotPasswordScreen()
                    }
                }
            }
        }
    )
}

private fun createNavRoute(navController: NavController, route: String) {
    if (route == AuthDestinations.Landing.name) {
        navController.popBackStack()
    }
    navController.navigate(route)
}

enum class AuthDestinations {
    Landing,
    CreateNewAccount,
    LogIn,
    ForgotPassword,
}

@Preview
@Composable
private fun PreviewAuthScreen() {
    AuthScreen(onLoginSuccessful = {}, onCreateNewAccountSuccessful = {})
}
