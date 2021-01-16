/*
 * Designed and developed by 2021 FlatMate (Harrison Bacordo)
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
