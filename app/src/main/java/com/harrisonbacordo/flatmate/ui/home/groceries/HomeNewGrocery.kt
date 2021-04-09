package com.harrisonbacordo.flatmate.ui.home.groceries

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.harrisonbacordo.flatmate.network.FlatmateNetworkResult

@Composable
fun HomeNewGrocery(initiateSave: Boolean, onSaveComplete: (FlatmateNetworkResult) -> Unit) {
    HomeNewGroceryScreen()
}

@Composable
private fun HomeNewGroceryScreen() {
    Text("New Grocery Screen")
}