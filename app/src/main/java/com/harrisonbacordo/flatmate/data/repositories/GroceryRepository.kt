package com.harrisonbacordo.flatmate.data.repositories

import com.harrisonbacordo.flatmate.data.models.Grocery
import com.harrisonbacordo.flatmate.data.models.GroceryList
import com.harrisonbacordo.flatmate.network.GroceryApi
import javax.inject.Inject

class GroceryRepository @Inject constructor(private val groceryApi: GroceryApi) {

    suspend fun updateGrocery(grocery: Grocery, groceryList: GroceryList) {
        try {
            groceryApi.updateGrocery(grocery, groceryList)
        } catch (e: Exception) {
            TODO()
        }
    }

    suspend fun addGroceryList(groceryList: GroceryList): GroceryList {
        return try {
            groceryApi.addGroceryList(groceryList)
        } catch (e: Exception) {
            TODO()
        }
    }

    suspend fun updateGroceryList(groceryList: GroceryList): GroceryList {
        return try {
            groceryApi.updateGroceryList(groceryList)
        } catch (e: Exception) {
            TODO()
        }
    }

    suspend fun addGrocery(grocery: Grocery, groceryList: GroceryList) {
        try {
            groceryApi.addGrocery(grocery, groceryList)
        } catch (e: Exception) {
            TODO()
        }
    }

    suspend fun removeGrocery(grocery: Grocery, groceryList: GroceryList) {
        try {
            groceryApi.removeGrocery(grocery, groceryList)
        } catch (e: Exception) {
            TODO()
        }
    }

}