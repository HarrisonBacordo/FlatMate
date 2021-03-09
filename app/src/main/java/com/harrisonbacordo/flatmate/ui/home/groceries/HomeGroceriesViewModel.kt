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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrisonbacordo.flatmate.data.mocks.Mocks
import com.harrisonbacordo.flatmate.data.models.ExpandableGroceryCardModel
import com.harrisonbacordo.flatmate.data.models.Grocery
import com.harrisonbacordo.flatmate.data.models.GroceryList
import com.harrisonbacordo.flatmate.data.repositories.GroceryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * [ViewModel] associated with [HomeGroceries]
 */
class HomeGroceriesViewModel @ViewModelInject constructor(private val groceryRepository: GroceryRepository) : ViewModel() {
    var groceryListCards: List<ExpandableGroceryCardModel> by mutableStateOf(getGroceryCards())

    init {
        getGroceryCards()
    }

    private fun getGroceryCards(): List<ExpandableGroceryCardModel> {
        val testList = arrayListOf<ExpandableGroceryCardModel>()
        repeat(5) { testList += ExpandableGroceryCardModel(title = "Card $it", expanded = false, groceries = Mocks.GroceryListMock) }
        return testList
    }

    fun onExpandCardClicked(expandedId: UUID) {
        val newCardList = arrayListOf<ExpandableGroceryCardModel>()
        groceryListCards.forEach {
            if (it.id == expandedId) {
                newCardList.add(it.copy(expanded = !it.expanded))
            } else {
                newCardList.add(it)
            }
        }
        groceryListCards = newCardList
    }

    fun onGroceryToggled(toggledGroceryId: UUID) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    val newCardList = arrayListOf<ExpandableGroceryCardModel>()
                    groceryListCards.forEach {
                        if (it.hasGroceryWithUUID(toggledGroceryId)) {
                            newCardList.add(it.copy(groceries = it.toggleGroceryWithUUID(toggledGroceryId)))
                        } else {
                            newCardList.add(it)
                        }
                    }
                    groceryListCards = newCardList
                }
            } catch (e: Exception) {
                TODO()
            }
        }
    }

    fun onGroceryListAdded() {
        val groceryList = GroceryList()
        groceryListCards = groceryListCards.toMutableList().also {
            it.add(ExpandableGroceryCardModel(groceryList))
        }
        viewModelScope.launch(Dispatchers.IO) {
                try {
                groceryRepository.addGroceryList(groceryList)
            } catch (e: Exception) {
                TODO()
            }
        }
    }

    fun onGroceryAdded(groceryName: String, groceryListId: UUID) {
        val newGrocery = Grocery(groceryName)
        val newCardList = arrayListOf<ExpandableGroceryCardModel>()
        groceryListCards.forEach {
            if (it.id == groceryListId) {
                newCardList.add(it.copy(groceries = it.groceries.toMutableList().apply { add(newGrocery) }))
            } else {
                newCardList.add(it)
            }
        }
        groceryListCards = newCardList
    }
}
