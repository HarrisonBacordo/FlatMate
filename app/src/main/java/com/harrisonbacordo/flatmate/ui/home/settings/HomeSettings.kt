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
package com.harrisonbacordo.flatmate.ui.home.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.harrisonbacordo.flatmate.R
import com.harrisonbacordo.flatmate.ui.theme.FlatMateHomeTheme

/**
 * High-level composable that holds the state and high-level UI composable of the home settings screen
 */
@Composable
fun HomeSettings(onFlatmatesClicked: () -> Unit, onLogoutSuccessful: () -> Unit) {
    val viewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(HomeSettingsViewModel::class.java)
    HomeSettingsScreen({ onFlatmatesClicked() }, { viewModel.signCurrentUserOut(onLogoutSuccessful) })
}

/**
 * High-level composable that displays the home settings screen
 */
@Composable
private fun HomeSettingsScreen(
    onFlatmatesClicked: () -> Unit,
    onLogoutClicked: () -> Unit,
) {
    LazyColumn {
        item {
            SettingsListItem(title = "Flatmates", leadingIcon = R.drawable.ic_join_existing_flat_24dp, onClick = onFlatmatesClicked)
            SettingsListItem(title = "Log out", leadingIcon = R.drawable.ic_logout, onClick = onLogoutClicked)
        }
    }
}

@Composable
private fun SettingsListItem(
    title: String,
    leadingIcon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier
            .clickable(onClick = onClick)
            .padding(vertical = 24.dp)
            .fillMaxWidth()
    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Row {
                    Image(
                        painterResource(id = leadingIcon),
                        "",
                        Modifier
                            .width(24.dp)
                            .height(24.dp), colorFilter = ColorFilter.tint(Color.Black)
                    )
                    Text(title, modifier = Modifier.padding(start = 16.dp))
                }
                Image(
                    painterResource(id = R.drawable.ic_arrow_right_24dp),
                    "",
                    Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .padding(end = 8.dp), colorFilter = ColorFilter.tint(Color.Black)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSettingsScreen() {
    FlatMateHomeTheme {
        HomeSettingsScreen({}, {})
    }
}

@Preview
@Composable
private fun PreviewSettingsListItem() {
    FlatMateHomeTheme {
        SettingsListItem(title = "Log Out", leadingIcon = 1, onClick = {})
    }
}
