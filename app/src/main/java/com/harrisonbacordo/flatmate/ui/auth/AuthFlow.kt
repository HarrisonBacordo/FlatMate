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

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.harrisonbacordo.flatmate.data.models.User
import com.harrisonbacordo.flatmate.ui.auth.createnewaccount.AuthCreateNewAccount
import com.harrisonbacordo.flatmate.ui.auth.debug.AuthDebug
import com.harrisonbacordo.flatmate.ui.auth.forgotpassword.AuthForgotPassword
import com.harrisonbacordo.flatmate.ui.auth.landing.AuthLanding
import com.harrisonbacordo.flatmate.ui.auth.login.AuthLogin

/**
 * High level composable that coordinates the routes and screens for the Auth flow
 *
 * @param onLoginSuccessful Callback that is executed when a login is successful
 * @param onCreateNewAccountSuccessful Callback that is executed when an account is successfully created
 */
@Composable
fun AuthFlow(onLoginSuccessful: (userId: String) -> Unit, onCreateNewAccountSuccessful: (userId: String) -> Unit) {
    val authNavController = rememberNavController()
    val landingRoute = { executeNavRoute(authNavController, AuthDestinations.Landing.name) }
    val loginRoute = { executeNavRoute(authNavController, AuthDestinations.LogIn.name) }
    val createNewAccountRoute = { executeNavRoute(authNavController, AuthDestinations.CreateNewAccount.name) }
    val forgotPasswordRoute = { executeNavRoute(authNavController, AuthDestinations.ForgotPassword.name) }
    val debugRoute = { executeNavRoute(authNavController, AuthDestinations.Debug.name) }
    Scaffold(
        content = {
            NavHost(authNavController, startDestination = AuthDestinations.Landing.name) {
                composable(AuthDestinations.Landing.name) {
                    AuthLanding(onCreateNewAccountClicked = createNewAccountRoute, onLoginClicked = loginRoute, onDebugClicked = debugRoute)
                }
                composable(AuthDestinations.CreateNewAccount.name) {
                    AuthCreateNewAccount(onCreateNewAccountSuccessful = onCreateNewAccountSuccessful)
                }
                composable(AuthDestinations.LogIn.name) {
                    AuthLogin(onLoginSuccessful = onLoginSuccessful, onForgotPasswordClicked = forgotPasswordRoute)
                }
                composable(AuthDestinations.ForgotPassword.name) {
                    AuthForgotPassword()
                }
                composable(AuthDestinations.Debug.name) {
                    AuthDebug()
                }
            }
        }
    )
}

/**
 * Executes auth-specific navigation to [route] via [navController]. Clears the backstack if [route] is [AuthDestinations.Landing]
 * to ensure that the application closes if back is pressed from there.
 *
 * @param navController [NavController] to navigate with
 * @param route [String] that identifies the route
 */
private fun executeNavRoute(navController: NavController, route: String) {
    if (route == AuthDestinations.Landing.name) {
        navController.popBackStack()
    }
    navController.navigate(route)
}

/**
 * Identifies the different destinations that can be reached from within [AuthFlow]
 */
enum class AuthDestinations {
    Landing,
    CreateNewAccount,
    LogIn,
    ForgotPassword,
    Debug,
}
