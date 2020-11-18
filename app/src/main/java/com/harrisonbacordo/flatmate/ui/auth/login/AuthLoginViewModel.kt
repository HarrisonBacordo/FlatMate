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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrisonbacordo.flatmate.data.repositories.AuthRepository
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextFieldState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * [ViewModel] associated with [AuthLogin]
 */
class AuthLoginViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var errorMessage: String by mutableStateOf("")
        private set

    /**
     * Executes the login flow:
     * 1. Checks if login fields are valid
     * 2. Hits login endpoint
     * 3. Return results of login
     *
     * @param emailState State that represents the email to login with
     * @param passwordState State that represents the password to login with
     * @param onLoginSuccessful Callback that is executed when a login is successful
     */
    fun executeLoginFlow(emailState: TextFieldState, passwordState: TextFieldState, onLoginSuccessful: () -> Unit) {
        if (emailState.isValid && passwordState.isValid) {
            attemptLogin(emailState.text, passwordState.text, onLoginSuccessful)
        } else {
            errorMessage = "${emailState.getError()}\n${passwordState.getError()}"
        }
    }

    /**
     * Attempts to log user in with [email] and [password]. If successful, invoke [onLoginSuccessful]
     *
     * @param email String that represents the email to login with
     * @param password String that represents the password to login with
     * @param onLoginSuccessful Callback that is executed when a login is successful
     */
    private fun attemptLogin(email: String, password: String, onLoginSuccessful: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.attemptLogin(email, password)?.let {
                if (it.user != null) {
                    withContext(Dispatchers.Main) {
                        onLoginSuccessful.invoke()
                    }
                }
            }
        }
    }
}
