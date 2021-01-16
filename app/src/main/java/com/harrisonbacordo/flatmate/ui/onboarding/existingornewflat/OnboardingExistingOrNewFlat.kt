/*
 * Designed and developed by 2021 FlatMate (Harrison Bacordo)
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
package com.harrisonbacordo.flatmate.ui.onboarding.existingornewflat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.R
import com.harrisonbacordo.flatmate.ui.onboarding.OnboardingHeaderText
import com.harrisonbacordo.flatmate.ui.theme.AuthOnboardingScreenOption
import com.harrisonbacordo.flatmate.ui.theme.FlatmateOnboardingTheme

@Composable
fun OnboardingExistingOrNewFlat(
    onNewFlatClicked: () -> Unit,
    onExistingFlatClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    OnboardingExistingOrNewFlatScreen(onNewFlatClicked, onExistingFlatClicked, onBackClicked)
}

@Composable
private fun OnboardingExistingOrNewFlatScreen(
    onNewFlatClicked: () -> Unit,
    onExistingFlatClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    OnboardingHeaderText("Are you creating a new flat or joining an existing flat?")
    Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuthOnboardingScreenOption(
            title = "Create a new flat",
            leadingIcon = R.drawable.ic_create_new_flat_24dp,
            modifier = Modifier.clickable(onClick = onNewFlatClicked)
        )
        AuthOnboardingScreenOption(
            title = "Join an existing flat",
            leadingIcon = R.drawable.ic_join_existing_flat_24dp,
            modifier = Modifier.clickable(onClick = onExistingFlatClicked)
        )
        Button(onClick = onBackClicked) {
            Text(text = "Back")
        }
    }
}

@Preview(name = "Onboarding Existing or New Flat Screen")
@Composable
fun PreviewOnboardingExistingOrNewFlatScreen() {
    FlatmateOnboardingTheme {
        Scaffold {
            OnboardingExistingOrNewFlatScreen({}, {}, {})
        }
    }
}
