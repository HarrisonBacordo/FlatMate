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
import androidx.lifecycle.ViewModel
import com.harrisonbacordo.flatmate.util.TextValidators

class AuthLoginViewModel : ViewModel() {
    var email: String by mutableStateOf("")
        private set
    var password: String by mutableStateOf("")
        private set

    /**
     * Sets [email] to [value]
     *
     * @param value new value for [email]
     */
    fun onEmailFieldChanged(value: String) {
        email = value
    }

    /**
     * Sets [password] to [value]
     *
     * @param value new value for [password]
     */
    fun onPasswordFieldChanged(value: String) {
        password = value
    }

    /**
     * Executes the login flow:
     * 1. Checks if login fields are valid
     * 2. Hits login endpoint
     * 3. Return results of login
     */
    fun executeLoginFlow() {
        if (loginFieldsAreValid()) {
            attemptLogin()
        }
    }

    /**
     * Validates [email] and [password]
     *
     * @return true if both [email] and [password] are in valid format
     */
    private fun loginFieldsAreValid(): Boolean {
        return TextValidators.emailIsValid(email) && TextValidators.passwordIsValid(password)
    }

    /**
     * Attempts to log user in with [email] and [password]
     */
    private fun attemptLogin() {
    }
}
