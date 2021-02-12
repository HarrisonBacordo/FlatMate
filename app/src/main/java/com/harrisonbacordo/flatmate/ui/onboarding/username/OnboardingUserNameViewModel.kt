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
package com.harrisonbacordo.flatmate.ui.onboarding.username

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrisonbacordo.flatmate.data.models.User
import com.harrisonbacordo.flatmate.data.repositories.UserRepository
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextFieldState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnboardingUserNameViewModel @ViewModelInject constructor(private val userRepository: UserRepository) : ViewModel() {

    var user: User? by mutableStateOf(null)
        private set

    var errorMessage: String by mutableStateOf("")
        private set

    fun getUser(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            user = userRepository.getUserWithId(userId)
        }
    }

    fun executeSaveUserNameFlow(firstNameState: TextFieldState, lastNameState: TextFieldState, onUserNameSuccessfullySaved: () -> Unit) {
        if (!firstNameState.isValid && firstNameState.showErrors()) {
            errorMessage = firstNameState.getError()!!
        } else if (!lastNameState.isValid && lastNameState.showErrors()) {
            errorMessage = lastNameState.getError()!!
        } else {
            attemptSaveUserName(firstNameState.text, lastNameState.text, onUserNameSuccessfullySaved)
        }
    }

    private fun attemptSaveUserName(firstName: String, lastName: String, onUserNameSuccessfullySaved: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.attemptSetUserName(firstName, lastName)
            withContext(Dispatchers.Main) {
                onUserNameSuccessfullySaved()
            }
        }
    }
}
