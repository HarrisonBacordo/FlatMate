package com.harrisonbacordo.flatmate.util

object Keys {
    object Firestore {
        object Users {
            const val firestoreCollection = "users"
            const val id = "id"
            const val email = "email"
            const val flatId = "flatId"
            const val firstName = "firstName"
            const val lastName = "lastName"
            const val fullName = "fullName"
            const val nudgeCount = "nudgeCount"
            const val profPicUri = "profPicUri"
        }
        object Flat {
            const val firestoreCollection = "flats"
        }

        const val FlatsCollection = "flats"

    }
}