package com.harrisonbacordo.flatmate.ui.home.calendar

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.harrisonbacordo.flatmate.network.FlatmateNetworkResult

@Composable
fun HomeNewEvent(initiateSave: Boolean, onSaveComplete: (FlatmateNetworkResult) -> Unit) {
    NewEventScreen()
}

@Composable
private fun NewEventScreen() {
    Text("New Event Screen")
}