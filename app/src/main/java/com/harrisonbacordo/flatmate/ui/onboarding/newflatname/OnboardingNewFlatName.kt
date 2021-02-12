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
package com.harrisonbacordo.flatmate.ui.onboarding.newflatname

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.input.KeyboardCapitalization
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
fun OnboardingNewFlatName(userId: String, onFlatSuccessfullyCreated: (userId: String) -> Unit, onBackClicked: () -> Unit) {
    val viewModel: OnboardingNewFlatNameViewModel = ViewModelProvider(AmbientContext.current as ViewModelStoreOwner).get(OnboardingNewFlatNameViewModel::class.java)
    val nameState = remember { NameState() }
    OnboardingNewFlatNameScreen(
        nameState,
        { viewModel.executeCreateFlatFlow(nameState, userId, onFlatSuccessfullyCreated) },
        onBackClicked,
    )
}

@Composable
private fun OnboardingNewFlatNameScreen(
    flatNameState: TextFieldState = remember { NameState() },
    onSubmitClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    Column(
        Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OnboardingHeaderText(text = "What is the name of your new flat?")
        AlphaTextField(
            value = flatNameState.text,
            hint = "Flat name",
            onValueChange = flatNameState::updateText,
            capitalization = KeyboardCapitalization.Words,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Button(
            onClick = onSubmitClicked,
            Modifier.fillMaxWidth(),
        ) {
            Text(text = "Submit")
        }
        Button(onClick = onBackClicked) {
            Text(text = "Back")
        }
    }
}

@Preview(name = "Onboarding New Flat Name Screen")
@Composable
private fun PreviewOnboardingNewFlatScreen() {
    FlatmateOnboardingTheme {
        Scaffold {
            OnboardingNewFlatNameScreen(onSubmitClicked = {}, onBackClicked = {})
        }
    }
}