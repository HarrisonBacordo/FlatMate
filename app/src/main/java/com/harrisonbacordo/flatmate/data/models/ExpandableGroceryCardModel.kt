package com.harrisonbacordo.flatmate.data.models

import java.util.*
import javax.annotation.concurrent.Immutable

@Immutable
data class ExpandableGroceryCardModel(
    override var id: UUID = UUID.randomUUID(),
    override val title: String,
    override val expanded: Boolean = false,
    val groceries: List<Grocery>,
) : ExpandableCardModel {

    constructor(groceryList: GroceryList) : this(
        id = groceryList.id,
        title = groceryList.name,
        groceries = groceryList.groceries,
    )

    fun hasGroceryWithUUID(uuid: UUID): Boolean {
        return groceries.find { it.id == uuid } != null
    }

    fun toggleGroceryWithUUID(uuid: UUID): List<Grocery> {
        val newGroceryList = mutableListOf<Grocery>()
        groceries.forEach {
            if (it.id == uuid) {
                newGroceryList.add(it.copy(isChecked = !it.isChecked))
            } else {
                newGroceryList.add(it)
            }
        }
        return newGroceryList
    }
}
