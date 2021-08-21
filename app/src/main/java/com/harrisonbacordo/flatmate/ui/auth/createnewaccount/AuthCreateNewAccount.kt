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
package com.harrisonbacordo.flatmate.ui.auth.createnewaccount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.harrisonbacordo.flatmate.ui.auth.CompanyLogo
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.EmailState
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.EmailTextField
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.PasswordState
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.PasswordTextField
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.TextFieldState
import com.harrisonbacordo.flatmate.ui.theme.FlatmateAuthTheme
import com.harrisonbacordo.flatmate.ui.theme.typography

/**
 * High-level composable that holds the state and high-level UI composable of the auth create new account screen
 *
 * @param onCreateNewAccountSuccessful Callback that is executed when an account is successfully created
 */
@Composable
fun AuthCreateNewAccount(onCreateNewAccountSuccessful: (userId: String) -> Unit) {
    val viewModel: AuthCreateNewAccountViewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(AuthCreateNewAccountViewModel::class.java)
    val emailState = remember { EmailState() }
    val passwordState = remember { PasswordState() }
    CreateNewAccountScreen(
        emailState,
        passwordState,
        viewModel.errorMessage
    ) { viewModel.executeCreateNewAccountFlow(emailState, passwordState, onCreateNewAccountSuccessful) }
}

/**
 * High-level composable that displays the auth create new account screen
 *
 * @param emailState State that represents the email text field
 * @param passwordState State that represents the password text field
 * @param errorMessage String that represents the current state of the error message
 * @param onFormSubmitted Callback that is executed when the form's login  button has been clicked
 */
@Composable
private fun CreateNewAccountScreen(
    emailState: TextFieldState = remember { EmailState() },
    passwordState: TextFieldState = remember { PasswordState() },
    errorMessage: String,
    onFormSubmitted: () -> Unit,
) {
    val passwordFocusRequest = remember { FocusRequester() }
    CompanyLogo()
    Column(
        Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Create New Account", style = typography.h4)
        EmailTextField(
            value = emailState.text,
            onValueChange = emailState::updateText,
            Modifier.fillMaxWidth(),
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(onNext = { passwordFocusRequest.requestFocus() })
        )
        PasswordTextField(
            value = passwordState.text,
            onValueChange = passwordState::updateText,
            Modifier.fillMaxWidth().focusRequester(passwordFocusRequest)
        )
        if (errorMessage.isNotEmpty()) Text(errorMessage, color = MaterialTheme.colors.error)
        Spacer(Modifier.padding(top = 8.dp))
        Button(onClick = onFormSubmitted, Modifier.fillMaxWidth()) {
            Text("Create New Account")
        }
    }
}

@Preview(name = "Create New Account Screen")
@Composable
private fun PreviewAuthCreateNewAccountScreen() {
    FlatmateAuthTheme {
        Scaffold {
            CreateNewAccountScreen(errorMessage = "Error", onFormSubmitted = {})
        }
    }
}
