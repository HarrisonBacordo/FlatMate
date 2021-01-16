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
package com.harrisonbacordo.flatmate.ui.auth.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.viewModel
import com.harrisonbacordo.flatmate.R
import com.harrisonbacordo.flatmate.ui.auth.CompanyLogo
import com.harrisonbacordo.flatmate.ui.theme.AuthOnboardingScreenOption
import com.harrisonbacordo.flatmate.ui.theme.FlatmateAuthTheme
import com.harrisonbacordo.flatmate.ui.theme.facebookBlue

/**
 * High-level composable that holds the state and high-level UI composable of the auth landing screen
 */
@Composable
fun AuthLanding(onCreateNewAccountClicked: () -> Unit, onLoginClicked: () -> Unit) {
    val viewModel: AuthLandingViewModel = viewModel()
    AuthLandingScreen(onCreateNewAccountClicked, onLoginClicked, viewModel::attemptLoginWithFacebook, viewModel::attemptLoginWithGoogle)
}

/**
 * High-level component that displays the auth landing screen
 */
@Composable
private fun AuthLandingScreen(
    onCreateNewAccountClicked: () -> Unit,
    onLoginClicked: () -> Unit,
    onContinueWithFacebookClicked: () -> Unit,
    onContinueWithGoogleClicked: () -> Unit
) {
    CompanyLogo()
    Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuthOnboardingScreenOption(
            title = "Create New Account",
            leadingIcon = R.drawable.ic_sign_up_24dp,
            modifier = Modifier.clickable(onClick = onCreateNewAccountClicked)
        )
        AuthOnboardingScreenOption(
            title = "Continue With Email",
            leadingIcon = R.drawable.ic_email_24dp,
            modifier = Modifier.clickable(onClick = onLoginClicked)
        )
        AuthOnboardingScreenOption(
            title = "Continue With Facebook",
            leadingIcon = R.drawable.ic_email_24dp,
            Modifier.background(facebookBlue).clickable(onClick = onContinueWithFacebookClicked)
        )
        AuthOnboardingScreenOption(
            title = "Continue With Google",
            leadingIcon = R.drawable.ic_email_24dp,
            modifier = Modifier.background(Color.White).clickable(onClick = onContinueWithGoogleClicked),
            contentColor = Color.Black
        )
    }
}

@Preview(name = "Auth Landing Theme")
@Composable
private fun AuthLandingScreenPreview() {
    FlatmateAuthTheme {
        Scaffold {
            AuthLandingScreen({}, {}, {}, {})
        }
    }
}
