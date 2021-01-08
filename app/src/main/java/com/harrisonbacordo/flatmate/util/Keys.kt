package com.harrisonbacordo.flatmate.util

object Keys {
    object Firestore {
        object User {
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
            const val id = "id"
            const val name = "name"
            const val flatmates = "flatmates"
            const val choresCollection = "chores"
            const val groceriesCollection = "groceries"
        }
        object Chore {
            const val id = "id"
            const val name = "name"
            const val completed = "completed"
            const val interval = "interval"
            const val flatmateName = "flatmateName"
            const val flatmateId = "flatmateId"
        }
        object Grocery {
            const val id = "id"
            const val name = "name"
            const val personal = "personal"
        }

        const val FlatsCollection = "flats"

    }
}