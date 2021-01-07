package com.harrisonbacordo.flatmate.ui.onboarding.existingflatidmanualentry

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextFieldState

class OnboardingExistingFlatIdManualEntryViewModel @ViewModelInject constructor() : ViewModel() {
    fun executeJoinFlatFlow(flatIdState: TextFieldState, onFlatSuccessfullyJoined: () -> Unit) {
        onFlatSuccessfullyJoined.invoke()
    }
}