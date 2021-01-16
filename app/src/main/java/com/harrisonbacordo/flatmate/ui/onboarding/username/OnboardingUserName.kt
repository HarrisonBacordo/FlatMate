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
package com.harrisonbacordo.flatmate.ui.onboarding.username

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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focusRequester
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.harrisonbacordo.flatmate.ui.composables.textfield.NameState
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextFieldState
import com.harrisonbacordo.flatmate.ui.composables.textfield.AlphaTextInput
import com.harrisonbacordo.flatmate.ui.onboarding.OnboardingHeaderText
import com.harrisonbacordo.flatmate.ui.theme.FlatmateOnboardingTheme

@Composable
fun OnboardingUserName(onNextClicked: () -> Unit) {
    val viewModel: OnboardingUserNameViewModel = ViewModelProvider(AmbientContext.current as ViewModelStoreOwner).get(OnboardingUserNameViewModel::class.java)
    val firstNameState = remember { NameState() }
    val lastNameState = remember { NameState() }
    OnboardingUserNameScreen(
        firstNameState = firstNameState,
        lastNameState = lastNameState,
        errorMessage = viewModel.errorMessage,
        onNextClicked = { viewModel.executeSaveUserNameFlow(firstNameState, lastNameState, onNextClicked) },
    )
}

@Composable
private fun OnboardingUserNameScreen(
    firstNameState: TextFieldState = remember { NameState() },
    lastNameState: TextFieldState = remember { NameState() },
    errorMessage: String,
    onNextClicked: () -> Unit,
) {
    Column(
        Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val lastNameFocusRequest = remember { FocusRequester() }
        OnboardingHeaderText(text = "Welcome! What's your name?")
        AlphaTextInput(
            value = firstNameState.text,
            hint = "First Name",
            onValueChange = firstNameState::updateText,
            Modifier.fillMaxWidth(),
            imeAction = ImeAction.Next,
            onImeAction = lastNameFocusRequest::requestFocus,
        )
        AlphaTextInput(
            value = lastNameState.text,
            hint = "Last Name",
            onValueChange = lastNameState::updateText,
            Modifier.fillMaxWidth().focusRequester(lastNameFocusRequest),
        )
        if (errorMessage.isNotEmpty()) Text(errorMessage, color = MaterialTheme.colors.error)
        Spacer(Modifier.padding(top = 8.dp))
        Button(onClick = onNextClicked, Modifier.fillMaxWidth()) {
            Text(text = "Next")
        }
    }
}

@Preview(name = "Onboarding User Name Screen")
@Composable
private fun PreviewOnboardingUserNameScreen() {
    FlatmateOnboardingTheme {
        Scaffold {
            OnboardingUserNameScreen(errorMessage = "Error", onNextClicked = {})
        }
    }
}
