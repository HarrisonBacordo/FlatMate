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
import com.harrisonbacordo.flatmate.data.models.Interval
import com.harrisonbacordo.flatmate.data.models.User
import com.harrisonbacordo.flatmate.data.repositories.ChoreRepository
import com.harrisonbacordo.flatmate.data.repositories.FlatRepository
import com.harrisonbacordo.flatmate.network.FlatmateErrorNetworkResult
import com.harrisonbacordo.flatmate.network.FlatmateNetworkResult
import com.harrisonbacordo.flatmate.network.FlatmateSuccessNetworkResult
import com.harrisonbacordo.flatmate.ui.composables.textfield.RequiredTextFieldState
import kotlinx.coroutines.launch

/**
 * [ViewModel] associated with [HomeChores]
 */
class HomeNewChoreViewModel @ViewModelInject constructor(
    private val flatRepository: FlatRepository,
    private val choreRepository: ChoreRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state: HomeNewChoreState by mutableStateOf(
        HomeNewChoreState(
            choreName = RequiredTextFieldState(),
            errorMessage = "",
        )
    )
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                flatmates = flatRepository.getFlatmates()
            )
        }
    }

    fun onIntervalClicked() {
        state = state.copy(showIntervalDialog = true)
    }

    fun onIntervalSelected(interval: Interval) {
        state = state.copy(choreInterval = interval, showIntervalDialog = false)
    }

    fun onIntervalDialogDismissRequest() {
        state = state.copy(showIntervalDialog = false)
    }

    fun onFlatmatesClicked() {
        state = state.copy(showFlatmatesDialog = true)
    }

    fun onFlatmateSelected(flatmate: User) {
        state = state.copy(assignedFlatmate = flatmate, showFlatmatesDialog = false)
    }

    fun onFlatmateDialogDismissRequest() {
        state = state.copy(showFlatmatesDialog = false)
    }

    fun onChoreSaved(onSaveComplete: (FlatmateNetworkResult) -> Unit) {
        if (!fieldsAreValid()) {
            onSaveComplete(FlatmateErrorNetworkResult(""))
            return
        }
        viewModelScope.launch {
            val newChore = Chore(
                name = state.choreName.text,
                assignedFlatmateId = state.assignedFlatmate!!.id,
                assignedFlatmateName = state.assignedFlatmate!!.fullName,
                interval = state.choreInterval,
                completed = false,
            )
            choreRepository.addChore(newChore)
            onSaveComplete(FlatmateSuccessNetworkResult)
        }
    }

    private fun fieldsAreValid(): Boolean {
        var isValid = true
        if (!state.choreName.isValid) {
            state.choreName.displayErrors = true
            isValid =  false
        }
        if (state.assignedFlatmate == null) {
            // TODO no assigned flatmate error
            isValid =  false
        }
        if (state.choreInterval == Interval.NotSelected) {
            // TODO no interval selected error
            isValid =  false
        }
        return isValid
    }
}

data class HomeNewChoreState(
    val choreName: RequiredTextFieldState,
    val choreInterval: Interval = Interval.NotSelected,
    val assignedFlatmate: User? = null,
    val flatmates: List<User>? = null,
    val errorMessage: String,
    val showIntervalDialog: Boolean = false,
    val showFlatmatesDialog: Boolean = false,
    val saveComplete: Boolean = false
)
