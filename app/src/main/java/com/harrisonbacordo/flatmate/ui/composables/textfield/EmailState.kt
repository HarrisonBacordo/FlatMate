package com.harrisonbacordo.flatmate.ui.composables.textfield

import android.text.TextUtils

class EmailState: TextFieldState(validator = ::isEmailValid, ::emailValidationError)

private fun emailValidationError(email: String): String {
    return "Invalid email: $email"
}

private fun isEmailValid(email: String): Boolean {
    return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}