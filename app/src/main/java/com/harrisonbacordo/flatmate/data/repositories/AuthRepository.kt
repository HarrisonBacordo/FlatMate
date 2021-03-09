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
package com.harrisonbacordo.flatmate.data.repositories

import com.google.firebase.auth.AuthResult
import com.harrisonbacordo.flatmate.network.AuthApi
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class AuthRepository @Inject constructor(private val authApi: AuthApi) {

    fun getCurrentUserId(): String? {
        return authApi.getCurrentUserId()
    }

    /**
     * Makes a network call to create a new account
     *
     * @param email String that represents the email to create a new account with
     * @param password String that represents the password to create a new account with
     */
    suspend fun createNewAccount(email: String, password: String): String? {
        return try {
            authApi.createNewAccountWithEmail(email, password)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Makes a network call to login
     *
     * @param email String that represents the email to log in with
     * @param password String that represents the password to log in with
     */
    suspend fun login(email: String, password: String): AuthResult? {
        return try {
            authApi.login(email, password)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Signs the current user out
     */
    fun signCurrentUserOut() {
        authApi.logout()
    }
}
