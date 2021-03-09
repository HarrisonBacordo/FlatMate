package com.harrisonbacordo.flatmate.ui.home.settings.flatmates

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.harrisonbacordo.flatmate.data.models.User
import com.harrisonbacordo.flatmate.ui.theme.FlatMateHomeTheme

@Composable
fun HomeSettingsFlatmates() {
    val viewModel: HomeSettingsFlatmatesViewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(HomeSettingsFlatmatesViewModel::class.java)
    HomeSettingsFlatmatesScreen(viewModel.flatMates, viewModel.flatId)
}

@Composable
private fun HomeSettingsFlatmatesScreen(flatmates: List<User>, flatId: String) {
    if (flatmates.isEmpty() || flatId.isEmpty()) {
        CircularProgressIndicator()
    } else {
        Column {
            flatmates.forEach {
                Text(it.email)
            }
            Text("FlatId: $flatId")
        }
    }
}

@Composable
@Preview
private fun HomeSettingsFlatmatesScreenPreview() {
    FlatMateHomeTheme {
        HomeSettingsFlatmatesScreen(flatmates = emptyList(), "flat id")
    }
}