package com.harrisonbacordo.flatmate.data.models

import com.harrisonbacordo.flatmate.util.Keys
import java.util.*

data class GroceryList(
    val id: UUID = UUID.randomUUID(),
    val name: String = "New Grocery List",
    val ownerId: String = "",
    val sharedUserIds: List<String> = emptyList(),
    val groceries: List<Grocery> = emptyList(),
) {
    fun toApiMap(): Map<String, Any> {
        return mapOf(
            Keys.Firestore.GroceryList.id to id.toString(),
            Keys.Firestore.GroceryList.name to name,
            Keys.Firestore.GroceryList.ownerId to ownerId,
            Keys.Firestore.GroceryList.sharedUserIds to sharedUserIds,
            Keys.Firestore.GroceryList.groceries to groceries,
        )
    }
}