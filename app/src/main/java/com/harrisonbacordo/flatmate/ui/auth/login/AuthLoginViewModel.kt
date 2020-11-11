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
import com.harrisonbacordo.flatmate.data.repositories.AuthRepository
import com.harrisonbacordo.flatmate.util.TextValidators
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.FragmentScoped

/**
 * [ViewModel] associated with [AuthLogin]
 */
@FragmentScoped
class AuthLoginViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    /**
     * Executes the login flow:
     * 1. Checks if login fields are valid
     * 2. Hits login endpoint
     * 3. Return results of login
     */
    fun executeLoginFlow(email: String, password: String) {
        if (TextValidators.emailIsValid(email) && TextValidators.passwordIsValid(password)) {
            attemptLogin(email, password)
        }
    }

    /**
     * Attempts to log user in with [email] and [password]
     */
    private fun attemptLogin(email: String, password: String) {
        authRepository.attemptLogin(email, password)
    }
}
