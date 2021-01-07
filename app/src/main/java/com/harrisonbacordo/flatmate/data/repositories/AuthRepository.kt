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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.harrisonbacordo.flatmate.data.models.User
import com.harrisonbacordo.flatmate.util.Keys
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@ActivityRetainedScoped
class AuthRepository @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) {

    /**
     * Makes a network call to create a new account
     *
     * @param email String that represents the email to create a new account with
     * @param password String that represents the password to create a new account with
     */
    suspend fun attemptCreateNewAccount(email: String, password: String): AuthResult? {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            authResult.user?.let { user ->
                createUserInFirestoreWithId(email, user.uid)
            }
            authResult
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
    suspend fun attemptLogin(email: String, password: String): AuthResult? {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Signs the current user out
     */
    fun signCurrentUserOut() {
        firebaseAuth.signOut()
    }

    /**
     *
     */
    private suspend fun createUserInFirestoreWithId(email: String, userId: String) {
        firestore
            .collection(Keys.Firestore.Users.firestoreCollection)
            .document(userId)
            .set(User(email = email, id = userId))
            .await()
    }
}
