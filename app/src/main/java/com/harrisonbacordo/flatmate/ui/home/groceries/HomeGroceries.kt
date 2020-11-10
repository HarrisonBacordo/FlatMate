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
package com.harrisonbacordo.flatmate.ui.home.groceries

import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview

/**
 * High-level composable that holds the state and high-level UI composable of the home groceries screen
 */
@Composable
fun HomeGroceries() {
    val viewModel: HomeGroceriesViewModel = viewModel()
    HomeGroceriesScreen()
}

/**
 * High-level composable that displays the home groceries screen
 */
@Composable
private fun HomeGroceriesScreen() {
    Text("Groceries")
}

@Preview
@Composable
private fun PreviewGroceriesScreen() {
    HomeGroceriesScreen()
}
