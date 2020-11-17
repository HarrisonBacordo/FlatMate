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
package com.harrisonbacordo.flatmate.ui.composables.textfield

import androidx.compose.foundation.Text
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.ui.tooling.preview.Preview

/**
 * High-level composable that displays a standard [OutlinedTextField]
 *
 * @param value [String] that is currently populating the [OutlinedTextField]
 * @param hint [String] that is used in the [OutlinedTextField]
 * @param onValueChange Callback that is executed when the value of [OutlinedTextField] is changed
 */
@Composable
fun TextInput(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        label = { Text(hint) },
        onValueChange = onValueChange
    )
}

/**
 * High-level composable that displays a password-specific [OutlinedTextField]
 *
 * @param value [String] that is currently populating the [OutlinedTextField]
 * @param hint [String] that is used in the [OutlinedTextField]
 * @param onValueChange Callback that is executed when the value of [OutlinedTextField] is changed
 */
@Composable
fun HiddenTextInput(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        label = { Text(hint) },
        visualTransformation = PasswordVisualTransformation(),
        onValueChange = onValueChange
    )
}

@Preview
@Composable
fun AuthTextInputPreview() {
    TextInput(value = "", hint = "Email", onValueChange = {})
}

@Preview
@Composable
fun AuthHiddenTextInputPreview() {
    HiddenTextInput(value = "", hint = "Password", onValueChange = {})
}
