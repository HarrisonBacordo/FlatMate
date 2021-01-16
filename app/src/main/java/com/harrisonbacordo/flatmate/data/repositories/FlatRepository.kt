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

import com.google.firebase.firestore.FirebaseFirestore
import com.harrisonbacordo.flatmate.data.models.User
import com.harrisonbacordo.flatmate.util.Keys
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@ActivityRetainedScoped
class FlatRepository @Inject constructor(private val firestore: FirebaseFirestore) {

    suspend fun attemptCreateNewFlat(flatName: String) {
        try {
            val flatDocument = firestore
                .collection(Keys.Firestore.Flat.firestoreCollection)
                .document()
            val flatFirestoreMap = mapOf(
                Keys.Firestore.Flat.id to flatDocument.id,
                Keys.Firestore.Flat.name to flatName,
                Keys.Firestore.Flat.flatmates to listOf<User>()
            )
            flatDocument
                .set(flatFirestoreMap)
                .await()
        } catch (e: Exception) {
        }
    }
}
