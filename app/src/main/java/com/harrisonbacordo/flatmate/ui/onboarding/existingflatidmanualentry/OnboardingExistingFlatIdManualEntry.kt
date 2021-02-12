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
package com.harrisonbacordo.flatmate.ui.onboarding.existingflatidmanualentry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.harrisonbacordo.flatmate.ui.composables.textfield.NameState
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextFieldState
import com.harrisonbacordo.flatmate.ui.composables.textfield.AlphaTextField
import com.harrisonbacordo.flatmate.ui.onboarding.OnboardingHeaderText
import com.harrisonbacordo.flatmate.ui.theme.FlatmateOnboardingTheme

@Composable
fun OnboardingExistingFlatIdManualEntry(userId: String, onFlatSuccessfullyJoined: (userId: String) -> Unit, onBackClicked: () -> Unit) {
    val viewModel: OnboardingExistingFlatIdManualEntryViewModel = ViewModelProvider(AmbientContext.current as ViewModelStoreOwner).get(OnboardingExistingFlatIdManualEntryViewModel::class.java)
    val flatIdState = remember { NameState() }
    OnboardingExistingFlatIdManualEntryScreen(
        flatIdState = flatIdState,
        errorMessage = "",
        onFlatIdSubmitted = { viewModel.executeJoinExistingFlatFlow(flatIdState = flatIdState, userId = userId, onFlatSuccessfullyJoined = onFlatSuccessfullyJoined) },
        onBackClicked = onBackClicked
    )
}

@Composable
private fun OnboardingExistingFlatIdManualEntryScreen(
    flatIdState: TextFieldState = remember { NameState() },
    errorMessage: String,
    onFlatIdSubmitted: () -> Unit,
    onBackClicked: () -> Unit,
) {
    Column(
        Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnboardingHeaderText(text = "What's your flat's ID?")
        AlphaTextField(
            value = flatIdState.text,
            hint = "Flat ID",
            onValueChange = flatIdState::updateText,
            Modifier.fillMaxWidth()
        )
        if (errorMessage.isNotEmpty()) Text(errorMessage, color = MaterialTheme.colors.error)
        Spacer(Modifier.padding(top = 8.dp))
        Button(onClick = onFlatIdSubmitted, Modifier.fillMaxWidth()) {
            Text(text = "Submit")
        }
        Button(onClick = onBackClicked, Modifier.fillMaxWidth()) {
            Text(text = "Back")
        }
    }
}

@Preview(name = "Onboarding Existing Flat ID Manual Entry")
@Composable
private fun PreviewOnboardingExistingFlatIdManualEntryScreen() {
    FlatmateOnboardingTheme {
        Scaffold {
            OnboardingExistingFlatIdManualEntryScreen(errorMessage = "Error", onFlatIdSubmitted = {}, onBackClicked = {})
        }
    }
}
