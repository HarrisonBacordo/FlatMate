package com.harrisonbacordo.flatmate.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.harrisonbacordo.flatmate.data.models.Chore
import com.harrisonbacordo.flatmate.util.Keys
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChoreApiImpl @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) : ChoreApi {

    override suspend fun getChores(): List<Chore> {
        return try {
            val flatId = firestore.collection(Keys.Firestore.User.firestoreCollection)
                .document(firebaseAuth.currentUser!!.uid)
                .get()
                .await()
                .get(Keys.Firestore.User.flatId) as String
            firestore
                .collection(Keys.Firestore.Flat.firestoreCollection)
                .document(flatId)
                .collection(Keys.Firestore.Flat.choresCollection)
                .get()
                .await()
                .toObjects(Chore::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addChore(chore: Chore): Chore {
        return try {
            val flatId = firestore.collection(Keys.Firestore.User.firestoreCollection)
                .document(firebaseAuth.currentUser!!.uid)
                .get()
                .await()
                .get(Keys.Firestore.User.flatId) as String
            firestore
                .collection(Keys.Firestore.Flat.firestoreCollection)
                .document(flatId)
                .collection(Keys.Firestore.Flat.choresCollection)
                .document(chore.id.toString())
                .set(chore.toApiMap())
                .await()
            chore
        } catch (e: Exception) {
            TODO()
        }
    }

    override suspend fun updateChore(chore: Chore): Chore {
        return try {
            val flatId = firestore.collection(Keys.Firestore.User.firestoreCollection)
                .document(firebaseAuth.currentUser!!.uid)
                .get()
                .await()
                .get(Keys.Firestore.User.flatId) as String
            firestore
                .collection(Keys.Firestore.Flat.firestoreCollection)
                .document(flatId)
                .collection(Keys.Firestore.Flat.choresCollection)
                .document(chore.id.toString())
                .update(chore.toApiMap())
                .await()
            chore
        } catch (e: Exception) {
            TODO()
        }
    }

    override suspend fun removeChore(chore: Chore) {
        try {
            val flatId = firestore.collection(Keys.Firestore.User.firestoreCollection)
                .document(firebaseAuth.currentUser!!.uid)
                .get()
                .await()
                .get(Keys.Firestore.User.flatId) as String
            firestore
                .collection(Keys.Firestore.Flat.firestoreCollection)
                .document(flatId)
                .collection(Keys.Firestore.Flat.choresCollection)
                .document(chore.id.toString())
                .delete()
                .await()
        } catch (e: Exception) {
            TODO()
        }
    }

}

interface ChoreApi {
    suspend fun getChores(): List<Chore>
    suspend fun addChore(chore: Chore): Chore
    suspend fun updateChore(chore: Chore): Chore
    suspend fun removeChore(chore: Chore)
}