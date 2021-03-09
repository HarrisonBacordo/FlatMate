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
import androidx.compose.material.TextButton
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
import com.harrisonbacordo.flatmate.ui.composables.textfield.EmailState
import com.harrisonbacordo.flatmate.ui.composables.textfield.EmailTextField
import com.harrisonbacordo.flatmate.ui.composables.textfield.PasswordState
import com.harrisonbacordo.flatmate.ui.composables.textfield.PasswordTextField
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextFieldState
import com.harrisonbacordo.flatmate.ui.theme.FlatmateAuthTheme
import com.harrisonbacordo.flatmate.ui.theme.typography

/**
 * High-level composable that holds the state and high-level UI composable of the auth login screen
 *
 * @param onLoginSuccessful Callback that is executed when a login is successful
 * @param onForgotPasswordClicked Callback that is executed when the forgot password button is clicked
 */
@Composable
fun AuthLogin(onLoginSuccessful: (userId: String) -> Unit, onForgotPasswordClicked: () -> Unit) {
    val viewModel: AuthLoginViewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(AuthLoginViewModel::class.java)
    val emailState = remember { EmailState() }
    val passwordState = remember { PasswordState() }
    AuthLoginScreen(
        emailState,
        passwordState,
        viewModel.errorMessage,
        { viewModel.executeLoginFlow(emailState, passwordState, onLoginSuccessful) },
        onForgotPasswordClicked
    )
}

/**
 * High-level composable that displays the auth login screen
 *
 * @param emailState State that represents the email text field
 * @param passwordState State that represents the password text field
 * @param errorMessage String that represents the current state of the error message
 * @param onFormSubmitted Callback that is executed when the form's login  button has been clicked
 * @param onForgotPasswordClicked Callback that is executed when the form's forgot password button has been clicked
 */
@Composable
private fun AuthLoginScreen(
    emailState: TextFieldState = remember { EmailState() },
    passwordState: TextFieldState = remember { PasswordState() },
    errorMessage: String,
    onFormSubmitted: () -> Unit,
    onForgotPasswordClicked: () -> Unit

) {
    CompanyLogo()
    Column(
        Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val passwordFocusRequest = remember { FocusRequester() }
        Text("Login", style = typography.h4)
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
            Modifier.fillMaxWidth().focusRequester(passwordFocusRequest),
        )
        if (errorMessage.isNotEmpty()) Text(errorMessage, color = MaterialTheme.colors.error)
        Spacer(Modifier.padding(top = 8.dp))
        Button(onClick = onFormSubmitted, Modifier.fillMaxWidth()) {
            Text("Login")
        }
        TextButton(onForgotPasswordClicked) {
            Text("Forgot Password?")
        }
    }
}

@Preview(name = "Auth Login Screen")
@Composable
private fun PreviewAuthLoginScreen() {
    FlatmateAuthTheme {
        Scaffold {
            AuthLoginScreen(errorMessage = "Error", onFormSubmitted = {}, onForgotPasswordClicked = {})
        }
    }
}
