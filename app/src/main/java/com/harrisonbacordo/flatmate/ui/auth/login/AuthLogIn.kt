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
package com.harrisonbacordo.flatmate.ui.auth.login

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.ui.auth.AuthHiddenTextInput
import com.harrisonbacordo.flatmate.ui.auth.AuthTextInput

/**
 * High-level composable that holds the state and high-level UI composable of the login screen
 *
 * @param onLoginSuccessful Callback that is executed when a login is successful
 * @param onForgotPasswordClicked Callback that is executed when the forgot password button is clicked
 */
@Composable
fun AuthLoginScreen(onLoginSuccessful: () -> Unit, onForgotPasswordClicked: () -> Unit) {
    val viewModel: AuthLoginViewModel = viewModel()
    LoginScreen(
        viewModel.email,
        viewModel.password,
        viewModel::onEmailFieldChanged,
        viewModel::onPasswordFieldChanged,
        viewModel::executeLoginFlow,
        onForgotPasswordClicked
    )
}

/**
 * High-level composable that displays the login screen
 *
 * @param email String that represents the current state of the email text field
 * @param password String that represents the current state of the password text field
 * @param onEmailFieldChanged Callback that is executed when a change is made to the email text field
 * @param onPasswordFieldChanged Callback that is executed when a change is made to the password text field
 * @param onFormSubmitted Callback that is executed when the form's login  button has been clicked
 */
@Composable
private fun LoginScreen(
    email: String,
    password: String,
    onEmailFieldChanged: (String) -> Unit,
    onPasswordFieldChanged: (String) -> Unit,
    onFormSubmitted: () -> Unit,
    onForgotPasswordClicked: () -> Unit

) {
    Column {
        Text("Login")
        AuthTextInput(value = email, hint = "Email", onValueChange = onEmailFieldChanged)
        AuthHiddenTextInput(value = password, hint = "Password", onValueChange = onPasswordFieldChanged)
        Button(onClick = onFormSubmitted) {
            Text("Login")
        }
        Button(onForgotPasswordClicked) {
            Text("Forgot Password")
        }
    }
}

@Preview
@Composable
private fun PreviewAuthLoginForm() {
    LoginScreen("harrisonbacordo@gmail.com", "TestTest", {}, {}, {}, {})
}
