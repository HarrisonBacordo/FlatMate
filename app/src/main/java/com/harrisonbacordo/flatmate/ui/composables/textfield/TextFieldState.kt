package com.harrisonbacordo.flatmate.ui.composables.textfield

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class TextFieldState(private val validator: (String) -> Boolean = { true }, private val errorFor: (String) -> String = { "" }) {
    var text: String by mutableStateOf("")
    private var displayErrors: Boolean by mutableStateOf(false)
        private set
    open val isValid: Boolean
        get() = validator(text)

    fun showErrors() = !isValid && displayErrors

    open fun getError(): String? {
        return if (showErrors()) {
            errorFor(text)
        } else {
            null
        }
    }
}