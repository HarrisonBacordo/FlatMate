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

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ContextAmbient
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.ui.composables.textfield.EmailState
import com.harrisonbacordo.flatmate.ui.composables.textfield.HiddenTextInput
import com.harrisonbacordo.flatmate.ui.composables.textfield.PasswordState
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextFieldState
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextInput
import com.harrisonbacordo.flatmate.ui.theme.FlatMateTheme

/**
 * High-level composable that holds the state and high-level UI composable of the auth create new account screen
 *
 * @param onCreateNewAccountSuccessful Callback that is executed when an account is successfully created
 */
@Composable
fun AuthCreateNewAccount(onCreateNewAccountSuccessful: () -> Unit) {
    val viewModel: AuthCreateNewAccountViewModel = ViewModelProvider(ContextAmbient.current as ViewModelStoreOwner).get(AuthCreateNewAccountViewModel::class.java)
    val emailState = remember { EmailState() }
    val passwordState = remember { PasswordState() }
    CreateNewAccountScreen(
        emailState,
        passwordState,
        viewModel.errorMessage
    ) { viewModel.executeCreateNewAccountFlow(emailState.text, passwordState.text, onCreateNewAccountSuccessful) }
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
    Column {
        Text("Create New Account")
        TextInput(value = emailState.text, hint = "Email", onValueChange = emailState::updateText)
        HiddenTextInput(value = passwordState.text, hint = "Password", onValueChange = passwordState::updateText)
        Button(onClick = onFormSubmitted) {
            Text("Create new account")
        }
    }
}

@Preview(name = "Create New Account Light Theme")
@Composable
private fun PreviewAuthCreateNewAccountScreen() {
    FlatMateTheme {
        Scaffold {
            CreateNewAccountScreen(errorMessage = "Error", onFormSubmitted = {})
        }
    }
}

@Preview(name = "Create New Account Dark Theme")
@Composable
private fun PreviewAuthCreateNewAccountScreenDark() {
    FlatMateTheme(darkTheme = true) {
        Scaffold {
            CreateNewAccountScreen(errorMessage = "Error", onFormSubmitted = {})
        }
    }
}
