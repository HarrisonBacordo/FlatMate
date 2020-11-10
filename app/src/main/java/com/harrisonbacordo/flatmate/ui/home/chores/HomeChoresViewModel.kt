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
package com.harrisonbacordo.flatmate.ui.home.chores

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrisonbacordo.flatmate.data.models.Chore
import com.harrisonbacordo.flatmate.data.repositories.ChoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * [ViewModel] associated with [HomeChores]
 */
class HomeChoresViewModel : ViewModel() {
    //    TODO inject [ChoreRepository]
    val choreRepository = ChoreRepository()
    var chores: List<Chore> by mutableStateOf(listOf())
        private set

    init {
        fetchChores()
    }

    /**
     * Fetches chores from [ChoreRepository]
     */
    private fun fetchChores() {
        viewModelScope.launch(Dispatchers.IO) {
            choreRepository.fetchChores()
        }
    }
}
