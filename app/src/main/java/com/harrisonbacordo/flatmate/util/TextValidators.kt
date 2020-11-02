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
package com.harrisonbacordo.flatmate.util

import android.text.TextUtils

/**
 * A set of helper functions specifically made for specific text validation
 */
object TextValidators {

    /**
     * Ensures that [email] is in valid format with regex
     *
     * @return true if email is in valid format
     */
    fun emailIsValid(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Ensures that [password] is in valid format with regex
     *
     * @return true if password is in valid format with regex
     */
    fun passwordIsValid(password: String): Boolean {
//        TODO
        return true
    }
}
