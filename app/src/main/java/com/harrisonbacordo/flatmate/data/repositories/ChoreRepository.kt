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

import android.util.Log
import com.harrisonbacordo.flatmate.data.models.Chore
import com.harrisonbacordo.flatmate.network.ChoreApi
import javax.inject.Inject

class ChoreRepository @Inject constructor(private val choreApi: ChoreApi) {

    /**
     * Fetches chores from either Room or Firestore
     */
    suspend fun getChores(): List<Chore> {
        return try {
            choreApi.getChores()
        } catch (e: Exception) {
            val ex = e
            Log.d("", ex.message!!)
            emptyList()
        }
    }

    suspend fun addChore(chore: Chore): Chore {
        return try {
            choreApi.addChore(chore)
        } catch (e: Exception) {
            TODO()
        }
    }

    suspend fun removeChore(chore: Chore) {
        try {
            choreApi.removeChore(chore)
        } catch (e: Exception) {
            TODO()
        }
    }

    suspend fun updateChore(chore: Chore): Chore {
        return try {
            choreApi.updateChore(chore)
        } catch (e: Exception) {
            TODO()
        }
    }
}
