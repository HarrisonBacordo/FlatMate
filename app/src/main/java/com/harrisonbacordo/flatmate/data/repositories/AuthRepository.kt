package com.harrisonbacordo.flatmate.data.repositories

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class AuthRepository @Inject constructor() {

    fun attemptLogin(email: String, password: String) {

    }
}