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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ContextAmbient
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.ui.composables.textfield.HiddenTextInput
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextInput

/**
 * High-level composable that holds the state and high-level UI composable of the auth create new account screen
 *
 * @param onCreateNewAccountSuccessful Callback that is executed when an account is successfully created
 */
@Composable
fun AuthCreateNewAccount(onCreateNewAccountSuccessful: () -> Unit) {
    val viewModel: AuthCreateNewAccountViewModel = ViewModelProvider(ContextAmbient.current as ViewModelStoreOwner).get(AuthCreateNewAccountViewModel::class.java)
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    CreateNewAccountScreen(
        email,
        password,
        viewModel.errorMessage,
        setEmail,
        setPassword,
        { viewModel.executeCreateNewAccountFlow(email, password, onCreateNewAccountSuccessful) }
    )
}

/**
 * High-level composable that displays the auth create new account screen
 *
 * @param email String that represents the current state of the email text field
 * @param password String that represents the current state of the password text field
 * @param errorMessage String that represents the current state of the error message
 * @param onEmailFieldChanged Callback that is executed when a change is made to the email text field
 * @param onPasswordFieldChanged Callback that is executed when a change is made to the password text field
 * @param onFormSubmitted Callback that is executed when the form's login  button has been clicked
 */
@Composable
private fun CreateNewAccountScreen(
    email: String,
    password: String,
    errorMessage: String,
    onEmailFieldChanged: (String) -> Unit,
    onPasswordFieldChanged: (String) -> Unit,
    onFormSubmitted: () -> Unit,
) {
    Column {
        Text("Create New Account")
        TextInput(value = email, hint = "Email", onValueChange = onEmailFieldChanged)
        HiddenTextInput(value = password, hint = "Password", onValueChange = onPasswordFieldChanged)
        Button(onClick = onFormSubmitted) {
            Text("Create new account")
        }
    }
}

@Preview(name = "Create New Account Screen Preview")
@Composable
private fun PreviewAuthCreateNewAccountScreen() {
    CreateNewAccountScreen("harrisonbacordo@gmail.com", "TestTest", "Error", {}, {}, {})
}
