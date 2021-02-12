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
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.harrisonbacordo.flatmate.data.models.User
import com.harrisonbacordo.flatmate.util.Keys
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@ActivityRetainedScoped
class FlatRepository @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) {

    suspend fun attemptCreateNewFlat(flatName: String, userId: String): String? {
        return try {
            val flatDocument = firestore
                .collection(Keys.Firestore.Flat.firestoreCollection)
                .document()
            val flatFirestoreMap = mapOf(
                Keys.Firestore.Flat.id to flatDocument.id,
                Keys.Firestore.Flat.name to flatName,
                Keys.Firestore.Flat.flatmates to listOf(userId)
            )
            flatDocument
                .set(flatFirestoreMap)
                .await()
            flatDocument.id
        } catch (e: Exception) {
            null
        }
    }

    suspend fun attemptJoinExistingFlat(flatId: String, userId: String) {
        try {
            firestore
                .collection(Keys.Firestore.Flat.firestoreCollection)
                .document(flatId)
                .update(
                    mapOf(
                        Keys.Firestore.Flat.flatmates to FieldValue.arrayUnion(userId)
                    )
                )
                .await()
        } catch (e: Exception) {
            TODO()
        }
    }

    suspend fun getFlatmates(): List<User> {
        try {
            val flatId = firestore.collection(Keys.Firestore.User.firestoreCollection)
                .document(firebaseAuth.currentUser!!.uid)
                .get()
                .await()
                .get(Keys.Firestore.User.flatId) as String
            val flatmates = firestore.collection(Keys.Firestore.Flat.firestoreCollection)
                .document(flatId)
                .get()
                .await()
                .get(Keys.Firestore.Flat.flatmates) as List<String>
            return flatmates.map {
                val flatmate = firestore.collection(Keys.Firestore.User.firestoreCollection)
                    .document(it)
                    .get()
                    .await()
                    .toObject(User::class.java)
                flatmate ?: run {
                    throw Exception()
                }
            }
        } catch (e: Exception) {
            TODO()
        }
    }
}
