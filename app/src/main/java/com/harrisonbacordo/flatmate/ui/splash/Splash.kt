package com.harrisonbacordo.flatmate.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

@Composable
fun Splash(onRedirectToAuth: () -> Unit, onRedirectToHome: (userId: String) -> Unit) {
    val viewModel: SplashViewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(SplashViewModel::class.java)
    viewModel.userId?.let {
        onRedirectToHome(it)
    } ?: run {
        onRedirectToAuth()
    }
}

@Composable
private fun SplashScreen() {

}