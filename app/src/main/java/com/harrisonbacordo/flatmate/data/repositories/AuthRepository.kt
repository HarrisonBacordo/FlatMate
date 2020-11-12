package com.harrisonbacordo.flatmate.data.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.tasks.await
import java.lang.Exception
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
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
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
}