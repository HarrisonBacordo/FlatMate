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

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.harrisonbacordo.flatmate.util.Keys
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@ActivityRetainedScoped
class UserRepository @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) {

    suspend fun attemptSetUserName(firstName: String, lastName: String) {
        try {
            firebaseAuth.currentUser?.let { user ->
                val userNameFirestoreMap = mapOf(
                    Keys.Firestore.Users.firstName to firstName,
                    Keys.Firestore.Users.lastName to lastName,
                    Keys.Firestore.Users.fullName to "$firstName $lastName"
                )
                firestore
                    .collection(Keys.Firestore.Users.firestoreCollection)
                    .document(user.uid)
                    .update(userNameFirestoreMap)
                    .await()
            }
        } catch (e: Exception) {

        }
    }

    suspend fun attemptSetUserFlatId(flatId: String) {
        try {
            firebaseAuth.currentUser?.let { user ->
                val flatIdFirestoreMap = mapOf(
                    Keys.Firestore.Users.flatId to flatId
                )
                firestore
                    .collection(Keys.Firestore.Users.firestoreCollection)
                    .document(user.uid)
                    .update(flatIdFirestoreMap)
                    .await()
            }
        } catch (e: Exception) {

        }
    }
}
