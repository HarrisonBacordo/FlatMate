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
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.ui.auth.AuthHiddenTextInput
import com.harrisonbacordo.flatmate.ui.auth.AuthTextInput

/**
 * High-level composable that displays the create new account screen
 *
 * @param onCreateNewAccountClicked Callback that is executed when an account is successfully created
 */
@Composable
fun AuthCreateNewAccountScreen(onCreateNewAccountClicked: () -> Unit) {
    val viewModel: AuthCreateNewAccountViewModel = viewModel()
    Column {
        Text("Create New Account")
        AuthTextInput(value = viewModel.email, hint = "Email", onValueChange = viewModel::onEmailFieldChanged)
        AuthHiddenTextInput(value = viewModel.password, hint = "Password", onValueChange = viewModel::onPasswordFieldChanged)
        Button(onClick = viewModel::executeCreateNewAccountFlow) {
            Text("Create new account")
        }
    }
}

@Preview
@Composable
private fun PreviewAuthCreateNewAccountScreen() {
    AuthCreateNewAccountScreen(onCreateNewAccountClicked = {})
}