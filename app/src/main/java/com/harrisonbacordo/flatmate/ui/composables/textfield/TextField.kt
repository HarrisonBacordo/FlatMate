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

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.R

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(id = R.string.auth_email_hint)) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction, keyboardType = KeyboardType.Email),
        onImeActionPerformed = { action, softKeyboardController ->
            if (action == ImeAction.Done) {
                softKeyboardController?.hideSoftwareKeyboard()
            }
            onImeAction()
        },
        modifier = modifier,
    )
}

@Composable
fun AlphaTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Sentences,
    onImeAction: () -> Unit = {},
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(hint) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction, keyboardType = KeyboardType.Text, capitalization = capitalization),
        onImeActionPerformed = { action, softKeyboardController ->
            if (action == ImeAction.Done) {
                softKeyboardController?.hideSoftwareKeyboard()
            }
            onImeAction()
        },
        modifier = modifier,
    )
}

@Composable
fun NumericTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(hint) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction, keyboardType = KeyboardType.Number),
        onImeActionPerformed = { action, softKeyboardController ->
            if (action == ImeAction.Done) {
                softKeyboardController?.hideSoftwareKeyboard()
            }
            onImeAction()
        },
        modifier = modifier,
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
) {
    var passwordIsVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(id = R.string.auth_password_hint)) },
        visualTransformation = if (passwordIsVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordIsVisible = !passwordIsVisible }) {
                Icon(imageVector = vectorResource(id = if (passwordIsVisible) R.drawable.ic_visibility_on_24 else R.drawable.ic_visibility_off_24))
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction, keyboardType = KeyboardType.Password),
        onImeActionPerformed = { action, softKeyboardController ->
            if (action == ImeAction.Done) {
                softKeyboardController?.hideSoftwareKeyboard()
            }
            onImeAction()
        },
        modifier = modifier,
    )
}

@Preview(name = "Email Text Field Preview")
@Composable
fun Field() {
    EmailTextField(value = "john.appleseed@gmail.com", onValueChange = {})
}

@Preview(name = "Password Text Field Preview")
@Composable
fun AuthPasswordTextFieldPreview() {
    PasswordTextField(value = "Password", onValueChange = {})
}

@Preview(name = "Alpha Text Field Preview")
@Composable
fun AuthAlphaTextFieldPreview() {
    AlphaTextField(value = "Alpha value", hint = "Alpha", onValueChange = {})
}

@Preview(name = "Number Text Field Preview")
@Composable
fun AuthNumericTextFieldPreview() {
    NumericTextField(value = "123456789", hint = "Number", onValueChange = {})
}