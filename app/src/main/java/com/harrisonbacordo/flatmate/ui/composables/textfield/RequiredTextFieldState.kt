package com.harrisonbacordo.flatmate.ui.composables.textfield

class RequiredTextFieldState: TextFieldState(validator = ::isRequiredTextFieldValid, errorFor = ::requiredTextFieldValidationError)

private fun requiredTextFieldValidationError(textFieldValue: String): String {
    return "This field cannot be blank"
}

private fun isRequiredTextFieldValid(textFieldValue: String): Boolean {
    return textFieldValue.isNotBlank()
}