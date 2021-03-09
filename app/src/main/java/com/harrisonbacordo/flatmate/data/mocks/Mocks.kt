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
package com.harrisonbacordo.flatmate.data.mocks

import com.harrisonbacordo.flatmate.data.models.Chore
import com.harrisonbacordo.flatmate.data.models.ExpandableGroceryCardModel
import com.harrisonbacordo.flatmate.data.models.Grocery
import com.harrisonbacordo.flatmate.data.models.Interval

object Mocks {
    val ChoreMock
        get() = Chore(name = "Clean kitchen", assignedFlatmateName = "Elon Musk", assignedFlatmateId = "", interval = Interval.Fortnightly)
    val GroceryListMock: List<Grocery>
        get() = listOf(
            Grocery("Bananas"),
            Grocery("Apple"),
            Grocery("Ground beef"),
            Grocery("Soup"),
            Grocery("Beans"),
            Grocery("Broccolli"),
            Grocery("Bananas"),
            Grocery("Bananas")
        )
    val GroceryListCardMock
        get() = ExpandableGroceryCardModel(title = "Harrison's Groceries", expanded = false, groceries = GroceryListMock)

}
