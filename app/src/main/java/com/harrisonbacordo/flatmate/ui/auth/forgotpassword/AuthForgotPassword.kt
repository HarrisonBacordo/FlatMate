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
package com.harrisonbacordo.flatmate.ui.auth.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.EmailTextField
import com.harrisonbacordo.flatmate.ui.theme.FlatmateAuthTheme

/**
 * High-level composable that holds the state and high-level UI composable of the auth forgot password screen
 */
@Composable
fun AuthForgotPassword() {
    val viewModel: AuthForgotPasswordViewModel = viewModel()
    AuthForgotPasswordScreen(
        viewModel.email,
        viewModel::onEmailFieldChanged,
        viewModel::executeResetPasswordFlow
    )
}

/**
 * High-level composable that displays the auth forgot password screen
 *
 * @param email String that represents the current state of the email text field
 * @param onEmailFieldChanged Callback that is executed when a change is made to the email text field
 * @param onFormSubmitted Callback that is executed when the form's submit button has been clicked
 */
@Composable
private fun AuthForgotPasswordScreen(
    email: String,
    onEmailFieldChanged: (String) -> Unit,
    onFormSubmitted: () -> Unit
) {
    Column {
        Text("Forgot password Screen")
        EmailTextField(value = email, onValueChange = onEmailFieldChanged)
        Button(onClick = onFormSubmitted) {
            Text("Submit Email")
        }
    }
}

@Preview
@Composable
private fun PreviewAuthForgotPasswordScreen() {
    FlatmateAuthTheme {
        Scaffold {
            AuthForgotPasswordScreen("harrisonbacordo@gmail.com", {}, {})
        }
    }
}
