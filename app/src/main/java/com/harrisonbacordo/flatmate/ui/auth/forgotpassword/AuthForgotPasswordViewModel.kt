package com.harrisonbacordo.flatmate.ui.auth.forgotpassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.harrisonbacordo.flatmate.util.TextValidators

/**
 * [ViewModel] associated with [AuthForgotPassword]
 */
class AuthForgotPasswordViewModel : ViewModel() {
    var email: String by mutableStateOf("")
        private set

    /**
     * Sets [email] to [value]
     *
     * @param value new value for [email]
     */
    fun onEmailFieldChanged(value: String) {
        email = value
    }

    /**
     * Executes the reset password flow:
     * 1. Checks if [email] is valid
     * 2. Hits reset password endpoint
     * 3. Return results of endpoint
     */
    fun executeResetPasswordFlow() {
        if (TextValidators.emailIsValid(email)) {
            attemptResetPassword()
        }
    }

    /**
     * Attempts to send reset password email to [email]
     */
    private fun attemptResetPassword() {

    }
}