package com.harrisonbacordo.flatmate.ui.home.chores

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

@Composable
fun HomeNewChore() {
    val viewModel: HomeNewChoreViewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(HomeNewChoreViewModel::class.java)
    NewChoreScreen()
}

@Composable
private fun NewChoreScreen() {
    Text("New Chore Screen")
}