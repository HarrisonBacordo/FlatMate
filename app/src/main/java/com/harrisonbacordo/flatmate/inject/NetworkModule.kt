package com.harrisonbacordo.flatmate.inject

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {

    /**
     * Provides a global instance of [FirebaseAuth]
     */
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    /**
     * Provides a global instance of [FirebaseFirestore]
     */
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }
}