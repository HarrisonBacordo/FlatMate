package com.harrisonbacordo.flatmate.ui.onboarding.existingflatqrscanner

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextFieldState

class OnboardingExistingFlatQrScannerViewModel @ViewModelInject constructor() : ViewModel() {
    fun executeJoinFlatFlow(flatIdState: TextFieldState, onFlatSuccessfullyJoined: () -> Unit) {

    }
}