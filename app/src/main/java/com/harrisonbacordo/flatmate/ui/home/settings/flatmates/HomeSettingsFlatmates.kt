package com.harrisonbacordo.flatmate.ui.home.settings.flatmates

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.harrisonbacordo.flatmate.data.models.User

@Composable
fun HomeSettingsFlatmates() {
    val viewModel: HomeSettingsFlatmatesViewModel = ViewModelProvider(AmbientContext.current as ViewModelStoreOwner).get(HomeSettingsFlatmatesViewModel::class.java)
    HomeSettingsFlatmatesScreen(viewModel.flatMates)
}

@Composable
private fun HomeSettingsFlatmatesScreen(flatmates: List<User>) {
    if (flatmates.isEmpty()) {
        CircularProgressIndicator()
    } else {
        Column {
            flatmates.forEach {
                Text(it.email)
            }
        }
    }
}

@Composable
@Preview
private fun HomeSettingsFlatmatesScreenPreview() {
    HomeSettingsFlatmatesScreen(flatmates = emptyList())
}