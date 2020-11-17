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

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.ui.theme.FlatMateTheme

/**
 * High-level composable that holds the state and high-level UI composable of the auth landing screen
 */
@Composable
fun AuthLanding(onCreateNewAccountClicked: () -> Unit, onLoginClicked: () -> Unit) {
    val viewModel: AuthLandingViewModel = viewModel()
    AuthLandingScreen(onCreateNewAccountClicked, onLoginClicked)
}

/**
 * High-level component that displays the auth landing screen
 */
@Composable
private fun AuthLandingScreen(onCreateNewAccountClicked: () -> Unit, onLoginClicked: () -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        Text(text = "Landing")
        Button(onClick = onCreateNewAccountClicked) {
            Text("Create New Account")
        }
        Button(onClick = onLoginClicked) {
            Text("Log in")
        }
        Button(onClick = {}) {
            Text("Continue with Facebook")
        }
        Button(onClick = {}) {
            Text("Continue with Google")
        }
    }
}

@Preview(name = "Auth Landing Light Theme")
@Composable
private fun AuthLandingScreenPreview() {
    FlatMateTheme {
        Scaffold {
            AuthLandingScreen({}, {})
        }
    }
}

@Preview(name = "Auth Landing Dark Theme")
@Composable
private fun AuthLandingScreenPreviewDark() {
    FlatMateTheme(darkTheme = true) {
        Scaffold {
            AuthLandingScreen({}, {})
        }
    }
}
