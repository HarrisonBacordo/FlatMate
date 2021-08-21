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
package com.harrisonbacordo.flatmate.ui.onboarding.existingflatidmanualentry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrisonbacordo.flatmate.data.repositories.FlatRepository
import com.harrisonbacordo.flatmate.data.repositories.UserRepository
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.TextFieldState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnboardingExistingFlatIdManualEntryViewModel @ViewModelInject constructor(private val flatRepository: FlatRepository, private val userRepository: UserRepository) : ViewModel() {

    var errorMessage: String by mutableStateOf("")
        private set

    fun executeJoinExistingFlatFlow(flatIdState: TextFieldState, userId: String, onFlatSuccessfullyJoined: (userId: String) -> Unit) {
        if (!flatIdState.isValid && flatIdState.showErrors()) {
            errorMessage = flatIdState.getError()!!
        } else {
            attemptJoinExistingFlat(flatId = flatIdState.text, userId = userId, onFlatSuccessfullyJoined)
        }
    }

    private fun attemptJoinExistingFlat(flatId: String, userId: String, onFlatSuccessfullyJoined: (userId: String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            flatRepository.attemptJoinExistingFlat(flatId = flatId, userId = userId)
            userRepository.attemptSetUserFlatId(flatId = flatId)
            withContext(Dispatchers.Main) {
                onFlatSuccessfullyJoined(userId)
            }
        }
    }
}