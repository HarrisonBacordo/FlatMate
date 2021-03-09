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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.harrisonbacordo.flatmate.R
import com.harrisonbacordo.flatmate.data.mocks.Mocks
import com.harrisonbacordo.flatmate.data.models.ExpandableGroceryCardModel
import com.harrisonbacordo.flatmate.data.models.Grocery
import com.harrisonbacordo.flatmate.ui.composables.card.AddNewGroceryCard
import com.harrisonbacordo.flatmate.ui.composables.card.ExpandableCard
import com.harrisonbacordo.flatmate.ui.composables.textfield.RequiredTextFieldState
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextFieldState
import com.harrisonbacordo.flatmate.ui.theme.FlatMateHomeTheme
import com.harrisonbacordo.flatmate.ui.theme.grey700
import java.util.*

/**
 * High-level composable that holds the state and high-level UI composable of the home groceries screen
 */
@ExperimentalAnimationApi
@Composable
fun HomeGroceries() {
    val viewModel: HomeGroceriesViewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(HomeGroceriesViewModel::class.java)
    HomeGroceriesScreen(viewModel.groceryListCards, viewModel::onExpandCardClicked, viewModel::onGroceryToggled, viewModel::onGroceryAdded, viewModel::onGroceryListAdded)
}

/**
 * High-level composable that displays the home groceries screen
 */
@ExperimentalAnimationApi
@Composable
private fun HomeGroceriesScreen(
    groceryListCards: List<ExpandableGroceryCardModel>,
    onExpandClicked: (id: UUID) -> Unit,
    onGroceryToggled: (id: UUID) -> Unit,
    onAddNewGroceryClicked: (String, UUID) -> Unit,
    onAddNewGroceryListClicked: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(groceryListCards) { groceryListCardModel ->
            ExpandableCard(expandableCardModel = groceryListCardModel, onExpandClicked = onExpandClicked) {
                GroceryCheckList(groceryListCardModel.id, groceryListCardModel.groceries, onGroceryToggled, onAddNewGroceryClicked)
            }
        }
        item {
            AddNewGroceryCard(onAddNewGroceryListClicked)
        }
    }
}

@Composable
private fun GroceryCheckList(
    groceryListId: UUID,
    groceries: List<Grocery>,
    onGroceryToggled: (id: UUID) -> Unit,
    onAddNewGroceryClicked: (String, UUID) -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        groceries.forEach {
            GroceryItem(grocery = it, onGroceryToggled)
        }
        AddNewGroceryItem(
            groceryListId = groceryListId,
            newGroceryState = remember { RequiredTextFieldState() },
            isCheckmarkVisible = true,
            onGroceryAdded = onAddNewGroceryClicked
        )
    }
}

@Composable
private fun GroceryItem(grocery: Grocery, onGroceryToggled: (id: UUID) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onGroceryToggled(grocery.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = grocery.isChecked, onCheckedChange = { onGroceryToggled(grocery.id) }, modifier = Modifier.padding(end = 8.dp))
        Text(grocery.name, textAlign = TextAlign.Center)
    }
}

@Composable
private fun AddNewGroceryItem(
    groceryListId: UUID,
    newGroceryState: TextFieldState = remember { RequiredTextFieldState() },
    isCheckmarkVisible: Boolean,
    onGroceryAdded: (String, UUID) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(4f)) {
            Checkbox(modifier = Modifier.padding(end = 8.dp), checked = false, onCheckedChange = null, enabled = false)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                value = newGroceryState.text,
                label = { Text("New Grocery") },
                onValueChange = { newGroceryState.updateText(it) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    textColor = MaterialTheme.colors.onPrimary,
                    focusedLabelColor = grey700,
                )
            )
        }
        IconButton(modifier = Modifier.weight(1f), onClick = { onGroceryAdded(newGroceryState.text, groceryListId) }) {
            Icon(painter = painterResource(id = R.drawable.ic_check_24), contentDescription = null)
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
private fun PreviewGroceriesScreen() {
    FlatMateHomeTheme {
        HomeGroceriesScreen(List(8) { (Mocks.GroceryListCardMock) }, {}, {}, { _, _ -> }, {})
    }
}
