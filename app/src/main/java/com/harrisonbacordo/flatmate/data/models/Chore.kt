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
package com.harrisonbacordo.flatmate.data.models

import com.harrisonbacordo.flatmate.util.Keys
import java.util.*

data class Chore(
    val id: UUID = UUID.randomUUID(),
    val name: String = "New Chore",
    val assignedFlatmateName: String = "",
    val assignedFlatmateId: String = "",
    val interval: Interval = Interval.NotSelected,
    val completed: Boolean = false,
) {
    fun toApiMap(): Map <String, Any> {
        return mapOf(
            Keys.Firestore.Chore.id to id.toString(),
            Keys.Firestore.Chore.name to name,
            Keys.Firestore.Chore.flatmateId to assignedFlatmateId,
            Keys.Firestore.Chore.flatmateName to assignedFlatmateName,
            Keys.Firestore.Chore.interval to interval,
            Keys.Firestore.Chore.completed to completed,
        )
    }
}

enum class Interval {
    Daily,
    Weekly,
    Fortnightly,
    Monthly,
    NotSelected,
}
