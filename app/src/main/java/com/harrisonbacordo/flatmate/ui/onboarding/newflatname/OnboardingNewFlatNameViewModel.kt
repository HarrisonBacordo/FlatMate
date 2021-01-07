package com.harrisonbacordo.flatmate.ui.onboarding.newflatname

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextFieldState

class OnboardingNewFlatNameViewModel @ViewModelInject constructor() : ViewModel() {
    /**
     *
     */
    fun executeCreateFlatFlow(flatNameState: TextFieldState, onFlatSuccessfullyJoined: () -> Unit) {
        onFlatSuccessfullyJoined.invoke()
    }

}