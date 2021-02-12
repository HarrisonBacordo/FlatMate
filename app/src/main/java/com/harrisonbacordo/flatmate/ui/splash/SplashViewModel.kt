package com.harrisonbacordo.flatmate.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.harrisonbacordo.flatmate.data.repositories.AuthRepository

class SplashViewModel @ViewModelInject constructor(private val authRepository: AuthRepository) : ViewModel() {
    var userId: String? = ""
        get() = authRepository.getCurrentUserId()
}