package com.harrisonbacordo.flatmate.network

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.harrisonbacordo.flatmate.data.models.User
import com.harrisonbacordo.flatmate.util.Keys
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthApiImpl @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) : AuthApi {

    override fun getCurrentUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }

    override suspend fun createNewAccountWithEmail(email: String, password: String): String? {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            authResult.user?.let { user ->
                createUserInFirestoreWithId(email, user.uid)
                user.uid
            } ?: run {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun login(email: String, password: String): AuthResult? {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        } catch (e: Exception) {
            null
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    private suspend fun createUserInFirestoreWithId(email: String, userId: String) {
        firestore
            .collection(Keys.Firestore.User.firestoreCollection)
            .document(userId)
            .set(User(email = email, id = userId))
            .await()
    }
}

interface AuthApi {
    fun getCurrentUserId(): String?
    suspend fun createNewAccountWithEmail(email: String, password: String): String?
    suspend fun login(email: String, password: String): AuthResult?
    fun logout()
}