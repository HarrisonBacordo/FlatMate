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
package com.harrisonbacordo.flatmate.ui.home.chores

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.harrisonbacordo.flatmate.R
import com.harrisonbacordo.flatmate.data.mocks.Mocks
import com.harrisonbacordo.flatmate.data.models.Chore
import com.harrisonbacordo.flatmate.ui.composables.card.AddNewChoreCard
import com.harrisonbacordo.flatmate.ui.theme.FlatMateHomeTheme
import java.util.*

/**
 * High-level composable that holds the state and high-level UI composable of the home chores screen
 */
@Composable
fun HomeChores() {
    val viewModel: HomeChoresViewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(HomeChoresViewModel::class.java)
    ChoresScreen(
        chores = viewModel.chores,
        onChoreNudged = viewModel::onChoreNudged,
        onChoreCompleteToggled = viewModel::onChoreCompleteToggled,
        onChoreRemoved = viewModel::onChoreRemoved,
        onChoreEdited = viewModel::onChoreEdited
    )
}

/**
 * High-level composable that displays the home chores screen
 */
@Composable
private fun ChoresScreen(
    chores: List<Chore>,
    onChoreNudged: (String) -> Unit,
    onChoreCompleteToggled: (String) -> Unit,
    onChoreRemoved: (Chore) -> Unit,
    onChoreEdited: (Chore) -> Unit,
) {
    LazyColumn(Modifier.fillMaxHeight()) {
        items(chores) {
            ChoreCard(
                it,
                onChoreNudged = onChoreNudged,
                onChoreCompleteToggled = onChoreCompleteToggled,
                onChoreEdited = onChoreEdited,
                onChoreRemoved = onChoreRemoved
            )
        }
    }
}

/**
 * High-level composable that displays a chore card
 *
 * @param chore [Chore] to populated this [ChoreCard] with
 */
@Composable
private fun ChoreCard(
    chore: Chore,
    onChoreNudged: (String) -> Unit,
    onChoreCompleteToggled: (String) -> Unit,
    onChoreEdited: (Chore) -> Unit,
    onChoreRemoved: (Chore) -> Unit,
    ) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        var popupExpanded by remember { mutableStateOf(false) }
        ChorePopupMenu(expanded = popupExpanded, onDismissRequest = { popupExpanded = false }, onChoreRemoved = { onChoreRemoved(chore) })
        Column(Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth()) {
                Row {
                    Image(painterResource(id = R.drawable.ic_logo), "", modifier = Modifier.size(45.dp))
                    Text(chore.name, Modifier.padding(start = 8.dp).align(Alignment.CenterVertically))
                }
                IconButton({ popupExpanded = true }, Modifier.padding(end = 4.dp)) {
                    Image(painterResource(id = R.drawable.ic_popup_menu_24dp), "")
                }
            }
            Text(chore.assignedFlatmateName)
            Text(chore.interval.name, Modifier.padding(bottom = 8.dp))
            Row(Modifier.fillMaxWidth()) {
                Button({ onChoreNudged(chore.id) }, Modifier.weight(1f).padding(end = 4.dp)) {
                    Text("Nudge")
                }
                Button({ onChoreCompleteToggled(chore.id) }, Modifier.weight(1f).padding(start = 4.dp)) {
                    Text("Complete")
                }
            }
        }
    }
}

@Composable
private fun ChorePopupMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onChoreRemoved: () -> Unit,
) {
    DropdownMenu(expanded = expanded, offset = DpOffset(200.dp, (-125).dp), onDismissRequest = onDismissRequest) {
        DropdownMenuItem(onClick = onChoreRemoved) {
            Text("Delete", color = MaterialTheme.colors.error)
        }
    }
}

@Preview
@Composable
fun ChoresScreenPreview() {
    FlatMateHomeTheme {
        ChoresScreen(List(10) { Mocks.ChoreMock }, {}, {}, {}, {})
    }
}

@Preview
@Composable
fun ChoreCardPreview() {
    FlatMateHomeTheme {
        ChoreCard(Mocks.ChoreMock, {}, {}, {}, {})
    }
}
