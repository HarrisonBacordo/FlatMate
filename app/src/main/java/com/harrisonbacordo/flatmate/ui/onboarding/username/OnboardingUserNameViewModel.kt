package com.harrisonbacordo.flatmate.ui.onboarding.username

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrisonbacordo.flatmate.data.repositories.UserRepository
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextFieldState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnboardingUserNameViewModel @ViewModelInject constructor(private val userRepository: UserRepository) : ViewModel() {

    var errorMessage: String by mutableStateOf("")
        private set

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
                onUserNameSuccessfullySaved.invoke()
            }
        }
    }
}