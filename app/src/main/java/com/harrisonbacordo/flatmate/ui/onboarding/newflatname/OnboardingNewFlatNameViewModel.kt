package com.harrisonbacordo.flatmate.ui.onboarding.newflatname

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrisonbacordo.flatmate.data.repositories.FlatRepository
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextFieldState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnboardingNewFlatNameViewModel @ViewModelInject constructor(private val flatRepository: FlatRepository) : ViewModel() {

    var errorMessage: String by mutableStateOf("")
        private set

    fun executeCreateFlatFlow(flatNameState: TextFieldState, onFlatSuccessfullyJoined: () -> Unit) {
        if (!flatNameState.isValid && flatNameState.showErrors()) {
            errorMessage = flatNameState.getError()!!
        } else {
            attemptSaveCreateNewFlat(flatNameState.text, onFlatSuccessfullyJoined)
        }
        onFlatSuccessfullyJoined.invoke()
    }

    private fun attemptSaveCreateNewFlat(flatName: String, onFlatSuccessfullyJoined: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            flatRepository.attemptCreateNewFlat(flatName = flatName)
            withContext(Dispatchers.Main) {
                onFlatSuccessfullyJoined.invoke()
            }
        }
    }

}