package com.harrisonbacordo.flatmate.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

@Composable
fun Splash(onRedirectToAuth: () -> Unit, onRedirectToHome: (userId: String) -> Unit) {
    val viewModel: SplashViewModel = ViewModelProvider(AmbientContext.current as ViewModelStoreOwner).get(SplashViewModel::class.java)
    viewModel.userId?.let {
        onRedirectToHome(it)
    } ?: run {
        onRedirectToAuth()
    }
}

@Composable
private fun SplashScreen() {

}