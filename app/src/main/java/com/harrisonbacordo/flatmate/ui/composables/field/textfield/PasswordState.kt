/*
 * Designed and developed by 2020 FlatMate (Harrison Bacordo)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.harrisonbacordo.flatmate.ui.composables.field.textfield

private const val PASSWORD_REGEX = "[A-Z]+"

class PasswordState : TextFieldState(validator = ::isPasswordValid, errorFor = ::passwordValidationError)

private fun passwordValidationError(password: String): String {
    return "Invalid password: ${if (password.length < 8) "Please ensure your password is at least 8 characters long." else "Please ensure you use at least 1 uppercase letter."}"
}

private fun isPasswordValid(password: String): Boolean {
    return password.length >= 8 && password.contains(Regex(PASSWORD_REGEX))
}
