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
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.RequiredTextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

/**
 * [ViewModel] associated with [HomeChores]
 */
class HomeNewChoreViewModel @ViewModelInject constructor(
    private val flatRepository: FlatRepository,
    private val choreRepository: ChoreRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state: StateFlow<HomeNewChoreState> get() = _state
    private val _state = MutableStateFlow(HomeNewChoreState())

    private val choreFieldsState = MutableStateFlow(HomeNewChoreFieldsState())
    private val flatmates = MutableStateFlow(listOf<User>())
    private val errorMessage = MutableStateFlow<String?>(null)
    private val currentDialog = MutableStateFlow(NewChoreDialog.None)
    private val saveComplete = MutableStateFlow(false)


    init {
        viewModelScope.launch {
            flatmates.value = flatRepository.getFlatmates()
            combine(choreFieldsState, flatmates, errorMessage, currentDialog, saveComplete) { choreFieldsState, flatmates, errorMessage, currentDialog, saveComplete ->
                HomeNewChoreState(
                    choreFieldsState = choreFieldsState,
                    flatmates = flatmates,
                    errorMessage = errorMessage,
                    currentDialog = currentDialog,
                    saveComplete = saveComplete
                )
            }.collect {
                _state.value = it
            }
        }
    }

    fun onIntervalClicked() {
        currentDialog.value = NewChoreDialog.Interval
    }

    fun onIntervalSelected(interval: Interval) {
        choreFieldsState.value = choreFieldsState.value.copy(choreInterval = interval)
        currentDialog.value = NewChoreDialog.None
    }

    fun onFlatmatesClicked() {
        currentDialog.value = NewChoreDialog.Flatmates
    }

    fun onFlatmateSelected(flatmate: User) {
        choreFieldsState.value = choreFieldsState.value.copy(assignedFlatmate = flatmate)
        dismissDialog()
    }

    fun dismissDialog() {
        currentDialog.value = NewChoreDialog.None
    }

    fun onChoreSaved(onSaveComplete: (FlatmateNetworkResult) -> Unit) {
        if (!fieldsAreValid()) {
            onSaveComplete(FlatmateErrorNetworkResult(""))
            return
        }
        viewModelScope.launch {
            val newChore = Chore(
                name = choreFieldsState.value.choreName.text,
                assignedFlatmateId = choreFieldsState.value.assignedFlatmate!!.id,
                assignedFlatmateName = choreFieldsState.value.assignedFlatmate!!.fullName,
                interval = choreFieldsState.value.choreInterval,
            )
            choreRepository.addChore(newChore)
            onSaveComplete(FlatmateSuccessNetworkResult)
        }
    }

    private fun fieldsAreValid(): Boolean {
        var isValid = true
        if (!choreFieldsState.value.choreName.isValid) {
            choreFieldsState.value.choreName.displayErrors = true
            isValid = false
        }
        if (choreFieldsState.value.assignedFlatmate == null) {
            // TODO no assigned flatmate error
            isValid = false
        }
        if (choreFieldsState.value.choreInterval == Interval.NotSelected) {
            // TODO no interval selected error
            isValid = false
        }
        return isValid
    }
}

enum class NewChoreDialog {
    Interval,
    Flatmates,
    None
}

data class HomeNewChoreState(
    val choreFieldsState: HomeNewChoreFieldsState = HomeNewChoreFieldsState(),
    val flatmates: List<User>? = null,
    val errorMessage: String? = null,
    val currentDialog: NewChoreDialog = NewChoreDialog.None,
    val saveComplete: Boolean = false
)

data class HomeNewChoreFieldsState(
    val choreName: RequiredTextFieldState = RequiredTextFieldState(),
    val choreInterval: Interval = Interval.NotSelected,
    val assignedFlatmate: User? = null,
)
