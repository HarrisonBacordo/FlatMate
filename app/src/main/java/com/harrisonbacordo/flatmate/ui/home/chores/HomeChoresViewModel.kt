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
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrisonbacordo.flatmate.data.models.Chore
import com.harrisonbacordo.flatmate.data.repositories.ChoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

/**
 * [ViewModel] associated with [HomeChores]
 */
class HomeChoresViewModel @ViewModelInject constructor(private val choreRepository: ChoreRepository, @Assisted private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var chores: List<Chore> by mutableStateOf(emptyList())
        private set

    init {
        fetchChores()
    }

    private fun fetchChores() {
        viewModelScope.launch(Dispatchers.IO) {
            chores = choreRepository.getChores()
        }
    }

    fun onChoreNudged(id: UUID) {

    }

    fun onChoreCompleteToggled(id: UUID) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val chore = chores.find { it.id == id }!!
                val updateChore = choreRepository.updateChore(chore.copy(completed = !chore.completed))
                val newChoreList = mutableListOf<Chore>()
                chores.forEach {
                    if (it.id == id) {
                        newChoreList.add(updateChore)
                    } else {
                        newChoreList.add(it)
                    }
                }
                chores = newChoreList
            } catch (e: Exception) {
                TODO()
            }
        }
    }

    fun onChoreAdded() {
        val chore = Chore()
        chores = chores.toMutableList().also {
            it.add(chore)
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val chore = Chore()
                choreRepository.addChore(chore)
            } catch (e: Exception) {
                TODO()
            }
        }
    }

    fun onChoreRemoved(chore: Chore) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                choreRepository.removeChore(chore)
                chores = chores.toMutableList().also {
                    it.removeIf { currentChore -> currentChore.id == chore.id }
                }
            } catch (e: Exception) {
                TODO()
            }
        }

    }

    fun onChoreEdited(chore: Chore) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val updateChore = choreRepository.updateChore(chore)
                val newChoreList = mutableListOf<Chore>()
                chores.forEach {
                    if (it.id == chore.id) {
                        newChoreList.add(updateChore)
                    } else {
                        newChoreList.add(it)
                    }
                }
                chores = newChoreList
            } catch (e: Exception) {
                TODO()
            }
        }
    }
}
