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

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview

/**
 * High level composable that coordinates the routes and screens for the Onboarding flow
 *
 * @param onOnboardingComplete Callback that is executed when onboarding has been successfully completed
 */
@Composable
fun OnboardingFlow(onOnboardingComplete: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Flatmate") })
        },
        bodyContent = {
            Column {
                Text("Onboarding")
                Button(onClick = onOnboardingComplete) {
                    Text("Complete onboarding")
                }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewOnboarding() {
    OnboardingFlow(onOnboardingComplete = {})
}
