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
package com.harrisonbacordo.flatmate.ui.auth.signup

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.ui.auth.AuthTextInput

@Composable
fun AuthCreateNewAccountScreen(onCreateNewAccountClicked: () -> Unit) {
    Column {
        Text("Create New Account")
        AuthTextInput(value = "", hint = "Email", onValueChange = {})
        AuthTextInput(value = "", hint = "Password", onValueChange = {})
        Button(onClick = onCreateNewAccountClicked) {
            Text("Create new account")
        }
    }
}

@Preview
@Composable
private fun PreviewAuthCreateNewAccountScreen() {
    AuthCreateNewAccountScreen(onCreateNewAccountClicked = {})
}
