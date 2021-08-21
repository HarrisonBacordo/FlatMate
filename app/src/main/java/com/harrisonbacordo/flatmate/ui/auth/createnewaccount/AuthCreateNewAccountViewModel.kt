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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrisonbacordo.flatmate.data.repositories.AuthRepository
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.TextFieldState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * [ViewModel] associated with [AuthCreateNewAccount]
 */
class AuthCreateNewAccountViewModel @ViewModelInject constructor(private val authRepository: AuthRepository) : ViewModel() {

    var errorMessage: String by mutableStateOf("")
        private set

    /**
     * Executes the login flow:
     * 1. Checks if create new account fields are valid
     * 2. Hits create new account endpoint
     * 3. Return results of create new account
     *
     * @param emailState State that represents the email to create a new account with
     * @param passwordState State that represents the password to create a new account with
     * @param onCreateNewAccountSuccessful Callback that is executed when an account is successfully created
     */
    fun executeCreateNewAccountFlow(emailState: TextFieldState, passwordState: TextFieldState, onCreateNewAccountSuccessful: (userId: String) -> Unit) {
        if (!emailState.isValid && emailState.showErrors()) {
            errorMessage = emailState.getError()!!
        } else if (!passwordState.isValid && passwordState.showErrors()) {
            errorMessage = passwordState.getError()!!
        } else {
            attemptCreateNewAccount(emailState.text, passwordState.text, onCreateNewAccountSuccessful)
        }
    }

    /**
     * Attempts to log user in with [email] and [password]. If successful, invoke [onCreateNewAccountSuccessful]
     *
     * @param email String that represents the email to create a new account with
     * @param password String that represents the password to create a new account with
     * @param onCreateNewAccountSuccessful Callback that is executed when an account is successfully created
     */
    private fun attemptCreateNewAccount(email: String, password: String, onCreateNewAccountSuccessful: (userId: String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.createNewAccount(email, password)?.let {
                withContext(Dispatchers.Main) {
                    onCreateNewAccountSuccessful(it)
                }
            }
        }
    }
}
