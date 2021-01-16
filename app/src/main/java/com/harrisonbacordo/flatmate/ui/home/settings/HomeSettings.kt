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
package com.harrisonbacordo.flatmate.ui.home.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

/**
 * High-level composable that holds the state and high-level UI composable of the home settings screen
 */
@Composable
fun HomeSettings(onLogoutSuccessful: () -> Unit) {
    val viewModel = ViewModelProvider(AmbientContext.current as ViewModelStoreOwner).get(HomeSettingsViewModel::class.java)
    HomeSettingsScreen { viewModel.signCurrentUserOut(onLogoutSuccessful) }
}

/**
 * High-level composable that displays the home settings screen
 */
@Composable
private fun HomeSettingsScreen(onLogoutClicked: () -> Unit) {
    Row {
        Text("Settings")
        Button(onClick = onLogoutClicked) {
            Text("Back to auth")
        }
    }
}

@Preview
@Composable
private fun PreviewSettingsScreen() {
    HomeSettingsScreen {}
}
