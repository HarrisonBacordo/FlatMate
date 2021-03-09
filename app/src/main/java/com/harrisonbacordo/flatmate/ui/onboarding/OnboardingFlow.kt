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
package com.harrisonbacordo.flatmate.ui.onboarding

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.harrisonbacordo.flatmate.ui.onboarding.existingflatid.OnboardingExistingFlatId
import com.harrisonbacordo.flatmate.ui.onboarding.existingflatidmanualentry.OnboardingExistingFlatIdManualEntry
import com.harrisonbacordo.flatmate.ui.onboarding.existingflatqrscanner.OnboardingExistingFlatQrScanner
import com.harrisonbacordo.flatmate.ui.onboarding.existingornewflat.OnboardingExistingOrNewFlat
import com.harrisonbacordo.flatmate.ui.onboarding.newflatname.OnboardingNewFlatName
import com.harrisonbacordo.flatmate.ui.onboarding.username.OnboardingUserName

/**
 * High level composable that coordinates the routes and screens for the Onboarding flow
 *
 * @param onOnboardingComplete Callback that is executed when onboarding has been successfully completed
 */
@Composable
fun OnboardingFlow(userId: String, onOnboardingComplete: (userId: String) -> Unit) {
    val viewModel: OnboardingFlowViewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(OnboardingFlowViewModel::class.java)
    val onboardingNavController = rememberNavController()
    val userNameRoute = { executeNavRoute(onboardingNavController, OnboardingDestinations.UserName.name) }
    val existingOrNewFlatRoute = { executeNavRoute(onboardingNavController, OnboardingDestinations.ExistingOrNewFlat.name) }
    val newFlatNameRoute = { executeNavRoute(onboardingNavController, OnboardingDestinations.NewFlatName.name) }
    val existingFlatIdRoute = { executeNavRoute(onboardingNavController, OnboardingDestinations.ExistingFlatId.name) }
    val existingFlatQrScannerRoute = { executeNavRoute(onboardingNavController, OnboardingDestinations.ExistingFlatQrScanner.name) }
    val existingFlatManualEntryIdRoute = { executeNavRoute(onboardingNavController, OnboardingDestinations.ExistingFlatIdManualEntry.name) }
    Scaffold(
        content = {
            NavHost(onboardingNavController, startDestination = OnboardingDestinations.UserName.name) {
                composable(OnboardingDestinations.UserName.name) {
                    OnboardingUserName(userId = userId, onNextClicked = existingOrNewFlatRoute)
                }
                composable(OnboardingDestinations.ExistingOrNewFlat.name) {
                    OnboardingExistingOrNewFlat(onNewFlatClicked = newFlatNameRoute, onExistingFlatClicked = existingFlatIdRoute, onBackClicked = userNameRoute)
                }
                composable(OnboardingDestinations.NewFlatName.name) {
                    OnboardingNewFlatName(userId = userId, onFlatSuccessfullyCreated = onOnboardingComplete, onBackClicked = existingOrNewFlatRoute)
                }
                composable(OnboardingDestinations.ExistingFlatId.name) {
                    OnboardingExistingFlatId(userId = userId, onQrCodeClicked = existingFlatQrScannerRoute, onManualEntryClicked = existingFlatManualEntryIdRoute, onBackClicked = existingOrNewFlatRoute)
                }
                composable(OnboardingDestinations.ExistingFlatQrScanner.name) {
                    OnboardingExistingFlatQrScanner(userId = userId, onFlatSuccessfullyJoined = onOnboardingComplete, onBackClicked = existingFlatIdRoute)
                }
                composable(OnboardingDestinations.ExistingFlatIdManualEntry.name) {
                    OnboardingExistingFlatIdManualEntry(userId = userId, onFlatSuccessfullyJoined = onOnboardingComplete, onBackClicked = existingFlatIdRoute)
                }
            }
        }
    )
}

/**
 * Executes onboarding-specific navigation to [route] via [navController]. Clears the backstack if [route] is [OnboardingDestinations.UserName]
 * to ensure that the application closes if back is pressed from there.
 *
 * @param navController [NavController] to navigate with
 * @param route [String] that identifies the route
 */
private fun executeNavRoute(navController: NavController, route: String) {
    if (route == OnboardingDestinations.UserName.name) {
        navController.popBackStack()
    }
    navController.navigate(route)
}

enum class OnboardingDestinations {
    UserName,
    ExistingOrNewFlat,
    NewFlatName,
    ExistingFlatQrScanner,
    ExistingFlatId,
    ExistingFlatIdManualEntry,
}
