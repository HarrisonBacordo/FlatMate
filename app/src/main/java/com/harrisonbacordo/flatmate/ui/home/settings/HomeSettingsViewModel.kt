package com.harrisonbacordo.flatmate.ui.home.settings

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.harrisonbacordo.flatmate.data.repositories.AuthRepository

/**
 * [ViewModel] associated with [HomeSettings]
 *
 */
class HomeSettingsViewModel @ViewModelInject constructor(private val authRepository: AuthRepository) : ViewModel() {

    /**
     * Signs the current user out
     */
    fun signCurrentUserOut(onLogoutSuccessful: () -> Unit) {
        authRepository.signCurrentUserOut()
        onLogoutSuccessful.invoke()
    }
}